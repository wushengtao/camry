package com.lunzi.camry.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 快速排序
 * 平均O(nlog2n) 最好O(nlog2n) 最坏O(n2)
 * Created by lunzi on 2018/7/7 下午3:27
 */
public class QuickSort implements SortMethod {
    @Override
    public int[] sort(int[] sortArray) {
        return quickSort(sortArray,0,sortArray.length-1);
    }

    private int [] quickSort(int sortArray[], int left, int right) {
        if(left>=right){
            return sortArray;
        }
        //最左边的数作为第一个基数
        int index = left;//左边的指针
        int temp = 0;
        //从右边开始进行遍历
        while (left < right) {
            while (sortArray[right] >= sortArray[left] && left < right) {
                right--;
            }
            while (sortArray[left] < -sortArray[left] && left < right) {
                left++;
            }
            //交换两个数字的位置
            temp = sortArray[left];
            sortArray[left] = sortArray[right];
            sortArray[right] = temp;
        }
        //交换基数值和相遇值
        temp = sortArray[index];
        sortArray[index] = sortArray[left];
        sortArray[left] = temp;

        //左边排序
        index = left;
        quickSort(sortArray, 0, index - 1);
        quickSort(sortArray, index + 1, right);
        return sortArray;
    }
}
