package com.lunzi.camry.rpc;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lunzi on 2019/7/3 11:23 AM
 */
@Data
public class RpcInvoker implements Serializable {
    private int a;
    private int b;
    private int result;

    RpcInvoker(int a, int b) {
        this.a = a;
        this.b = b;
    }

    private int getResult(){
        return result;
    }
}
