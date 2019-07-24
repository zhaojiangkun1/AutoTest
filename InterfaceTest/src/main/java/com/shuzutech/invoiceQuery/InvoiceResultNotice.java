package com.shuzutech.invoiceQuery;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;

public class InvoiceResultNotice {
    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceQuery\\invoiceResultNotice.xml";

    @Test
    public void invoiceResultNotice() throws Exception{
        new RequestInterface().requestInterface(file, InterfaceName.TEST);

    }


}
