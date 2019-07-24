package com.shuzutech.cases;


import com.shuzutech.bean.AddSendResult;
import com.shuzutech.config.InterfaceNum;
import com.shuzutech.sms.SmsInterface;
import com.shuzutech.sms.SwitchJson;

import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SendSms {

    /**
     * (invocationCount = 10,threadPoolSize = 3)
     * 3个线程池，共被调用10次
     * 短信消息推送接口
     *
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test(groups = {"发短信"})
    public void sendSms() throws Exception {
        String body = SwitchJson.switchJson("selectcase",2);
        String result = SmsInterface.
                sendSmsInterface(body,InterfaceNum.PRO);
        System.out.println("本次用例执行结果为："+result);
        AddSendResult.addSendResult(result);
    }

    @Test
    public void sendSms1() throws Exception{
        String body = SwitchJson.switchJson("selectcase",1);
        String result = SmsInterface.sendSmsInterface(body,InterfaceNum.PRO);
        System.out.println(result);
    }

}
