package com.zyx.cache;


import com.alibaba.fastjson.JSONObject;
import com.zyx.annotation.RedisAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;

@Aspect
@Configuration
public class RedisCommentCache {
    @Autowired
    private Jedis jedis;


    @Around("execution(* com.zyx.service.*.select*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result=null;
        /*获取方法*/
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        /*获取方法上是否有注解*/
        boolean b = method.isAnnotationPresent(RedisAnnotation.class);

        if(b){
            StringBuffer sb= new StringBuffer();
            /*获取类的名称*/
            String className = proceedingJoinPoint.getTarget().getClass().getName();
            /*获取方法名*/
            String methodName = proceedingJoinPoint.getSignature().getName();
            /*获取方法参数*/
            Object[] args = proceedingJoinPoint.getArgs();

            /*拼接*/
            sb.append(className).append(".").append(methodName);
            for (Object arg : args) {
                sb.append(arg);
            }
            /*转换成String类型赋值给key*/
            String key=sb.toString();
            /*判断redis是否存在此key*/
            if(jedis.hexists(className,key)){
                String json = jedis.hget(className, key);
                result = JSONObject.parse(json);
                System.out.println("存在===============");
            }else{
                /*不存在此key进行放行*/
                result= proceedingJoinPoint.proceed();
                /*并存到redis当中*/
                jedis.hset(className,key,JSONObject.toJSONString(result));
                System.out.println("不存在==============");
            }
        }else{
            result= proceedingJoinPoint.proceed();
            System.out.println("放行=====================");
        }
        jedis.close();
        return result;
    }
    @After("execution(* com.zyx.service.*.*(..))&&!execution(* com.zyx.service.*.select*(..))")
//    @AfterReturning("execution(* com.zyx.service.*.*(..))&&!execution(* com.zyx.service.*.select*(..))")
    public void after(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getName();
        jedis.del(className);
        jedis.close();
    }
}
