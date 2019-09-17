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

public class GoodsEncodingOperator {


    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\goodsEncoding\\";
    /**
     * 自定义编码设置
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void settingEncoding() throws Exception {
        String file = "encodingsetting.xml";
        new RequestInterface().requestInterface(filePath+file, InterfaceName.TEST);

    }

    /**
     * 自定义商品编码删除
     */
    @Test
    public void deleteEncoding() throws Exception {
        String file = "encodingdelete.xml";
        new RequestInterface().requestInterface(filePath+file,InterfaceName.TEST);

    }
    /**
     * 商品编码默认值设置
     */
    @Test
    public void defaultEncoding() throws Exception {
        String file = "defaultencodingsetting.xml";
        new RequestInterface().requestInterface(filePath+file,InterfaceName.TEST);

    }
    /**
     * 4.5.	获取商户开票商品列表
     */
    @Test
    public void getGoodsList() throws Exception {
        String file = "getgoodslist.xml";
        new RequestInterface().requestInterface(filePath+file,InterfaceName.PRO);
    }
    @Test
    public void getSpbm() throws Exception{
        String file = "getspbm.xml";
        new RequestInterface().requestInterface(filePath+file,InterfaceName.TEST);
    }
}
