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
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sd.format(new Date());

    @Test
    public void getEncoder() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String body = "{\"mobiles\":\"13701476279\",\"enterpriseId\":\"124425925\",\"placeHolderContent\":\"{[placeholder:remark]}##\\\"云票测试六十八\\\"|@|{[placeholder:amount]}##63.6|@|{[placeholder:url]}##https://fp.bwjf.cn/kB1IEGIpUK\",\"templateId\":\"1415\"}";


        System.out.println(body);
        String accessToken = "65c95edcaa86c7ddaa6d139cee570848";

        System.out.println(date);
        System.out.println("加密后文件为："+ Md5.EncoderByMd5(body+date+accessToken));

    }

    @Test
    public void test1() throws IOException, NoSuchAlgorithmException {
        String body = "{\"pageModel\":{\"pageSize\":3,\"pageNum\":2},\"taskId\":\"1563981403971000001780628006\"}";
        String token = "0e82fbfcaaae7e918accc6eb40afb5c0";
        System.out.println(body);
        System.out.println(date);
        System.out.println("加密后文件为："+Md5.EncoderByMd5(body+date+token));
    }

}
