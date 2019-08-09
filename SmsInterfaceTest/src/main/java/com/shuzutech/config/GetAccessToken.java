package com.shuzutech.config;


import com.shuzutech.bean.SaveToken;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetAccessToken {

    private static String str=null;
    private static String date = TestConfig.sd.format(new Date());
    /**
     * 获取AccessToken
     * @return
     * @throws IOException
     */
    public static String getAccessToken(InterfaceNum name) throws Exception {
        String result;
        String geturl = TestConfig.getAccessTokenUrl(name);

        String appid = TestConfig.getAppid(name);
        String appSecret = TestConfig.getAppSecret(name);

        String url = geturl+"?&"+"appId="+appid+"&appSecret="+appSecret;

        HttpClient client = new DefaultHttpClient();
        //这个是用来执行get方法的
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);//需要捕获异常
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        String str1 = "<access_token>";
        String str2 = "</access_token>";
        str = result.substring(result.indexOf(str1)+str1.length(),result.indexOf(str2));
        System.out.println(result);
        return str;

    }

    public static int judgeEnv(InterfaceNum num){
        int id=0;
        if(num == InterfaceNum.PRO||num == InterfaceNum.PRONEWREQ || num == InterfaceNum.NEWGETRESULT
            || num == InterfaceNum.GETRESULT){
            id = 1;
        }
        else {
            id =2;
        }
        return id;

    }


    public static String getToken(InterfaceNum name) throws Exception {

        String accessToken;

        SqlSession session = DataBaseUtil.getSqlSession();
        int id = judgeEnv(name);
        SaveToken saveToken = session.selectOne("getToken",id);
        SaveToken st = new SaveToken();
        Date oldDate = saveToken.getCurrentTime();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTime = sdf.parse(date);
        //计算前后时间差，单位是秒
        long diffSec = (endTime.getTime() - oldDate.getTime())/1000;
        System.out.println("之前的时间:"+oldDate);
        System.out.println("当前的时间:"+endTime);
        System.out.println("前后时间差："+diffSec);

        if(diffSec > 7200){
            accessToken = GetAccessToken.getAccessToken(name);
            st.setId(id);
            st.setCurrentTime(endTime);
            st.setAccessToken(accessToken);
            session.update("updateToken",st);
            session.commit();
        }else {
            accessToken = saveToken.getAccessToken();
        }
        return accessToken;
    }

}
