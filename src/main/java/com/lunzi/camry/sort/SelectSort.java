package com.lunzi.camry.sort;

import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序
 * 时间复杂度:平均() 最好O(n2) 最坏O(n2) 平均O(n2)
 * Created by lunzi on 2019/2/18 3:41 PM
 */
@Slf4j
public class SelectSort implements SortMethod {
    //待排序的数组;
    private int[] a;

    @Override
    public  int[] sort(int[] sortArray) {
        for(int index=0;index<sortArray.length;index++){
            SortNode sortNode=getMin(sortArray,index);
            int temp=sortArray[index];
            sortArray[index]=sortNode.getData();
            sortArray[sortNode.getIndex()]=temp;
        }
        return sortArray;
    }

    /**
     * 获取最小值
     * @param sortArray
     * @return
     */
    public SortNode getMin(int [] sortArray,int index){
        SortNode sortNode=new SortNode(index,sortArray[index]);
        for(int i=index;i<sortArray.length;i++){
            if(sortArray[i]<sortNode.getData()){
                sortNode.setData(sortArray[i]);
                sortNode.setIndex(i);
            }
        }
        return sortNode;
    }

    @Data
    class SortNode{
        private Integer data;
        private Integer index;

        public SortNode(Integer index,Integer data) {
            this.data = data;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        int sortArray[]=GenNumUtil.genNums(10000,0,20);
        SelectSort selectSort=new SelectSort();
        selectSort.sort(sortArray);
    }
}
