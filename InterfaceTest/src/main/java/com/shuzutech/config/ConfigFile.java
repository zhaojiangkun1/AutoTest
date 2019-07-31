package com.shuzutech.config;


import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("config.application", Locale.CHINA);
    public static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getUrl(InterfaceName name){
        String url="";
        String getUri=bundle.getString("getUri");


        if(name == InterfaceName.DEV || name == InterfaceName.SHANGHUDEV){
            String address = bundle.getString("devUrl");
            url = address + getUri;

        }

        if (name == InterfaceName.TEST || name == InterfaceName.SHANGHUTEST){
            String address = bundle.getString("testUrl");
            url = address + getUri;
        }

        if (name == InterfaceName.PRO || name == InterfaceName.SHANGHUPRO){
            String address = bundle.getString("proUrl");
            url = address + getUri;
        }

        return url;
    }

    public static String postUrl(InterfaceName name){
        String postUri = bundle.getString("postUri");
        String url="";
        if (name == InterfaceName.PRO){
            String address = bundle.getString("proUrl");
            url = address + postUri;
        }

        if (name == InterfaceName.TEST){
            String address = bundle.getString("testUrl");
            url = address + postUri;
        }

        if (name == InterfaceName.DEV){
            String address = bundle.getString("devUrl");
            url = address + postUri;
        }

        if (name == InterfaceName.SHANGHUTEST){
            String addr = bundle.getString("shanghuUrl");
            String uri = bundle.getString("shanghuUri");
            url = addr + uri;
        }

        if (name == InterfaceName.SHANGHUPRO){
            String addr = bundle.getString("proUrl");
            String uri = bundle.getString("shanghuUri");
            url = addr + uri;
        }
        if (name == InterfaceName.SHANGHUDEV){
            String addr = bundle.getString("shanghuDevUrl");
            String uri = bundle.getString("shanghuUri");
            url = addr + uri;
        }

        return url;
    }

    public static String getAppid(InterfaceName name){
        String appid="";
        if (name == InterfaceName.DEV || name == InterfaceName.SHANGHUDEV){
            appid = bundle.getString("devAppid");
        }
        if (name == InterfaceName.TEST){
            appid = bundle.getString("testAppid");
        }
        if (name == InterfaceName.PRO || name == InterfaceName.SHANGHUPRO){
            appid = bundle.getString("proAppid");
        }
        if (name == InterfaceName.SHANGHUTEST){
            appid = bundle.getString("testAppid");
        }
        return appid;
    }

    public static String getAppSecret(InterfaceName name){
        String appSecret = "";
        if (name == InterfaceName.DEV || name == InterfaceName.SHANGHUDEV){
            appSecret = bundle.getString("devAppSecret");
        }
        if (name == InterfaceName.PRO || name == InterfaceName.SHANGHUPRO){
            appSecret = bundle.getString("proAppSecret");
        }
        if (name == InterfaceName.TEST){
            appSecret = bundle.getString("testAppSecret");
        }
        if (name == InterfaceName.SHANGHUTEST){
            appSecret = bundle.getString("testAppSecret");
        }

        return appSecret;
    }

}
