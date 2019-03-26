package com.eyas.service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAop {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Around("execution(* com.eyas.service.service.*Service.*(..))")
    public Object aroundPower(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 设置开始时间
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object obj = proceedingJoinPoint.proceed();
        log.info("RETURN : " + obj);
        log.info("USED_TIME : " + (System.currentTimeMillis() - startTime.get()) + "ms");
        return obj;
    }
}
