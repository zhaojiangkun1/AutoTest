package com.shuzutech.invoice;


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

public class UpdateFpqqlsh {


    public void updateFppqqlsh(String fileName) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        Element root = document.getDocumentElement();
        NodeList inputlist = root.getElementsByTagName("input");
        Node item = inputlist.item(0);
        Element inputElement = (Element) item;
        NodeList fpqqlsh = inputElement.getElementsByTagName("fpqqlsh");
//        System.out.println("fpqqlsh:"+fpqqlsh.item(0).getTextContent());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        int n = (int) (1+Math.random()*1000);
        String date = simpleDateFormat.format(new Date());
        String newFpqqlsh = n + date;
        //修改数据
        System.out.println("本次开票的发票请求流水号:"+newFpqqlsh);
        fpqqlsh.item(0).setTextContent(newFpqqlsh);
        //修改后的数据写入文件
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(fileName);
        transformer.transform(source,result);//将 XML==>Source 转换为 Result
    }

}
