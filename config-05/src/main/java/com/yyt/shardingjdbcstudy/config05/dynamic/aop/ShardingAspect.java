package com.yyt.shardingjdbcstudy.config05.dynamic.aop;

import com.yyt.shardingjdbcstudy.config05.dynamic.DataSourceType;
import com.yyt.shardingjdbcstudy.config05.dynamic.DatabaseContextHolder;
import com.yyt.shardingjdbcstudy.config05.dynamic.Sharding;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangxin
 * @date 2021/9/13
 */
@Component
@Aspect
@Slf4j
@Order(-1)
public class ShardingAspect {

    @Pointcut("@annotation(sharding)")
    public void methodValidate(Sharding sharding) {

    }

    @Around(value = "methodValidate(sharding)", argNames = "thisJoinPoint,sharding")
    public Object doSystemValidate(ProceedingJoinPoint thisJoinPoint, Sharding sharding) throws Throwable {
        Method method = getMethodFromAop(thisJoinPoint);
        if (method == null) {
            return thisJoinPoint.proceed();
        }
        try {
            DatabaseContextHolder.setCustomerType(DataSourceType.SHARDING.getType());
            log.info("开始切换数据源:{}", method.getDeclaringClass().getName() + "." + method.getName());
            return thisJoinPoint.proceed();
        } finally {
            DatabaseContextHolder.clearCustomerType();
        }
    }

    public static Method getMethodFromAop(ProceedingJoinPoint thisJoinPoint) {
        Signature sig = thisJoinPoint.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            return null;
        }
        msig = (MethodSignature) sig;
        Object target = thisJoinPoint.getTarget();
        try {
            return target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
