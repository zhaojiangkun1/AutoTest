package com.shuzutech.invoice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class UpdateInvoiceBody {

    public void updateMerchantsInfo(String fileName, String newShnsrsbh, String newJsbh) throws Exception {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        Element root = document.getDocumentElement();
        NodeList inputlist = root.getElementsByTagName("input");
        Node item = inputlist.item(0);
        Element inputElement = (Element) item;
        NodeList shnsrsbh = inputElement.getElementsByTagName("shnsrsbh");
        NodeList jsbh = inputElement.getElementsByTagName("jsbh");
        //修改数据
        shnsrsbh.item(0).setTextContent(newShnsrsbh);
        jsbh.item(0).setTextContent(newJsbh);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //DOMSource source = new DOMSource(doc);
        Source source = new DOMSource(document);
        //StreamResult result = new StreamResult();
        Result result = new StreamResult(fileName);
        transformer.transform(source,result);
    }


}
