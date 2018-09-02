package com.lunzi.camry.leecode;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by lunzi on 2018/7/1 下午8:33
 */
public class Solution {
    public int[][] transpose(int[][] A) {
        int [][] B=new int[A.length][A.length];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A.length;j++){
                B[i][j]=A[j][j];
            }
        }
        return B;
    }

}
