package com.syriana.sso.oidc.api.bo.ouath2;

import lombok.Data;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BlindBoxTest {

    static int simulatorTotalNum = 100000;
    static int[] typeXInBoxNumTimes = new int[12];// wantsType在12个位置中出现的次数
    static int wantsType = 5;// 期望的第几号玩具
//    static int[][] notIn = {{10,7,2}, {11,2,7},{5,12,7},{7,9,8},
//                            {9,10,4},{3,12,4},{4,1,6},{4,12,7},
//                            {1,2,9},{12,11,6},{4,11,12},{12,5,4}}; // 盒子的提示，INDEX是第几个盒子的位置
    static int[][] notIn = {{5,1,11}, {6,5,8},{1,11,6},{10,8,6},
                            {12,8,11},{11,4,7},{12,8,2},{11,1,6},
                            {11,12,9},{7,8,6},{8,11,9},{10,4,8}}; // 盒子的提示，INDEX是第几个盒子的位置
    static List<List<Integer>> convertNotIn = new ArrayList<>(12);


    public static void main(String[] args) {
        // 轉化
        for(int i = 0; i < notIn.length; i++){
            int[] ints = notIn[i];
            List<Integer> array = new ArrayList<>();
            for(int j = 0; j < ints.length; j++){
                array.add(ints[j]);
            }
            convertNotIn.add(array);
        }
        // 測試
        int simulatorTime = 0;
        // 模拟总次数，每个循环就模拟出12个盒子的分布。
        while(simulatorTime < simulatorTotalNum){
            int index = 0;
            int retryTimes = 0;
            Set<Integer> hasCreated = new LinkedHashSet<>(12);// 12个盒子
            do{ // 因为某些情况下线性塞入的，最后判断的可能不满足NOT IN死循环
                if(retryTimes > 10000){// 重试次数死循环过多，这次生成就作废了
                    break;
                }
                Set<Integer> notInSet = new HashSet<>(convertNotIn.get(index));
                int typeNum = ThreadLocalRandom.current().nextInt(12) + 1;// 随机数，1-12
                if(notInSet.contains(typeNum)|| hasCreated.contains(typeNum)){// 如果生成的数在提示里  或者  已经被生成过了
                    retryTimes++;
                    continue;
                }
                hasCreated.add(typeNum);// 一个有效的数模拟
                if(typeNum == wantsType){ // 统计是期望N号玩具所在位置
                    typeXInBoxNumTimes[index]++;
                }
                index++;
            }while(hasCreated.size() < 12);// 当生成了12个不同号玩具才满足
            if(hasCreated.size() == 12){// 当生成了12个不同号玩具才满足
                Integer[] boxNumList = hasCreated.toArray(new Integer[12]);
                if(simulatorTime % 10 == 0){
                    System.out.println(Arrays.toString(boxNumList));// 间隔打印验证
                }
                simulatorTime++;
            }
        }
        System.out.println("=====>>各次數" + Arrays.toString(typeXInBoxNumTimes));// 打印统计次数
    }

    @Data
    static class BlindBox {
        private Integer[] box1To12Array;
    }
}
