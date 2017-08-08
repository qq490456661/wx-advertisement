package com.onway.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by linjunjie(490456661@qq.com) on 2016/4/30.
 */
@Aspect
@Component
public class WriterLogAOP {

    private Logger logger = Logger.getLogger(WriterLogAOP.class);

    @Around("execution(* com.onway.web.controller..*(..)) || execution(* com.onway.web.dao.*.*(..))") //onway.web包以及子包任意方法执行
    public Object recordExecuteTime(ProceedingJoinPoint point)throws Throwable {
        Object result = null;
        //方法名字
        Signature sig = point.getSignature();

        MethodSignature msig = null;
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        try {


            long start = System.currentTimeMillis();

            result = point.proceed();

            long end = System.currentTimeMillis();

            StringBuilder sb = new StringBuilder()
                    .append(target.getClass().toString())
                    .append(".")
                    .append(currentMethod.getName())
                    .append(" [")
                    .append(String.valueOf(end - start))
                    .append("ms]");
            logger.info(sb.toString());
        } catch (Throwable throwable) {
            StringBuilder sb = new StringBuilder()
                    .append(currentMethod.getName());
            logger.info(sb.toString());
            throw throwable;
        }
        return result;
    }
}

