package com.shuzutech.bean;

import lombok.Data;

@Data
public class SendResult {
    private int id;
    private String taskId;
    private PageModel pageModel;
}
