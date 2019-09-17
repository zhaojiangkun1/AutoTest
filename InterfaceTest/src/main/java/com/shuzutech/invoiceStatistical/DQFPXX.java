package com.shuzutech.invoiceStatistical;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;

public class DQFPXX {

    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceStatistical\\weikaipiaonumber.xml";

    @Test()
    public void dqfpxx() throws Exception {
        new RequestInterface().requestInterface(file, InterfaceName.PRO);
    }

}
