package com.shuzutech.goodsEncoding;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class TerminalQuery {

    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\goodsEncoding\\";
    @Test
    public void termianlQuery() throws Exception {
       new RequestInterface().requestInterface(filePath+"terminalQuery.xml", InterfaceName.PRO);
    }
}
