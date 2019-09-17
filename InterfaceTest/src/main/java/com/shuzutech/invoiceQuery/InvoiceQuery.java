package com.shuzutech.invoiceQuery;


import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;


public class InvoiceQuery {
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceQuery\\";

    @Test()
    public void queryInvoice() throws Exception {
        new RequestInterface().requestInterface(filePath+"query.xml", InterfaceName.PRO);
    }

    /**
     * 发票明细查询
     * @throws Exception
     */

    @Test
    public void fpdzcx() throws Exception {
        new RequestInterface().requestInterface(filePath+"fpdzcx.xml",InterfaceName.PRO);
    }

}
