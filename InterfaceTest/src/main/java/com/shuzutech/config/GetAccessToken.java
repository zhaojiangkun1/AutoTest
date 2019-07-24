package com.shuzutech.config;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;


import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetAccessToken {

    private static String current_time = ConfigFile.sd.format(new Date());


    @Test
    public static String getAccessToken(InterfaceName name) throws IOException {

        String appid = ConfigFile.getAppid(name);
        String appSecret = ConfigFile.getAppSecret(name);
        String getUrl = ConfigFile.getUrl(name);

        //封装请求参数
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("appId",appid));
        list.add(new BasicNameValuePair("appSecret",appSecret));

        //转化参数
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        System.out.println("Get请求中的params:"+params);

        //创建HttpGet请求
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(getUrl+"?"+params);
        //执行httpGet
        HttpResponse response = client.execute(httpGet);
        //获取执行的结果，把结果转化成字符串
        String result = EntityUtils.toString(response.getEntity(),"utf-8");

        //获取结果中的AccessToken，并返回
        String str1 = "<access_token>";
        String str2 = "</access_token>";
        String accessToken = result.substring(result.indexOf(str1)+(str1).length(),
                             result.indexOf(str2));
        System.out.println("accessToken:"+accessToken);
//        writeAccessToken(accessToken,date);
        return accessToken;

    }

    @Test
    public static String getToken(InterfaceName name) throws Exception {

        String access_token;
        SqlSession session = DataBaseUtil.getSqlSession();
        SaveToken saveToken = session.selectOne("getToken",1);

        Date old_time = saveToken.getCurrentTime();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTime = sdf.parse(current_time);
        //计算前后时间差，单位是秒
        long diffSec = (endTime.getTime() - old_time.getTime())/1000;
        System.out.println("之前的时间:"+old_time);
        System.out.println("当前的时间:"+endTime);
        System.out.println("前后时间差："+diffSec);


        if(diffSec > 7200){
            access_token = GetAccessToken.getAccessToken(name);
            SaveToken st = new SaveToken();
            st.setId(1);
            st.setCurrentTime(endTime);
            st.setAccessToken(access_token);
            session.update("updateToken",st);
            session.commit();
        }else {
            String old_AccessToken = saveToken.getAccessToken();
            access_token = old_AccessToken;
        }

        return access_token;
    }


}
