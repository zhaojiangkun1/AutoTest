package com.shuzutech.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class GetBundleResource {

    public String getBundleResource(){
        ResourceBundle bundle = ResourceBundle.getBundle("config.application", Locale.CHINA);
        String getUrl = bundle.getString("test.url");
        String uri = bundle.getString("post.uri");
        String url = getUrl+uri;
        return url;
    }


}
