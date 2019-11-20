package com.shuzutech.config;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class GetAppInfo {

    public static AppInfo getAppInfo(InterfaceName name) throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        AppInfo appInfo = new AppInfo();
        if (name == InterfaceName.DEV || name == InterfaceName.SHANGHUDEV){
            appInfo = session.selectOne("getAppInfo",3);
        }
        if (name == InterfaceName.TEST || name == InterfaceName.SHANGHUTEST){
            appInfo = session.selectOne("getAppInfo",2);
        }
        if (name == InterfaceName.PRO || name == InterfaceName.SHANGHUPRO){
            appInfo = session.selectOne("getAppInfo",1);
        }
        if (name == InterfaceName.DEV1){
            appInfo = session.selectOne("getAppInfo",6);
        }
        return appInfo;
    }

    public static void updateAppInfo(InterfaceName name,AppInfo appInfo) throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        if (name == InterfaceName.SHANGHUDEV || name == InterfaceName.DEV){
            appInfo.setId(3);
            session.update("updateAppInfo",appInfo);
        }
        if (name == InterfaceName.SHANGHUTEST || name == InterfaceName.TEST){
            appInfo.setId(2);
            session.update("updateAppInfo",appInfo);
        }
        if (name == InterfaceName.PRO || name == InterfaceName.SHANGHUPRO){
            appInfo.setId(1);
            session.update("updateAppInfo",appInfo);
        }
        if (name == InterfaceName.DEV1){
            appInfo.setId(6);
            session.update("updateAppInfo",appInfo);
        }
        session.commit();
    }
}
