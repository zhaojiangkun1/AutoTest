package com.shuzutech.enterpriseInformation;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class Enterprise {
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\enterpriseInformation\\";
    RequestInterface ri = new RequestInterface();
    @Test
    public void enterpriseSetting() throws Exception {
        String fileName = "enterpriseSetting.xml";
        ri.requestInterface(filePath+fileName, InterfaceName.TEST);
    }


    @Test
    public void enterpriseQuery() throws Exception {
        String fileName = "enterpriseQuery.xml";
        ri.requestInterface(filePath+fileName,InterfaceName.TEST);
    }
}
