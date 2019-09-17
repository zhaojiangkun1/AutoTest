package com.shuzutech.goodsEncoding;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;

public class CXSBZT {

    /**
     * 查询设备状态
     * @throws Exception
     */
    @Test
    public void cxsbzt() throws Exception {
        String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\goodsEncoding\\";
        new RequestInterface().requestInterface(filePath+"cxsbzt.xml", InterfaceName.PRO);
    }

}
