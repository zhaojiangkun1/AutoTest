package com.shuzutech.invoiceQuery;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.Md5;
import com.shuzutech.config.RequestInterface;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoicePrintingQuery {
    /**
     * 发票打印查询
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\merchants\\";
    @Test
    public void invoicePrintingQuery() throws Exception {
      new RequestInterface().requestInterface(filePath+"MerchantInformationPush.xml", InterfaceName.TEST);
    }
}
