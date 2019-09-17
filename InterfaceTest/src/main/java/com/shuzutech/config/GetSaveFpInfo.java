package com.shuzutech.config;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class GetSaveFpInfo {

    public static int getSaveFpInfo() throws IOException {
        SaveFpInfo saveFpInfo = new SaveFpInfo();
        SqlSession session = DataBaseUtil.getSqlSession();
        saveFpInfo=session.selectOne("queryfpinfo");
        return saveFpInfo.getId();
    }
}
