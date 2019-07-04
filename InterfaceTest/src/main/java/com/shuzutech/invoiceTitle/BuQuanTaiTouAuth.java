package com.shuzutech.invoiceTitle;


import com.shuzutech.config.Md5;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BuQuanTaiTouAuth {

    String string;
    String access_token;

    @BeforeTest
    public void authentication1() throws IOException {
        String result;//用来存放结果，并打印出来
        String url = "http://106.14.193.154:8081/invoice/token?&appId=00ca44d59d6c121bdd567053fac8dcda&appSecret=dc684a1d7548d31b4abe655c3f03def0a7b400d2";
        HttpClient client = new DefaultHttpClient();
        //这个是用来执行post方法的
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);//需要捕获异常
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        String str1 = "<access_token>";
        String str2 = "</access_token>";
        access_token = result.substring(result.indexOf(str1)+str1.length(),result.indexOf(str2));
        System.out.println(result);
        System.out.println("====================================");
        System.out.println("accessToken:"+access_token);
    }

    @Test
    public void fullInvoiceTitle() throws Exception {
        String body="";
        File file = new File("D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceTitle\\buquantaitou.xml");
        if (!file.exists()){
            System.out.println("找不到指定的文件");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        while ((string = bufferedReader.readLine())!=null){
            body = body + string;
        }
        bufferedReader.close();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        String APPID = "00ca44d59d6c121bdd567053fac8dcda";
        System.out.println("access_token:"+ access_token);
        String http = body + date + access_token;
        String s = Md5.EncoderByMd5(http);
        System.out.println(s);
        String url = "http://106.14.193.154:8081/invoice/business";
        //声明一个client对象，用来进行方法的执行
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(url);
        //添加body信息
        StringEntity entity = new StringEntity(body,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(entity);
        //设置请求头信息
        post.setHeader("APPID",APPID);
        post.setHeader("Date",date);
        post.setHeader("Content-MD5",s);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

}
