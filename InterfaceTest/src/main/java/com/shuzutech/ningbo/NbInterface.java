package com.shuzutech.ningbo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class NbInterface {

    @Test
    public static void nbRequestInterface() throws IOException {
        String url = "https://paymgmt.shuzutech.com/api/invoice/passby?orgid=1304221336041001&isBoss=true&token=b56c3682d7893f67d0fa32a95530cf2f";
        System.out.println("请求的url:"+url);
        //发票使用统计接口
//        String xml = "xml=<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"FPSYTJ\"><body><input><jsbh>91320191MA1ML4CL25~~661826092245</jsbh></input></body></business>";
        //发票统计接口
//        String xml = "xml=<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"TJCX\"><body><input><jsbh>91320191MA1ML4CL25~~661826092245</jsbh><kprqStart>20190601</kprqStart><kprqEnd>20190906</kprqEnd></input></body></business>";
        //金税盘设备查询接口：
//        String body;
//        String xml = "xml=<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"CXSBXX\"><body><input><sh>91320191MA1ML4CL25</sh></input></body></business>";
        //当前未开票号码查询
//        String xml = "xml=<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"GPXXCX\"><body><input><jsbh>91320191MA1ML4CL25~~661826092245</jsbh><fplxdm>007</fplxdm><sblx>2</sblx><fpzt>1</fpzt><nsrsbh>91320191MA1ML4CL25</nsrsbh><qtzd>661826092245</qtzd><lgqxx></lgqxx></input></body></business>";
        //宁波的发票打印接口
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"PRINTDATA\"><body><input><jsbh>91320191MA1ML4CL25~~661826092245</jsbh><fplxdm>007</fplxdm><cxfs>0</cxfs><cxtj>032001800304~24283394</cxtj><left>25</left><top>40</top></input></body></business>";

        //企业信息查询接口
//        String xml = "xml=<?xml version=\"1.0\" encoding=\"utf-8\"?><business id=\"QYXXCX\"><body><input><jsbh>91320191MA1ML4CL25~~661826092245</jsbh></input></body></business>";

        System.out.println("请求的body:"+ xml);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(xml,"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/x-www-form-urlencoded");
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("  ");
        System.out.println("返回的报文："+result);

    }

}
