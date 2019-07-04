package com.shuzutech.goodsEncoding;


import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class GoodsEncodingOperator {


    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\goodsEncoding\\";
    /**
     * 自定义编码设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void settingEncoding() throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, ParseException, TransformerException {
        String file = "encodingsetting.xml";
        new RequestInterface().requestInterface(filePath+file);

    }

    /**
     * 自定义商品编码删除
     */
    @Test
    public void deleteEncoding() throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, ParseException, TransformerException {
        String file = "encodingdelete.xml";
        new RequestInterface().requestInterface(filePath+file);

    }
    /**
     * 商品编码默认值设置
     */
    @Test
    public void defaultEncoding() throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, ParseException, TransformerException {
        String file = "defaultencodingsetting.xml";
        new RequestInterface().requestInterface(filePath+file);

    }
    /**
     * 4.5.	获取商户开票商品列表
     */
    @Test
    public void getGoodsList() throws IOException, NoSuchAlgorithmException, ParserConfigurationException, SAXException, ParseException, TransformerException {
        String file = "getgoodslist.xml";
        new RequestInterface().requestInterface(filePath+file);
    }
}
