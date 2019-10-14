package com.shuzutech.invoice;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ZhuanPiaoHongChong {
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoice\\";

    /**
     *当天的专票不能被红冲，当天的专票可作废，只测两种接口：红色信息表上传和红色信息表下载
     * 红冲隔天的专票
     */

    /**
     * 红字信息表上传
     * @throws Exception
     */

    @Test
    public void hzxxbsc() throws Exception {
        String fileName = "hzxxbsc.xml";
        new RequestInterface().requestInterface(filePath+fileName,InterfaceName.PRO);
    }

    @Test
    public void zhuanPiaoHongChong() throws Exception {
        String fileName = "fushuzhuanpiao.xml";
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
        Assert.assertEquals(code,0);
    }


    /**
     * 专票红冲，目前调用红字信息表下载和红字信息表上传
     * 红字信息表下载
     * @throws Exception
     */

    @Test
    public void hsxxbxz() throws Exception {
        String fileName = "hsxxbxz.xml";
        new RequestInterface().requestInterface(filePath+fileName, InterfaceName.PRO);
    }





}
