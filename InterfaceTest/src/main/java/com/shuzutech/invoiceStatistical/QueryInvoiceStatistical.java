package com.shuzutech.invoiceStatistical;

import com.shuzutech.config.RequestInterface;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

public class QueryInvoiceStatistical {
    //设置日期格式
    String file = "D:\\IdeaProjects\\AutoTest\\InterfaceTest\\src\\main\\resources\\invoiceStatistical\\QueryInvoiceStatistics.xml";

    /**
     * 发票查询统计接口
     * 查询当前税控设备中的发票统计信息，即税控设备中自动计算的领用存信息。
     * 兼容税控服务器
     * @throws IOException
     * @throws NoSuchAlgorithmException
     *
     */
    //统计电子票开票统计
    @Test(groups = {"统计电子票"})
    public void statisicalEleInvoice() throws Exception {
        UpdateFplxdm(file,"026");
        new RequestInterface().requestInterface(file);
    }
    //查找专票统计
    @Test(groups = {"统计专票"})
    public void statisicalZhuanInvoice() throws Exception{
        UpdateFplxdm(file,"004");
        new RequestInterface().requestInterface(file);
    }

    @Test(groups = {"统计卷票"})
    public void statisicalJuanInvoice() throws Exception {
        UpdateFplxdm(file,"025");
        new RequestInterface().requestInterface(file);
    }

    @Test(groups = {"统计普票"})
    public void statisicalPuInvoice() throws Exception {
        UpdateFplxdm(file,"007");
        new RequestInterface().requestInterface(file);
    }

    public void UpdateFplxdm(String fileName,String fplxdm) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = newDocumentBuilder.parse(fileName);
        Element root = doc.getDocumentElement();
        //查找指定节点
        //通过根节点获得子结点
        NodeList inputList = root.getElementsByTagName("input");
        //获取节点
        Node item = inputList.item(0);
//        System.out.println("item:"+item);//获取input节点下内容
        Element inputElement = (Element) item;
        NodeList fplxdmList = inputElement.getElementsByTagName("fplxdm");
//        System.out.println("当前fplxdm:"+fplxdmList.item(0).getTextContent());
        //修改数据
        fplxdmList.item(0).setTextContent(fplxdm);
        //加载到文件中
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        Source source = new DOMSource(doc);
        Result result = new StreamResult(fileName);
        transformer.transform(source,result);
    }
}
