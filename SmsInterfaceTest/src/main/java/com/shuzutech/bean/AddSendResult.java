package com.shuzutech.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuzutech.config.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class AddSendResult {

    public static void addSendResult(String result) throws IOException {
        JSONObject jsonObject = JSON.parseObject(result);
        String data = jsonObject.getString("data");
        JSONObject jsonObject1 = JSON.parseObject(data);
        String taskId = jsonObject1.getString("taskId");

        SqlSession session = DataBaseUtil.getSqlSession();
        SendResult sendResult = session.selectOne("getTaskId");
        int id = sendResult.getId();


        SendResult result1 = new SendResult();
        result1.setId(id+1);
        result1.setTaskId(taskId);

        session.insert("addTaskId",result1);
        session.commit();

    }

}
