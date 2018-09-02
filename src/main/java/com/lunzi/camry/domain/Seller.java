package com.lunzi.camry.domain;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

/**
 * Created by lunzi on 2018/8/30 下午11:02
 */
@Data
@Builder
public class Seller {
    private Long carId;
    private Long contractAmount; // 合同金额
    private Long earnest; // 定金
    private Long balance; // 尾款
    private Long speedReturnApplyFee; // 速回款(申请)金额
    private Long speedReturnActualFee; // 速回款(借贷实际)金额
    private Long expectedInterests; // 预估利息
    private Long actualInterests; // 实际利息
    private Long expectedWmsFee; // 预估仓储费
    private Long expectedTmsFee; // 预估物流费用
    private Long earnestFrozenFee; // 定金冻结预估费用
    private Long balanceFrozenFee; // 尾款冻结预估费用
    public static void main(String args[]){
        Seller seller=Seller.builder()
                .carId(1L)
                .contractAmount(1L)
                .earnest(1L)
                .balance(1L)
                .speedReturnApplyFee(1L)
                .speedReturnActualFee(1L)
                .expectedInterests(1L)
                .actualInterests(1L)
                .expectedWmsFee(1L)
                .expectedTmsFee(1L)
                .earnestFrozenFee(1L)
                .balanceFrozenFee(1L)
                .build();
        System.out.println(new Gson().toJson(seller));
    }
}
