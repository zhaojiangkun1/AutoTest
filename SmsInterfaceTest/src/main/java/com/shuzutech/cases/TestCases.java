package com.shuzutech.cases;

import com.shuzutech.bean.SaveToken;
import com.shuzutech.config.DataBaseUtil;
import com.shuzutech.config.Md5;

import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCases {

    @Test
    public void getEncoder() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String body = "{\n" +
                "  \"enterpriseId\": \"124425925\",\n" +
                "  \"mobiles\": \"13701476279\",\n" +
                "  \"templateId\": \"1415\",\n" +
                "  \"placeHolderContent\":\"{[placeholder:remark]}##\\\"云票测试六十八\\\"|@|{[placeholder:amount]}##63.6|@|{[placeholder:url]}##https://fp.bwjf.cn/kB1IEGIpUK\"\n" +
                "}";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sd.format(new Date());

        System.out.println(body);
        String accessToken = "4ee057c6d1f7c1571895388427fe1947";

        System.out.println(date);
        System.out.println("加密后文件为："+ Md5.EncoderByMd5(body+date+accessToken));

    }

    @Test
    public void test1() throws IOException {
        SqlSession session = DataBaseUtil.getSqlSession();
        SaveToken saveToken = session.selectOne("getToken",1);
        System.out.println(saveToken.getCurrentTime());
    }

}
