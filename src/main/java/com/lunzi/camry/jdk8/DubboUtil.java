package com.lunzi.camry.jdk8;

import java.util.function.Supplier;

/**
 * Created by lunzi on 2018/12/3 10:44 AM
 */
public class DubboUtil {
    private static InvokerBuilder newInstance(){
        return new InvokerBuilder();
    }
    private static class InvokerBuilder{
        private String param;
        private String log;

        public InvokerBuilder setParam(String param) {
            this.param = param;
            return this;
        }

        public InvokerBuilder setLog(String log) {
            this.log = log;
            return this;
        }
        public <T> BizResult<T> invoke(Supplier<BizResult> bizResultSupplier){
            try{
                BizResult<T> serviceResult=bizResultSupplier.get();
                return serviceResult;
            }catch (Exception e){
                System.out.println("服务发生了异常");
                return new BizResult<>();

            }

        }
    }

    public static void main(String[] args) {
        DubboUtil.newInstance().invoke(()->{
            throw  new RuntimeException("npe");
        });
    }
}
class BizResult<T> {
}


