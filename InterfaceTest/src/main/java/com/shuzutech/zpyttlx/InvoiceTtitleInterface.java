package com.shuzutech.zpyttlx;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;

public class InvoiceTtitleInterface {

    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\zpyttlx\\";

    /**
     * 企业头联想查询
     * @throws Exception
     */
    @Test
    public void invoiceTitleQuery() throws Exception{
        String fileName = "invoiceTitleQuery.xml";
        new RequestInterface().requestInterface(filePath+fileName, InterfaceName.TEST);
    }

    /**
     *用于智能获取税收编码
     * @throws Exception
     */
    @Test
    public void aiCode() throws Exception{
        String fileName = "aicode.xml";
        new RequestInterface().requestInterface(filePath+fileName,InterfaceName.TEST);
    }

}
