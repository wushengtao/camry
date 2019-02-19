package com.lunzi.camry.sort;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * Created by lunzi on 2019/2/18 4:37 PM
 */
@Slf4j
public class TestSort {
    private static Gson gson=new Gson();
    private int [] sortArray;
    private TestSort(int [] sortArray){
        this.sortArray=sortArray;
        gson.toJson(sortArray);
    }

    private static int [] sort(int [] sortArray, Supplier<SortMethod> sortMethodSupplier){
        SortMethod sortMethod=sortMethodSupplier.get();
        sortMethod.sort(sortArray);
        log.info(sortMethod.getClass().getSimpleName()+gson.toJson(sortArray));
        return sortArray;
    }

    public static void main(String[] args) {
        int [] nums=GenNumUtil.genNums(20,0,100);
        TestSort.sort(nums,SelectSort::new);//选择排序
        TestSort.sort(nums,BubbleSort::new);//冒泡排序
        TestSort.sort(nums,QuickSort::new);//快速排序
    }
}
