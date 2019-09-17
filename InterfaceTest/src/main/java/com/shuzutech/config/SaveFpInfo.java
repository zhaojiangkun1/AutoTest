package com.shuzutech.config;

import lombok.Data;

import java.util.Date;

@Data
public class SaveFpInfo {
    private int id;
    private String fpqqlsh;
    private String fplxdm;
    private String nsrsbh;
    private Date updateTime;
    private String env;
}
