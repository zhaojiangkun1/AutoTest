package com.shuzutech.invoiceInvalid;


import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;


public class InvalidInvoice {

    /**
     * 发票作废：专票、普票、卷票  (电子票不能作废)
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invalidInvoice\\";
    @Test
    public void invalidInvoice() throws Exception {
        new RequestInterface().requestInterface(filePath+"invalidInvoice.xml", InterfaceName.TEST);
    }
}
