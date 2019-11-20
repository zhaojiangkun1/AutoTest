package com.shuzutech.config;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;

public class UpdateFpInfo {

    public static void updateFpInfo(String fileName, Date date) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        Element root = document.getDocumentElement();
        NodeList inputlist = root.getElementsByTagName("input");
        Node item = inputlist.item(0);
        Element inputElement = (Element) item;
        NodeList fpqqlshElement = inputElement.getElementsByTagName("fpqqlsh");
        String fpqqlsh = fpqqlshElement.item(0).getTextContent();
        NodeList fplxdmElement = inputElement.getElementsByTagName("fplxdm");
        String fplxdm = fplxdmElement.item(0).getTextContent();
        String nsrsbh="";
        int id = GetSaveFpInfo.getSaveFpInfo()+1;
        if (fplxdm.equals("026")){
            NodeList nsrsbhElement = inputElement.getElementsByTagName("shnsrsbh");
            nsrsbh = nsrsbhElement.item(0).getTextContent();
        }else {
            NodeList jsbhElement = inputElement.getElementsByTagName("jsbh");
            String jsbh = jsbhElement.item(0).getTextContent();
            nsrsbh = jsbh.substring(0,jsbh.indexOf("~~"));
        }

        SqlSession session = DataBaseUtil.getSqlSession();
        SaveFpInfo saveFpInfo = new SaveFpInfo();
        saveFpInfo.setId(id);
        saveFpInfo.setFpqqlsh(fpqqlsh);
        saveFpInfo.setFplxdm(fplxdm);
        saveFpInfo.setNsrsbh(nsrsbh);
        saveFpInfo.setUpdateTime(date);
        session.insert("insertFpInfo",saveFpInfo);
        session.commit();

    }
}
