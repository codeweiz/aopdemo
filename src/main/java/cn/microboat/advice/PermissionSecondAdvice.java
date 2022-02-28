package cn.microboat.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 第二个权限切面
 *
 * @author zhouwei
 */
@Aspect
@Component
@Order(0)
public class PermissionSecondAdvice {

    @Pointcut("@annotation(cn.microboat.annotation.PermissionAnnotation)")
    private void permissionCheck() {}

    @Around("permissionCheck()")
    public Object permissionCheckSecond(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("============第二个切面============");

        Object[] args = joinPoint.getArgs();
        Long id = ((JSONObject) args[0]).getLong("id");
        String name = ((JSONObject) args[0]).getString("name");
        System.out.println("id ==========>" + id);
        System.out.println("name ==========>" + name);

        if (!"admin".equals(name)) {
            return JSON.parseObject("{\"message\":\"not admin\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
