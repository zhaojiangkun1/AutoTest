package com.shuzutech.invoice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class UpdateGroup {

    public static void updateGroup(String fileName,String spsl,
                                   String dj,String je,
                                   String sl,String se) throws Exception{

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        Element root = document.getDocumentElement();

        NodeList inputlist = root.getElementsByTagName("input");
        Node item = inputlist.item(0);
        Element inputElement = (Element) item;
        NodeList fyxm = inputElement.getElementsByTagName("fyxm");

        Node item1 = fyxm.item(0);
        Element fyxmElement = (Element) item1;
        NodeList groupList = fyxmElement.getElementsByTagName("group");
        Node groupItem = groupList.item(0);

        Element groupElement = (Element)groupItem;
        NodeList spslList = groupElement.getElementsByTagName("spsl");
        NodeList spdjList = groupElement.getElementsByTagName("dj");
        NodeList jeList = groupElement.getElementsByTagName("je");
        NodeList slList = groupElement.getElementsByTagName("sl");
        NodeList seList = groupElement.getElementsByTagName("se");

        //修改商品金额
        spslList.item(0).setTextContent(spsl);
        spdjList.item(0).setTextContent(dj);
        jeList.item(0).setTextContent(je);
        slList.item(0).setTextContent(sl);
        seList.item(0).setTextContent(se);

//        //判断，如果税率sl不等于0，则lslbs必须为空
//        NodeList lslbsList = groupElement.getElementsByTagName("lslbs");
//        double slCast = Double.valueOf(sl);
//        if(slCast != 0){
//            lslbsList.item(0).setTextContent("");
//        }else{
//            lslbsList.item(0).setTextContent("3");
//        }

        //修改后的数据写入文件
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(document);
        Result result = new StreamResult(fileName);
        transformer.transform(source,result);//将 XML==>Source 转换为 Result
    }

}
