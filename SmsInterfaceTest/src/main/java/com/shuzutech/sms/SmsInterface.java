package com.shuzutech.sms;

import com.shuzutech.config.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Date;

public class SmsInterface {

    private static String appId = "";
    private static String url = "";

    private static String date = TestConfig.sd.format(new Date());

    public static String sendSmsInterface(String body,InterfaceNum name) throws Exception {
        appId = TestConfig.getAppid(name);
        url = TestConfig.getUrl(name);
        System.out.println(body);
        String accessToken = GetAccessToken.getToken(name);
        String pj = body + date + accessToken;
        String contentMd5 = Md5.EncoderByMd5(pj);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(body,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.setHeader("APPID",appId);
        post.setHeader("Date",date);
        post.setHeader("Content-MD5",contentMd5);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }


}
