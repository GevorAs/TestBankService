package com.agr.bankservice.aoc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspectService {


    @Pointcut("execution(* com.agr.bankservice.service.CardService.deposit(..))" +
            "|| execution(* com.agr.bankservice.service.CardService.withdrawal(..))")
    public void transactionMethods() {

    }

    @Around(value = "transactionMethods()")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object type = joinPoint.proceed();
        try {
            String methodName = joinPoint.getSignature().getName();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("methode : ").append(methodName);
            Object[] inputArgs = joinPoint.getArgs();
            Signature signature = joinPoint.getSignature();
            Object[] inputType = ((MethodSignature) signature).getParameterNames();
            Map<Object, Object> parametersMap = new HashMap<>();
            for (int i = 0; i < inputType.length; i++) {
                parametersMap.put(inputType[i], inputArgs[i]);
            }
            stringBuilder.append("parameters : ").append(parametersMap);
            if (type != null) {
                Set<Object> returnType = Collections.singleton(type.toString()
                        .replaceAll("<", "")
                        .replaceAll(">", ""));
                stringBuilder.append("returnType : ").append(returnType);
            } else {
                stringBuilder.append("return object : ").append("[]");
            }
            log.info("--Transaction log:{}", stringBuilder.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return type;
    }

}