package com.shuzutech.bean;

import lombok.Data;

@Data
public class SmsList {
    int id;
    String enterpriseId;
    String mobiles;
    String templateId;
    String placeHolderContent;
    String environment;

    @Override
    public String toString() {
        return (
                "{" +
                        "enterpriseId:"+enterpriseId+","+
                        "mobiles:"+mobiles+","+
                        "templateId:"+templateId+","+
                        "placeHolderContent:"+placeHolderContent+
                        "}"
                );
    }
}
