package com.shuzutech.sms;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class SimulationSendMessage {

    @Test
    public void simulationSendMessgae() throws IOException {
        String json = "{\n" +
                "  \"enterpriseId\": \"124230400\",\n" +
                "  \"mobiles\": \"13701476279\",\n" +
                "  \"templateId\": \"729\",\n" +
                "  \"placeHolderContent\":\"{[placeholder:date]}##2019-06-14 12:07|@|{[placeholder:amount]}##63.6|@|{[placeholder:url]}##https://fp.bwjf.cn/kB1IEGIpUK\"\n" +
                "}";
        String url = "http://192.168.1.159:8086/smstest/sms/sendSms";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(json,"utf-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("执行的结果为："+result);
    }

}
