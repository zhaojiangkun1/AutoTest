package com.shuzutech.invoice;

import com.shuzutech.config.GetAccessToken;
import com.shuzutech.config.Md5;
import com.shuzutech.config.ReadFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MakeInvoiceInterface {
    //测试环境
    String url;
    String appId = "f07dcd92fce254d4b344cb07dc4901e2";
    String accessToken;

    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\result\\result.xml";


    public void getUrl(){
        ResourceBundle bundle = ResourceBundle.getBundle("config.application", Locale.CHINA);
        url = bundle.getString("test.url") + bundle.getString("post.uri");//测试环境
//        url = bundle.getString("devPost.url") + bundle.getString("post.uri");//正式环境
    }

    public int makeInvoice(String fileName) throws Exception {
        //获取当前时间
        SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sd.format(new Date());

        new UpdateFpqqlsh().updateFppqqlsh(fileName);
        String body = new ReadFile().readFile(fileName);

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        Element root = document.getDocumentElement();
        NodeList resultList = root.getElementsByTagName("result");
        Node item = resultList.item(0);
        Element resultElement = (Element) item;
        NodeList getDate = resultElement.getElementsByTagName("date");
        String oldDate = getDate.item(0).getTextContent();

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTime = sdf.parse(oldDate);
        Date endTime = sdf.parse(date);
        //计算前后时间差，单位是秒
        long diffSec = (endTime.getTime() - beginTime.getTime())/1000;
        System.out.println("之前的时间:"+beginTime);
        System.out.println("当前的时间:"+endTime);
        System.out.println("前后时间差："+diffSec);

        if(diffSec > 7200){
            accessToken = GetAccessToken.getAccessToken();
        }else {
            NodeList getAccessToken = resultElement.getElementsByTagName("accessToken");
            accessToken = getAccessToken.item(0).getTextContent();
        }


        System.out.println("本次请求的accessToken:"+accessToken);

        String md5Content = Md5.EncoderByMd5(body+date+accessToken);
        System.out.println("加密后的文件为:" + md5Content);
        //执行getUrl方法，获取Url
        getUrl();

        //模拟HttpPost请求
        HttpClient client = new DefaultHttpClient();
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
