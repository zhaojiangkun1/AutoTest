package com.shuzutech.bean;

import lombok.Data;

import java.util.Date;

@Data
public class SaveToken {
    private int id;
    private Date currentTime;
    private String accessToken;
}
