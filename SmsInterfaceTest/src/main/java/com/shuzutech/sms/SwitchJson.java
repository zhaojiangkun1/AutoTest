package com.shuzutech.sms;

import com.alibaba.fastjson.JSONObject;
import com.shuzutech.bean.SmsList;
import com.shuzutech.config.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class SwitchJson {

    public static String switchJson(String caseName,int id) throws IOException {

        SqlSession session = DataBaseUtil.getSqlSession();
        SmsList smsList = session.selectOne(caseName, id);

//        JSONObject jsonObject = JSON.parseObject("");  //解析一个json格式字符串

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("enterpriseId",smsList.getEnterpriseId());
        jsonObject.put("mobiles",smsList.getMobiles());
        jsonObject.put("templateId",smsList.getTemplateId());
        jsonObject.put("placeHolderContent",smsList.getPlaceHolderContent());

        System.out.println(jsonObject);
        return jsonObject.toString();

    }
}
