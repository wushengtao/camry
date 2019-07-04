package com.lunzi.camry.rpc;

/**
 * Created by lunzi on 2019/6/26 9:24 AM
 */
public class RpcInterfaceImpl implements RpcInterface {
    @Override
    public int add(int a, int b) {
        return a+b;
    }
}
