package com.lunzi.camry.leecode;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by lunzi on 2019/4/19 9:24 PM
 */
public class LongStre {
    public static int lengthOfLongestSubstring(String s) {
       return getMax(0,s.length()-1,s);
    }

    private static int getMax(int left, int right,String str) {
        if(left>right){
            return 0;
        }
        int repeatIndex=0;
        boolean flag=false;
        for(int i=left;i<=right;i++){
            if(flag){
                break;
            }
            //找到第一次重复的点
            for(int j=i+1;j<=right;j++){
                if(str.charAt(i)==str.charAt(j)){
                    repeatIndex=j;
                    flag=true;
                    break;
                }
            }
        }
        if(repeatIndex==0){
            //没有重复的点
            return str.substring(left,right+1).length();
        }
        int leftMax=getMax(left,repeatIndex-1,str);
        int rightMax=getMax(repeatIndex,right,str);
        return leftMax>rightMax?leftMax:rightMax;
    }

    public static void main(String[] args) {


    }


}
