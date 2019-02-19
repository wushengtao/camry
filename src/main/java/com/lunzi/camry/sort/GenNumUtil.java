package com.lunzi.camry.sort;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 生成数字的工具类
 * Created by lunzi on 2019/2/18 3:42 PM
 */
@Slf4j
public class GenNumUtil {
    private static Random random=new Random();
    private static Gson gson=new Gson();
    public static int [] genNums(Integer num,Integer min,Integer max){
        int [] nums=new int[num];
        for(int i=0;i<num;i++){
            nums[i]= random.ints(min,(max+1)).limit(1).findFirst().getAsInt();
        }
        log.info(gson.toJson(nums));
        return nums;
    }
}
