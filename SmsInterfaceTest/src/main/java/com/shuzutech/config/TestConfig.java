package com.shuzutech.config;


import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestConfig {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getUrl(InterfaceNum name){
        String url = "";
        if (name == InterfaceNum.PRO){
            String addr = bundle.getString("proSendUrl");
            String uri = bundle.getString("proSendUri");
            url = addr + uri;
        }
        if (name == InterfaceNum.TEST){
            String addr = bundle.getString("testSendUrl");
            String uri = bundle.getString("testSendUri");
            url = addr + uri;
        }

        if (name == InterfaceNum.GETRESULT){
            String addr = bundle.getString("proSendUrl");
            String uri = bundle.getString("proResultUri");
            url = addr + uri;
        }
        if (name == InterfaceNum.PRONEWREQ){
            String addr = bundle.getString("proNewreq");
            String uri = bundle.getString("proSendUri");
            url = addr + uri;
        }
        if (name == InterfaceNum.NEWGETRESULT){
            String addr = bundle.getString("proNewreq");
            String uri = bundle.getString("proResultUri");
            url = addr + uri;
        }

        return url;
    }

    public static String getAccessTokenUrl(InterfaceNum name){
        String url = "";
        String uri = bundle.getString("getUri");
        if (name == InterfaceNum.TEST){
            String getUrl = bundle.getString("testAccessTokenUrl");
            url = getUrl + uri;
        }
        if (name == InterfaceNum.GETRESULT || name == InterfaceNum.NEWGETRESULT){
            String getUrl = bundle.getString("proGetUrl");
            url = getUrl + uri;
        }
        if (name == InterfaceNum.PRO || name == InterfaceNum.PRONEWREQ){
            String getUrl = bundle.getString("proGetUrl");
            url = getUrl + uri;
        }
        return url;
    }

    public static String getAppid(InterfaceNum name){
        String appId = "";
        if (name == InterfaceNum.TEST){
            appId = bundle.getString("testAppId");
        }

        if (name == InterfaceNum.PRO || name == InterfaceNum.PRONEWREQ){
            appId = bundle.getString("proAppId");
        }
        if (name == InterfaceNum.GETRESULT || name == InterfaceNum.NEWGETRESULT){
            appId = bundle.getString("proAppId");
        }

        return appId;
    }

    public static String getAppSecret(InterfaceNum name){
        String appSecret = "";
        if (name == InterfaceNum.PRO || name == InterfaceNum.PRONEWREQ){
            appSecret = bundle.getString("proappSecret");
        }
        if (name == InterfaceNum.TEST){
            appSecret = bundle.getString("testappSecret");
        }
        if (name == InterfaceNum.GETRESULT || name == InterfaceNum.NEWGETRESULT){
            appSecret = bundle.getString("proappSecret");
        }

        return appSecret;
    }

}
