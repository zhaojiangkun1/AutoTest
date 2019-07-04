package com.shuzutech.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    public static String readFile(String fileName) throws IOException {
        String s;
        String body = "";
        File file = new File(fileName);
        while (!file.exists()){
            System.out.println("文件不存在，请确认文件路径是否正确！！！");
            break;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while ((s=bufferedReader.readLine())!=null){
            body = body + s;
        }
        bufferedReader.close();
        System.out.println("请求报文:"+body);
        return body;
    }

}
