package com.shuzutech.sms;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
import java.util.Date;

public class GetAccessToken {

    String str=null;
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sd.format(new Date());

    /**
     * 获取AccessToken
     * @return
     * @throws IOException
     */
    public String authentication1() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        String result;//用来存放结果，并打印出来
//        String url = "http://testservice.shuzutech.com:8081/invoice/token?&appId=f07dcd92fce254d4b344cb07dc4901e2&appSecret=ff63dbfed0bd85fdcf7adfc57cdfb3da40f5bda9";
        String url = "https://paymgmt.shuzutech.com/invoice/token?&appId=253478c77363a9156e3e633bcb76dc1e&appSecret=70492f9f3f599ff030493a20631788c1f3ae52e1";//正式环境
        HttpClient client = new DefaultHttpClient();
        //这个是用来执行get方法的
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);//需要捕获异常
        result = EntityUtils.toString(response.getEntity(),"utf-8");

        String str1 = "<access_token>";
        String str2 = "</access_token>";
        str = result.substring(result.indexOf(str1)+str1.length(),result.indexOf(str2));
        System.out.println(result);
        writeAccessToken(str,date);
        return str;

    }

    public static void writeAccessToken(String newAccessToken,String newDate) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String fileName = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\sms\\result.xml";
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
