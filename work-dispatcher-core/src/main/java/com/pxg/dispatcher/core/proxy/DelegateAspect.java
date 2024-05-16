//package com.pxg.dispatcher.core.proxy;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//
//@Aspect
//public class DelegateAspect {
//
//    @Pointcut("@annotation(com.pxg.dispatcher.core.proxy.Delegate)")
//    public void cut() {
//    }
//
//    @Around("cut()")
//    public Object handle(ProceedingJoinPoint point) throws Throwable {
//        return point.proceed();
//    }
//
//}
