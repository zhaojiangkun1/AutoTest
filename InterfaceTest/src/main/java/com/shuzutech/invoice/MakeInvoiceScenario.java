package com.shuzutech.invoice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MakeInvoiceScenario {

    String newShnsrsbh="110101201707010057";
    String newJsbh = "110101201707010057~~499000152456";
    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoice\\";
    String fileName = "makeInvoice.xml";
    String file = filePath + fileName;
    MakeInvoiceInterface mi = new MakeInvoiceInterface();

    /**
     * 开0税率的票
     * @throws Exception
     */

    @Test(priority = 1,groups = {"0税率开票"})
    public void useLslbs() throws Exception {
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        UpdateGroup.updateGroup(file,"1","86","86","0","0");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file);
        Assert.assertEquals(code,0);
    }


    /**
     * 正常税率开票0.06
     * @throws Exception
     */

    @Test(priority = 2,groups = {"正常税率开票"})
    public void normalRate1() throws Exception {
        UpdateGroup.updateGroup(file,"3.6","6.10","21.96","0.06","1.32");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file);
        Assert.assertEquals(code,0);

    }

    /**
     * 税率为0.16
     * @throws Exception
     */
    @Test(priority = 2,groups = {"正常税率开票"})
    public void normalRate2() throws Exception {
        UpdateGroup.updateGroup(file,"1","20","20","0.16","3.20");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file);
        Assert.assertEquals(code,0);
    }

    /**
     * 税率为0.13
     * @throws Exception
     */

    @Test(priority = 2,groups = {"正常税率开票"} )
    public void onlyJeAndSl() throws Exception {
        UpdateGroup.updateGroup(file,"","","21.96","0.13","2.85");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file);
        Assert.assertEquals(code,0);
    }

    /**
     * 开折扣行的电子票
     * @throws Exception
     */

    @Test(priority = 2,groups = {"折扣行开票"})
    public void disCount() throws Exception {
        String fileDis = "discount.xml";
        String fil = filePath + fileDis;
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(fil);
        Assert.assertEquals(code,0);
    }

    /**
     *
     * 税率在商品和税控盘中不存在
     *
     */
    @Test(priority = 0,groups = {"税率不存在"})
    public void rateIsNotExist() throws Exception {
        UpdateGroup.updateGroup(file,"","","21.96","0.19","4.17");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file);
        Assert.assertEquals(code,99);
    }

    @Test(groups = {"四川自助开票"})
    public void siChuanInvoice() throws Exception {
        String newShnsrsbh="500102010003543";
        String newJsbh = "500102010003543~~500102010003543";
        String fileName = "sichuan.xml";
        new UpdateInvoiceBody().updateMerchantsInfo(filePath+fileName,newShnsrsbh,newJsbh);
        int code = mi.makeInvoice(fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 电子发票红冲
     * @throws Exception
     */

    @Test(priority = 4,groups = {"电子发票冲红"})
    public void makeFuShuInvoice() throws Exception {
        String fileName = "fushufapiao.xml";
        new UpdateInvoiceBody().updateMerchantsInfo(filePath+fileName,newShnsrsbh,newJsbh);
        UpdateGroup.updateGroup(filePath+fileName,"-3.6","6.10","-21.96","0.06","-1.32");
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);

    }

    /**
     * 开卷票
     * @throws Exception
     */
    @Test(priority = 3,groups = {"卷票"})
    public void makeJuanInvoice() throws Exception {
        String fileName = "juanpiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 开普票
     * @throws Exception
     */

    @Test(priority = 3,groups = {"普票"})
    public void makePuInvoice() throws Exception {
        String fileName = "zengpu.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 普票折扣行
     * @throws Exception
     */
    @Test
    public void discountPuInvoice() throws Exception {
        String fileName = "discountPuInvoice.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 8个商品多行普票
     * @throws Exception
     */
    @Test
    public void duohangPupiao() throws Exception {
        String fileName = "duohangPupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 超过8行，带有清单标志
     * @throws Exception
     */

    @Test
    public void qdbzPupiao() throws Exception {
        String fileName = "qingdanPupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 普票5%税率，按照5%简易征收
     */

    @Test
    public void yhzcbsPupiao() throws Exception {
        String fileName = "pupiaoyhzc.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 零税率普票
     */

    @Test
    public void lslPupiao() throws Exception {
        String fileName = "0slpupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 普票冲红
     * @throws Exception
     */
    @Test
    public void puInvoiceChongHong() throws Exception {
        String fileName = "pupiaochonghong.xml";
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 开专票
     * @throws Exception
     */

    @Test(priority = 3,groups = {"专票"})
    public void makeZhuanInvoice() throws Exception {
        String fileName = "zengzhuan.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 专票最多开8行，不带清单
     * @throws Exception
     */

    @Test
    public void discountZhuanInvoice() throws Exception {
        String fileName = "discountZhuanPiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        Assert.assertEquals(code,0);
    }

    /**
     * 专票0税率  0slzhuanpiao.xml  专票不支持0税率
     */
    @Test(priority = 3,groups = {"专票"})
    public void lslZhuanInvoice() throws Exception {
        String fileName = "0slzhuanpiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName);
        System.out.println(code);
        Assert.assertEquals(code,0);
    }
}
