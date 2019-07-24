package com.shuzutech.cases;

import com.shuzutech.config.InterfaceNum;
import com.shuzutech.sms.GetSendResult;
import com.shuzutech.sms.SmsInterface;
import org.testng.annotations.Test;

public class QuerySendResultCase {

    @Test
    public void querySendSmsResult() throws Exception {
       String sendResult = GetSendResult.getSendResult();
       String result = SmsInterface.sendSmsInterface(sendResult, InterfaceNum.GETRESULT);
       System.out.println(sendResult);
        System.out.println(result);
    }


}
