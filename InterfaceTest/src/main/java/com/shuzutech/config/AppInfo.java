package com.shuzutech.config;

import lombok.Data;

import java.util.Date;

@Data
public class AppInfo {
    private int id;
    private String appId;
    private String appSecret;
    private String accessToken;
    private Date updateTime;
    private String env;
    private String address;
}
