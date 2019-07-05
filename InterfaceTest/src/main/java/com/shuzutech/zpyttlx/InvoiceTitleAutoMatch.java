package com.shuzutech.zpyttlx;

import com.shuzutech.config.WithParamRequestInteface;
import org.testng.annotations.Test;
import java.util.Locale;
import java.util.ResourceBundle;

public class InvoiceTitleAutoMatch {

    String filePath = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\zpyttlx";
    ResourceBundle bundle = ResourceBundle.getBundle("zpyttlx.config", Locale.CHINA);

    /**
     * 智能赋码
     * @throws Exception
     */
    @Test
    public void smartGetSSBM() throws Exception {
        String getUrl = bundle.getString("test.url");
        String uri = bundle.getString("uri");
        String url = getUrl+uri;
        String file = "smartgetssbm.json";
        new WithParamRequestInteface().requestInterface(url,filePath+file);

    }

    /**
     * 云抬头
     * @throws Exception
     */

    @Test
    public void smartGetTitleInfo() throws Exception {
        String getUrl = bundle.getString("test.url");
        String uri = bundle.getString("yun.uri");
        String url = getUrl+uri;
        String file = "smartgettitleinfo.json";
        new WithParamRequestInteface().requestInterface(url,filePath+file);

    }

}
