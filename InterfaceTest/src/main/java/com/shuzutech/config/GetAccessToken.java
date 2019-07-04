package com.shuzutech.config;


import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GetAccessToken {


    @Test
    public static String getAccessToken() throws IOException, TransformerException, SAXException, ParserConfigurationException {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sd.format(new Date());

        //定义需要的字符串
        String appid = "f07dcd92fce254d4b344cb07dc4901e2";
        String appSecret = "ff63dbfed0bd85fdcf7adfc57cdfb3da40f5bda9";
//        String appid = "253478c77363a9156e3e633bcb76dc1e";
//        String appSecret = "70492f9f3f599ff030493a20631788c1f3ae52e1";
        String url;
        String uri;
        String getUrl;
        ResourceBundle bundle;

        //获取测试url地址
        bundle = ResourceBundle.getBundle("config.application", Locale.CHINA);
//        bundle = ResourceBundle.getBundle("config.application",new Locale("zh","CN"));
        url = bundle.getString("test.url");//测试环境地址
//        url = bundle.getString("pro.url");//正式环境地址
        uri = bundle.getString("get.uri");
        getUrl = url+uri;

        //封装请求参数
        List<BasicNameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("appId",appid));
        list.add(new BasicNameValuePair("appSecret",appSecret));

        //转化参数
        String params = EntityUtils.toString(new UrlEncodedFormEntity(list, Consts.UTF_8));
        System.out.println("Get请求中的params:"+params);

        //创建HttpGet请求
        HttpClient client = new DefaultHttpClient();
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
        writeAccessToken(accessToken,date);
        return accessToken;

    }


    public static void writeAccessToken(String newAccessToken,String newDate) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String fileName = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\result\\result.xml";
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        Element root = document.getDocumentElement();
        NodeList resultList = root.getElementsByTagName("result");
        Node item = resultList.item(0);
        Element resultElement = (Element) item;
        NodeList accessToken = resultElement.getElementsByTagName("accessToken");
        NodeList date = resultElement.getElementsByTagName("date");
        System.out.println("旧的accessToken:"+accessToken.item(0).getTextContent());
        System.out.println("新的accessToken:"+newAccessToken);
        //修改数据
        accessToken.item(0).setTextContent(newAccessToken);
        date.item(0).setTextContent(newDate);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(fileName);
        transformer.transform(source,result);
    }

}
