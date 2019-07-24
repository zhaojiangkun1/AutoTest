package com.shuzutech.smartPrint;


import com.shuzutech.config.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FpdyDataRequestInterface {

    String APPID = "f07dcd92fce254d4b344cb07dc4901e2";//测试环境的appid
//    String APPID = "253478c77363a9156e3e633bcb76dc1e";//正式环境的appid

    String url = ConfigFile.postUrl(InterfaceName.TEST); //开票的url
//    String url = new ConfigFile().getBundleResource1();//臻票云抬头联想
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = simpleDateFormat.format(new Date());
    String access_token;
    String body;

    public String requestInterface(String file,InterfaceName name) throws IOException, NoSuchAlgorithmException, ParseException, ParserConfigurationException, TransformerException, SAXException {
        body = ReadFile.readFile(file);
        getToken(name);
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
//        System.out.println(" ");
//        System.out.println(result);
        return result;
    }
    public void getToken(InterfaceName name) throws ParseException, SAXException, TransformerException, ParserConfigurationException, IOException {
        String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\result\\result.xml";
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
            access_token = GetAccessToken.getAccessToken(name);
        }else {
            NodeList getAccessToken = resultElement.getElementsByTagName("accessToken");
            access_token = getAccessToken.item(0).getTextContent();
        }
    }
}
