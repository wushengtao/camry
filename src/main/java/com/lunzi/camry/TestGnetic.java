package com.lunzi.camry;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.lunzi.camry.domain.Student;
import com.lunzi.camry.generics.TestGenerics;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by lunzi on 2018/8/22 上午9:06
 */
@Slf4j
public class TestGnetic<T>{
    public static void main(String args[]){
        List<Long> idList=Lists.newArrayList();
        idList.add(1L);
        idList.add(2L);
        log.info("idList={}",idList);
        try {
           throw new RuntimeException();
        }catch (Exception e){
            log.error("excecption e={}");
        }


//        List<Student> list=null;
//        System.out.println(LocalDateTime.now());
//        Map<Long,WarehouseVO> warehouseVOMap=new HashMap<>();
//        warehouseVOMap.put(1L,new WarehouseVO(1L));
//        warehouseVOMap.put(2L,new WarehouseVO(1L));
//        warehouseVOMap.put(3L,new WarehouseVO(3L));
//        warehouseVOMap.put(4L,new WarehouseVO(2L));
//        warehouseVOMap.put(5L,new WarehouseVO(3L));
//        Map<Long,List<Long>> placeOrderMap= Maps.newHashMap();
//        for(Map.Entry<Long,WarehouseVO> entry:warehouseVOMap.entrySet()){
//            WarehouseVO warehouseVO=entry.getValue();
//            Long warehouseId=warehouseVO.getWarehouseId();
//            Long ccCarId=entry.getKey();
//            if(placeOrderMap.containsKey(warehouseId)){
//                List<Long> carIds=placeOrderMap.get(warehouseId);
//                if(CollectionUtils.isEmpty(carIds)){
//                    carIds=Lists.newArrayList();
//                }
//                carIds.add(ccCarId);
//                placeOrderMap.put(warehouseId,carIds);
//            }else {
//                List<Long> carIds=Lists.newArrayList();
//                carIds.add(ccCarId);
//                placeOrderMap.put(warehouseId,carIds);
//            }
//        }
//        System.out.println(placeOrderMap);
       int i=1;
       int j=2;
       int k=i+j;
       for (int m=0;m<10;m++){
           Integer a=1;
           System.out.println(a+0L);
       }
    }
    static class WarehouseVO{

        private Long warehouseId;

        public WarehouseVO(Long warehouseId) {
            this.warehouseId = warehouseId;
        }

        public Long getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(Long warehouseId) {
            this.warehouseId = warehouseId;
        }
    }
}
