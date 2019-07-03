package com.wukw.spring.consul.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public aspect RoleAspect {

    @Around("@annotation(com.wukw.spring.consul.aspect.Role)")
    public void point(ProceedingJoinPoint point){
        point.getSignature().getDeclaringType().getAnnotation(Role.class);



    }

}
