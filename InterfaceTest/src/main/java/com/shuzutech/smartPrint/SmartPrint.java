package com.shuzutech.smartPrint;

import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;


public class SmartPrint {
    /**
     * 智能打印
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\smartprint\\smartprint.xml";
    @Test
    public void smartPrint() throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, ParseException, TransformerException {
        new RequestInterface().requestInterface(file);
    }

    /**
     * 普通打印接口
     */

    @Test
    public void invoicePrint() throws SAXException, ParseException, NoSuchAlgorithmException, IOException, ParserConfigurationException, TransformerException {
        String fileName = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoice\\invoiceprint.xml";
        new RequestInterface().requestInterface(fileName);
    }

    /**
     * 发票打印查询
     */

    @Test
    public void printQuery() throws SAXException, ParseException, NoSuchAlgorithmException, IOException, ParserConfigurationException, TransformerException {
        String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\smartprint\\printquery.xml";
        new RequestInterface().requestInterface(file);
    }
}
