package com.shuzu.dataType;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;


public class BasicDataTypeCast {

    @Test
    public void basicDataTypeCast(){
        /**
         * 自动装箱和拆箱
         * 如果整型字面量的值在-128到127之间，不会new新的Integer对象
         * 而是直接引用常量池中的Integer对象
         */
        Integer f1 = 100,f2 = 100, f3 = 150,f4 = 150;
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);

        System.out.println(Math.round(10.6));
        System.out.println(Math.round(-10.7));
        System.out.println(2<<3);//左移3位，相当于2乘以2的三次方
        System.out.println(2>>3);//右移三位，相当于2除以2的三次方
        System.out.println(3<<3);//左移3位，相当于3*（2的三次方） 24
    }

    @Test
    public void readFile() throws IOException {
        String file = "D:\\IdeaProjects\\AutoTest\\OperatorFile\\src\\main\\resources\\aaaa.txt";
        Scanner in = new Scanner(Paths.get(file),"UTF-8");
        while (in.hasNext()){
            String f = in.next();
            System.out.println(f);
        }
        in.close();

        PrintWriter out = new PrintWriter(file,"UTF-8");
        out.append("李五");
        out.close();
    }

}
