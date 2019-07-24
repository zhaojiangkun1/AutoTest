package com.shuzutech.merchants;


import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;


public class MerchantPush {
    //设置日期格式

    /**
     * 商户信息推送
     * dispatch:
     * 1：按配置的分派策略分配商户到子服务商；如果是1根据省份去找channelID。
     * 0：不按策略分配。默认为0 则channelID和业务员手机号必须填一个。
     *如果业务员手机号错误，直接抛错，不会再去查找channelID
     *
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\merchants\\MerchantInformationPush.xml";
    @Test
    public void merchantPush() throws Exception {
        new RequestInterface().requestInterface(file,InterfaceName.SHANGHU);
    }

    @Test
    public void shanghuQuery() throws Exception{
        String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\merchants\\shanghuInfoQuery.xml";
        new RequestInterface().requestInterface(file,InterfaceName.SHANGHU);
    }
}
