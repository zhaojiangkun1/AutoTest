package com.shuzutech.invoiceQuery;


import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;


public class InvoiceQuery {
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceQuery\\";

    @Test()
    public void queryInvoice() throws Exception {
        new RequestInterface().requestInterface(filePath+"query.xml");
    }

}
