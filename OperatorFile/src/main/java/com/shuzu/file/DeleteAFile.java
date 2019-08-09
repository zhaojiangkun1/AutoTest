package com.shuzu.file;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteAFile {

    public static void writerAFile(String fileName,List list) throws IOException {
//        File file = new File(fileName);
//        OutputStream outputStream = new FileOutputStream(file);
//        Iterator iterator = list.iterator();
//        String s = "";
//        while (iterator.hasNext()){
//            s = s + iterator.next().toString() + "\r\n";
//        }
//        outputStream.write(s.getBytes());
//        outputStream.close();
        PrintWriter out = new PrintWriter(fileName,"UTF-8");
        Iterator iterator = list.iterator();
        String s = "";
        while (iterator.hasNext()){
//            s = s + iterator.next().toString() + "\r\n";
            s = iterator.next().toString();
            out.append(s+"\r\n");
        }
        out.close();
    }

    @Test
    public void deleteAFile() throws IOException {
        String filePath = "D:\\IdeaProjects\\AutoTest\\OperatorFile\\src\\main\\resources\\";

        List<String> alist = getFileContent(filePath+"aaaa.txt");
        List<String> blist = getFileContent(filePath+"bbbb.txt");
        List<String> clist = new ArrayList<String>();

        String string;
        String a;
        boolean b;
        Iterator iterator = blist.iterator();
        while (iterator.hasNext()){
            string = (String) iterator.next();
            for (int i=0;i<alist.size();i++){
                a = alist.get(i);
                b = (a.equals(string));
                if (b){
                    clist.add(a);
                    System.out.println(a);
                }
            }
        }
        writerAFile(filePath+"aaaa.txt",clist);
        List<String> aNewlist = getFileContent(filePath+"aaaa.txt");
        System.out.println("删除A中存在但是B中不存在的数据：");
        for (int i=0;i<aNewlist.size();i++){
            System.out.println(aNewlist.get(i));
        }
    }

    public static List getFileContent(String fileName) throws IOException {
        List<String> list = new ArrayList<String>();
        File file = new File(fileName);
        while (!file.exists()){
            System.out.println("该文件路径不存在");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = "";
        while ((string=bufferedReader.readLine())!=null){
            list.add(string);
        }
        return list;
    }

}
