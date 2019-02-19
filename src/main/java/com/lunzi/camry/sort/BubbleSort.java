package com.lunzi.camry.sort;


/**
 * 冒泡排序
 * 时间复杂度:平均 O(n2) 最好O(n) 最坏(n2)
 * 空间复杂度:O(1)
 * Created by lunzi on 2019/2/18 4:35 PM
 */
public class BubbleSort implements SortMethod{

    @Override
    public int[] sort(int[] sortArray) {
        for(int i=0;i<sortArray.length;i++){
            for(int j=0;j<sortArray.length-i-1;j++){
                //交换两个元素
                if(sortArray[j]>sortArray[j+1]){
                    int temp=sortArray[j+1];
                    sortArray[j+1]=sortArray[j];
                    sortArray[j]=temp;
                }
            }
        }
        return sortArray;
    }


}
