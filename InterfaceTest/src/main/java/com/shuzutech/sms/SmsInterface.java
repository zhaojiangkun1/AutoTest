package com.shuzutech.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuzutech.config.Md5;
import com.shuzutech.config.ReadFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
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
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsInterface {

    String appId = "66c9cf9051f23b55bdb3b5734caa4830";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String url = "http://106.14.193.154:9085/sms-test/sms/sendSms";  //测试环境
//    String url = "http://sms.shuzutech.com:8080/sms-center/sms/sendSms";  //生产环境
//    String url = "http://106.14.13.223:8080/sms-center/sms/sendSms";
        String uri = "http://106.14.193.154:9085/sms-test/sms/getSmsResult";//短消息推送结果查询，测试环境
//    String uri = "http://106.14.13.223:8080/sms-center/sms/getSmsResult";
//    String uri = "http://sms.shuzutech.com:8080/sms-center/sms/getSmsResult";
    String date = simpleDateFormat.format(new Date());
    String accessToken;
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\sms\\";

    public void sendSmsInterface(String fileName,String templateId) throws IOException, NoSuchAlgorithmException, SAXException, ParserConfigurationException, ParseException, TransformerException {
        String body = updateSmsJson(ReadFile.readFile(filePath+fileName),templateId);
//        System.out.println("转换成字符串:"+body);
//        System.out.println("accessToken:"+accessToken);
//        System.out.println("date:"+date);
        getToken("result.xml");
        String pj = body + date + accessToken;
//        System.out.println("拼接后的字符串"+pj);
        String contentMd5 = Md5.EncoderByMd5(pj);
//        System.out.println("加密后："+contentMd5);
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
        System.out.println(result);
//        System.out.println(response.getStatusLine().getStatusCode());
//        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
    }

    public void getSendResultInterface(String fileName) throws IOException, NoSuchAlgorithmException, SAXException, ParserConfigurationException, ParseException, TransformerException {
        String body = ReadFile.readFile(filePath+fileName);
        getToken("result.xml");
        String s = body + date + accessToken;
        String md5Content = Md5.EncoderByMd5(s);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);
        StringEntity entity = new StringEntity(body,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        post.setHeader("APPID",appId);
        post.setHeader("Date",date);
        post.setHeader("Content-MD5",md5Content);
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("执行的结果为："+result);
        System.out.println(response.getStatusLine().getStatusCode());
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);

    }

    public void getToken(String file) throws IOException, SAXException, ParserConfigurationException, ParseException, TransformerException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(filePath+file);
        Element root = document.getDocumentElement();
        NodeList resultList = root.getElementsByTagName("result");
        Node item = resultList.item(0);

        Element resultElement = (Element) item;
        NodeList getDate = resultElement.getElementsByTagName("date");
        NodeList getAccessToken = resultElement.getElementsByTagName("accessToken");
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
            accessToken = new GetAccessToken().authentication1();
            getAccessToken.item(0).setTextContent(accessToken);
            getDate.item(0).setTextContent(date);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //DOMSource source = new DOMSource(doc);
            Source source = new DOMSource(document);
            //StreamResult result = new StreamResult();
            Result result = new StreamResult(filePath+file);
            transformer.transform(source,result);
        }else {
            accessToken = getAccessToken.item(0).getTextContent();
        }
        System.out.println("本次请求的accessToken:"+accessToken);
    }

    public String updateSmsJson(String fileName,String templateId){
        String placedate= "{[placeholder:date]}##";
        String placeEnd = "|@|{[placeholder:amount]}##13.6|@|{[placeholder:url]}##https://fp.bwjf.cn/kB1IEGIpUK";

        String placeRemark = "{[placeholder:remark]}##南京数族|@|{[placeholder:number]}##423635";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = simpleDateFormat.format(new Date());

        JSONObject jsonObject = JSON.parseObject(fileName);
//        System.out.println(jsonObject.getString("mobiles"));
        if(templateId == "729"){
            jsonObject.put("templateId",templateId);
            jsonObject.put("placeHolderContent",placedate+date+placeEnd);
        }
        if(templateId == "759"){
            jsonObject.put("templateId",templateId);
            jsonObject.put("placeHolderContent",placeRemark);
        }

//        System.out.println(jsonObject.getString("placeHolderContent"));
        String paraseSms = jsonObject.toString();
        //System.out.println(paraseSms);
        return paraseSms;
    }

}
