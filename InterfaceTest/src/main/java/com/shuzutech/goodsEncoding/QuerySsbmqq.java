package com.shuzutech.goodsEncoding;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class QuerySsbmqq {
    /**
     * 查询税局商品编码
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */


    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\goodsEncoding\\";
    @Test
    public void SsbmqqQuery() throws Exception {
        new RequestInterface().requestInterface(filePath+"queryspbm.xml", InterfaceName.TEST);
//
//
    }
}
