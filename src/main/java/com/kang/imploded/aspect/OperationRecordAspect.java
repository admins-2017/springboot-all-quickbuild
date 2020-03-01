package com.kang.imploded.aspect;

import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.until.SecurityUntil;
import com.kang.sys.entity.OperationRecord;
import com.kang.sys.service.IOperationRecordService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 将每次操作记录获取
 * @author kang
 * @date 2019.11.19
 */
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {

    @Autowired
    private IOperationRecordService recordService;

    @Pointcut("@annotation(com.kang.imploded.aspect.SysLog)")
    public void controllerAspect(){

    }

    /**
          * @Description 前置通知 ，就是在有LoggerOperator注解的接口执行前执行这个doBefore()方法。
          */
     @Before("controllerAspect()")
     public void doBefore(JoinPoint joinPoint) {

     try {
         HttpServletRequest request = ((ServletRequestAttributes)
                 RequestContextHolder.getRequestAttributes())
                 .getRequest();
         OperationRecord record=new OperationRecord();
         record.setRequestUser(SecurityUntil.getUserName());
         record.setRequestUrl(request.getRequestURL().toString());
         record.setRequestIp(IpUtil.getIpAdrress(request));
         record.setRequestType(request.getMethod());
         record.setRequestTime(LocalDateTime.now());
         record.setRequestMethod( joinPoint.getTarget().getClass().getName()
                 + "." + joinPoint.getSignature().getName());
         record.setDescription(getControllerMethodDescription(joinPoint));
         log.info("record={}",record);
         log.info("url={}",request.getRequestURL());
         log.info("uri={}",request.getRequestURI());
         recordService.save(record);
     } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }catch (Exception e){
         e.printStackTrace();
     }
}

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getControllerMethodDescription(JoinPoint point) throws Exception {
        // 获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        // 获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    description = method.getAnnotation(SysLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

}
