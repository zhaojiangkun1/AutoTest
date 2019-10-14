package com.shuzutech.config;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;


import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetAccessToken {

    @Test
    public static String getAccessToken(InterfaceName name) throws IOException {

        String appid = GetAppInfo.getAppInfo(name).getAppId();
        String appSecret = GetAppInfo.getAppInfo(name).getAppSecret();
        String getUrl = GetAppInfo.getAppInfo(name).getAddress();

        //封装请求参数
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("appId",appid));
        list.add(new BasicNameValuePair("appSecret",appSecret));

        //转化参数
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        System.out.println("Get请求中的params:"+params);

        //创建HttpGet请求
        HttpClient client = HttpClientBuilder.create().build();
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
    public static String getToken(InterfaceName num) throws Exception {
        String access_toekn="";
        AppInfo appInfo = GetAppInfo.getAppInfo(num);
        Date updateTime = appInfo.getUpdateTime();
        System.out.println("之前的时间："+updateTime);
        Date current_Time = new Date();
        System.out.println("当前的时间:"+current_Time);
        long diffSec = (current_Time.getTime()-updateTime.getTime())/1000;
        System.out.println("时间差："+diffSec);
        if (diffSec > 7200){
            access_toekn = getAccessToken(num);
            appInfo.setAccessToken(access_toekn);
            appInfo.setUpdateTime(current_Time);
            GetAppInfo.updateAppInfo(num,appInfo);
        }else {
            access_toekn = GetAppInfo.getAppInfo(num).getAccessToken();
        }
        return access_toekn;
    }




}
