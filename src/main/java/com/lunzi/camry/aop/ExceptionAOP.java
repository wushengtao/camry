package com.lunzi.camry.aop;

import com.lunzi.camry.bean.BizResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 参数校验+全局的异常捕获
 * Created by lunzi on 2019/2/18 1:44 PM
 */
@Aspect
@Component
@Slf4j
public class ExceptionAOP {
    @Around(value = "execution(public * com.lunzi.camry.controller..*.*(..))")
    public Object aopMethod(ProceedingJoinPoint proceedingJoinPoint) {
        return processMedthod(proceedingJoinPoint);
    }

    private Object processMedthod(ProceedingJoinPoint proceedingJoinPoint) {
        //参数校验
        BizResult validateResult = processValidate(proceedingJoinPoint);
        if(validateResult!=null){
            return validateResult;
        }
        Object resp;
        //捕获全局的异常
        try {
            resp=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            //异常了
            log.error("AOP Exception",throwable);
            Signature signature=proceedingJoinPoint.getSignature();
            if(signature instanceof MethodSignature){

            }
            else {
                resp=BizResult.create(9999,"服务器异常");
            }

        }
        return null;
    }

    private BizResult processValidate(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] objects = proceedingJoinPoint.getArgs();
        //没有参数无需校验
        if(ArrayUtils.isEmpty(objects)){
            return null;
        }

        for(Object obj:objects){
            //拿到BindResult
            if (obj instanceof BindingResult){
                BindingResult bindingResult=(BindingResult)obj;
                //如果有异常
                if(bindingResult.hasErrors()){
                    List<FieldError> fieldErrorList=bindingResult.getFieldErrors();
                    //获取第一个
                    FieldError fieldError=fieldErrorList.get(0);
                    if(fieldError!=null){
                        String msg=fieldError.getDefaultMessage();
                        return BizResult.create(9999,msg);
                    }
                }
            }
        }
        return null;

    }
}
