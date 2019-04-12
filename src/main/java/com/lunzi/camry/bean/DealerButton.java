package com.lunzi.camry.bean;

import lombok.Getter;

/**
 * Created by lunzi on 2019/4/10 5:26 PM
 */
public enum DealerButton {

    CLOSE(-9, "关闭订单"),
    FILL_FORM_BUTTON(0, "完善资料"),
    PAY_START_FEE(5, "支付费用"),
    CONFIRM_ABNORMAL_CAR(20, "确认异常"),
    REJECT_CONFIRM_ABNORMAL_CAR(21, "拒绝确认异常"),
    //SIGN_BUTTON(25, "签约"),
    PAY_CAR_FEE(40, "支付车款"),
    CONFIRM_RECEIVE(60, "确认收货"),
    REMARGIN(200, "追加保证金"),
    PAY_BACK(201, "回款"),
    CHANGECAR(202, "换车"),
    LOAN_SIGN(203, "买家金融签约"),
    SHARE(204, "分享给卖家"),
    ORDER_AGAIN(205, "重新下单"),
    ADVICE(206, "查看建议");


    DealerButton(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Getter
    public Integer code;
    @Getter
    private String desc;

    public Integer getCode() {
        return code;
    }
}
