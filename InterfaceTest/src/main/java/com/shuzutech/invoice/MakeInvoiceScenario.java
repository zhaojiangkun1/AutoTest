package com.shuzutech.invoice;

import com.shuzutech.config.InterfaceName;
import com.shuzutech.config.RequestInterface;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MakeInvoiceScenario {

    //总部：110101201707010043~~A20016420000265   110101201707010057~~499000152456  110101201707010037~~499000152093
    // 许继或者广东：554433221100001~~499111004288
    //宁波：500102010001459  500102010001459~~499000115698   500102010000097~~539900210230
    //四川：500102010003543~~500102010003543
    //91320191MA1ML4CL25////91320191MA1ML4CL25~~005056C00001  110101201707010037~~A10016420000196

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
        int code = mi.makeInvoice(file, InterfaceName.DEV);
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
        int code = mi.makeInvoice(file,InterfaceName.DEV);
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
        int code = mi.makeInvoice(file,InterfaceName.DEV);
        Assert.assertEquals(code,0);
    }

    /**
     * 税率为0.13
     * @throws Exception
     */

    @Test(priority = 2,groups = {"正常税率开票"} )
    public void onlyJeAndSl() throws Exception {
        UpdateGroup.updateGroup(file,"1","618.58407071","618.58","0.13","80.42");
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file,InterfaceName.PRO);
        Assert.assertEquals(code,0);
    }

    /**
     * 带有清单标志的电子票
     * @throws Exception
     */

    @Test(groups = {"带有清单标志的电子票"})
    public void qdbzDianPiao() throws Exception {
        String file = filePath+"qdbzdianpiao.xml";
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        int code = mi.makeInvoice(file,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 开折扣行的电子票
     * @throws Exception
     */

    @Test(priority = 2,groups = {"折扣行开票"})
    public void disCount() throws Exception {
        String fileDis = "discount.xml";
        String file = filePath + fileDis;
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = mi.makeInvoice(file,InterfaceName.PRO);
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
        int code = mi.makeInvoice(file,InterfaceName.PRO);
        Assert.assertEquals(code,99);
    }

    @Test(groups = {"自助开票"})
    public void ziZhuInvoice() throws Exception {
        //总部：110101201707010043~~A20016420000265   110101201707010057~~499000152456  110101201707010037~~499000152093
        // 许继或者广东：554433221100001~~499111004288   500102010000096~~539900210221  500102010000097~~539900210230
        //宁波：500102010001459  500102010001459~~499000115698
        //四川：500102010003543~~500102010003543
        String newShnsrsbh="110101201707010057";
        String newJsbh = "110101201707010057~~499000152456";
        String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoice\\zizhukaipiao.xml";
        new UpdateInvoiceBody().updateMerchantsInfo(file,newShnsrsbh,newJsbh);
        int code = mi.makeInvoice(file,InterfaceName.PRO);
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
//        UpdateGroup.updateGroup(filePath+fileName,"-1","86","-86","0","0");
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
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
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.DEV);
        Assert.assertEquals(code,0);
    }

    @Test(groups = {"多行卷票"})
    public void makeDuohangJuanPiao() throws Exception {
        String fileName = "maxjuanpiao.xml";
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.DEV);
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
        new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
    }

    /**
     * 普票折扣行
     * @throws Exception
     */
    @Test
    public void discountPuInvoice() throws Exception {
        String fileName = "discountPuInvoice.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 10个商品多行普票
     * @throws Exception
     */
    @Test
    public void duohangPupiao() throws Exception {
        String fileName = "duohangPupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.DEV);
        Assert.assertEquals(code,0);
    }

    /**
     * 超过8行，带有清单标志的普票
     * @throws Exception
     */

    @Test
    public void qdbzPupiao() throws Exception {
        String fileName = "qingdanPupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 普票5%税率，按照5%简易征收
     */

    @Test
    public void yhzcbsPupiao() throws Exception {
        String fileName = "pupiaoyhzc.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 零税率普票
     */

    @Test
    public void lslPupiao() throws Exception {
        String fileName = "0slpupiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 普票冲红
     * @throws Exception
     */
    @Test
    public void puInvoiceChongHong() throws Exception {
        String fileName = "pupiaochonghong.xml";
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
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
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.PRO);
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
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.DEV);
        Assert.assertEquals(code,0);
    }
    @Test
    public void twoZhuanPiao() throws Exception {
        String fileName = "twospzhuanpiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    @Test
    public void oneDiscountZhuanPiao() throws Exception {
        String fileName = "onespdiscountzhuanpiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        Assert.assertEquals(code,0);
    }

    /**
     * 专票0税率  0slzhuanpiao.xml  专票不支持0税率
     */
    @Test(priority = 3,groups = {"专票"})
    public void lslZhuanInvoice() throws Exception {
        String fileName = "0slzhuanpiao.xml";
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        int code = new MakeInvoiceInterface().makeInvoice(filePath+fileName,InterfaceName.TEST);
        System.out.println(code);
        Assert.assertEquals(code,0);
    }
}
