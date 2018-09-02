package com.lunzi.camry.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 快速排序
 * Created by lunzi on 2018/7/7 下午3:27
 */
public class QuickSort {
    //快速排序
    public static void quickSort(int low,int high,int [] nums){
        if(low>=high){
            return;
        }
        int left=low;
        int right=high;
        int index=left;
        int temp;
        //i和j相遇
        while(left<right){
            //哨兵在左边,从右边开始找
            while(nums[right]>=nums[index]&&right>left){
                right--;
            }

            //然后从左边开始找
            while(nums[left]<=nums[index]&&left<right){
                left++;
            }
            //交换一下位置
            temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
        }
        //交换相遇点和哨兵
        temp=nums[index];
        nums[index]=nums[left];
        nums[left]=temp;

        index=left;
        quickSort(low,index-1,nums);//左边
        quickSort(index+1,high,nums);//左边

    }
    public static void main(String args[]){
//        int [] array={3,4,5,7,9,1,2,2,2,4,6,7,1,2};
//        quickSort(0,array.length-1,array);
//        System.out.println(Arrays.toString(array));
        List<Integer> list= Lists.newArrayList(1,2,2,2,3,3,3);
        HashSet<Integer> hashSet= Sets.newHashSet(list);
        System.out.println(hashSet.toString());
    }
}
