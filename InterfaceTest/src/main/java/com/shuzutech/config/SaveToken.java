package com.shuzutech.config;

import lombok.Data;

import java.util.Date;


@Data
public class SaveToken {
    private int id;
    private String accessToken;
    private Date currentTime;
}
