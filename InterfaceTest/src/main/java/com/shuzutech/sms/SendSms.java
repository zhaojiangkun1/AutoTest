package com.shuzutech.sms;


import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class SendSms {


    SmsInterface smsInterface = new SmsInterface();

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
        String fileName = "sendsms.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms1() throws Exception {
        String fileName = "sendsms1.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test(groups = {"发短信"})
    public void sendSms2() throws Exception {
        String fileName = "sendsms2.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms3() throws Exception {
        String fileName = "sendsms3.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms4() throws Exception {
        String fileName = "sendsms4.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms5() throws Exception {
        String fileName = "sendsms5.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms6() throws Exception {
        String fileName = "sendsms6.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test(groups = {"发短信"})
    public void sendSms7() throws Exception {
        String fileName = "sendsms7.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test(groups = {"发短信"})
    public void sendSms8() throws Exception {
        String fileName = "sendsms8.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
    @Test(groups = {"发短信"})
    public void sendSms9() throws Exception {
        String fileName = "sendsms9.json";
        smsInterface.sendSmsInterface(fileName,"729");
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

    @Test
    public void getSendResult() throws Exception {
        String fileName = "getSendResult.json";
        smsInterface.getSendResultInterface(fileName);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }

}
