package com.shuzutech.invoice;

import com.shuzutech.config.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeInvoiceInterface {

    private static String accessToken;

//   private static String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\result\\result.xml";

    public static int makeInvoice(String fileName,InterfaceName name) throws Exception {
        //获取当前时间
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sd.format(new Date());

        String url = ConfigFile.postUrl(name);
        System.out.println("请求的url:"+url);
        String appId = ConfigFile.getAppid(name);

        new UpdateFpqqlsh().updateFppqqlsh(fileName);
        String body = new ReadFile().readFile(fileName);
        UpdateFpInfo.updateFpInfo(fileName,new Date());

        accessToken = GetAccessToken.getToken(name);
        System.out.println("本次请求的accessToken:"+accessToken);

        String md5Content = Md5.EncoderByMd5(body+date+accessToken);
        System.out.println("加密后的文件为:" + md5Content);

        //模拟HttpPost请求
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        //添加请求Body
        StringEntity entity = new StringEntity(body,"utf-8");
        post.setEntity(entity);

        //设置请求头信息
        post.setHeader("APPID",appId);
        post.setHeader("Date",date);
        post.setHeader("Content-MD5",md5Content);

        //执行Post请求，把返回的请求输出
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("返回的报文:"+result);

        //获取返回的信息及错误码
        String returncode1="<returncode>";
        String returncode2="</returncode>";
        String returncode = result.substring(result.indexOf(returncode1)+12,result.indexOf(returncode2));
        int code = Integer.valueOf(returncode);
        return code;
    }

}
