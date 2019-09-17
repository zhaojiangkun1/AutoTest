package com.shuzutech.ningbo;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.invoice.MakeInvoiceInterface;
import com.shuzutech.invoice.UpdateGroup;
import com.shuzutech.invoice.UpdateInvoiceBody;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NbMakeInvoice {

    String newShnsrsbh="91320191MA1ML4CL25";
    String newJsbh = "";
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoice\\";
    String fileName = "makeInvoice.xml";
    String file = filePath + fileName;
    MakeInvoiceInterface mi = new MakeInvoiceInterface();

    /**
     * 税率为0.13，宁波开票
     * @throws Exception
     */

    @Test
    public void nBMakeInvoice() throws Exception {
        UpdateGroup.updateGroup(file,"1","618.5840707","618.58","0.13","80.42");
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file, InterfaceName.PRO);
        Assert.assertEquals(code,0);
    }

    /**
     * 电子票冲红
     * @throws Exception
     */

    @Test
    public void makeFuShuInvoice() throws Exception {
        String fileName = "fushufapiao.xml";
        new UpdateInvoiceBody().updateMerchantsInfo(filePath+fileName,newShnsrsbh,newJsbh);
//        UpdateGroup.updateGroup(filePath+fileName,"-1","86","-86","0","0");
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
        Assert.assertEquals(code,0);
    }
}
