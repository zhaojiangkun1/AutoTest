package com.shuzu.problem;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SortNumber {

    /**
     * 有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     * 程序分析：可填在百位、十位、个位的数字都是1、2、3、4。组成所有的排列后再去 掉不满足条件的排列。
     */
    @Test
    public void sortNumber(){
        /**
         * 定义一个List，用来存放所有可能，然后再去掉数字相同的三位数
         */
        int num;
        for(int i=1;i<5;i++){
            for (int j = 1;j<5;j++){
                for (int n = 1; n<5;n++){
                    if (i != j && i != n && j != n){
                        num = i*100+j*10+n;
                        System.out.println(num);
                    }
                }
            }
        }

    }


}
