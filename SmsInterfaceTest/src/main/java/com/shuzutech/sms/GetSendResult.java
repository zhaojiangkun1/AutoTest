package com.shuzutech.sms;

import com.alibaba.fastjson.JSONObject;
import com.shuzutech.bean.PageModel;
import com.shuzutech.bean.SendResult;
import com.shuzutech.config.DataBaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetSendResult {

    public static String getSendResult() throws IOException {
        JSONObject js = new JSONObject();
        System.out.println("taskId:"+getResultTaskId());
        js.put("taskId",getResultTaskId());
        System.out.println("PageModel:"+getPageModel());
        js.put("pageModel",getPageModel());

        String getResult = js.toString();
        return getResult;
    }


    /**
     * 从数据库中获取TaskId
     * @return
     * @throws IOException
     */
    public static String getResultTaskId() throws IOException {

        SqlSession session = DataBaseUtil.getSqlSession();

        SendResult sendResult = session.selectOne("getTaskId");
        String taskId = sendResult.getTaskId();
        return taskId;

    }

    public static JSONObject getPageModel() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        PageModel pageModel = session.selectOne("getPageModel",1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageNum",pageModel.getPageNum());
        jsonObject.put("pageSize",pageModel.getPageSize());
        return jsonObject;
    }

    @Test
    public void test1() throws IOException {
        System.out.println(GetSendResult.getResultTaskId());
        System.out.println(getPageModel());
        System.out.println(GetSendResult.getSendResult());
    }

}
