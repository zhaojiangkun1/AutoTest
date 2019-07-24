package com.shuzutech.config;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.util.Date;

public class RequestInterface {

    String date = ConfigFile.sd.format(new Date());
    String access_token;
    String body;

    public void requestInterface(String file,InterfaceName name) throws Exception {
        body = ReadFile.readFile(file);
        String url = ConfigFile.postUrl(name);
        String APPID = ConfigFile.getAppid(name);
        access_token=GetAccessToken.getToken(name);
        System.out.println("本次请求的accessToken:"+access_token);
        String headerContent = body + date + access_token;
        String s = Md5.EncoderByMd5(headerContent);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //添加Body信息
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
//        OutputStream out = new FileOutputStream("D:\\工作内容\\测试信息\\接口测试用例设计\\税局编码33.0.xml");
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out,"utf-8"));
//        bw.write(result);
        System.out.println(" ");
        System.out.println(result);
    }
}
