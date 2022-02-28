package cn.microboat.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author zhouwei
 */
@Aspect
@Component
@Order(1)
public class PermissionFirstAdvice {

    /**
     * 定义一个切面，括号内写入第一步中自定义注解的路径
     * */
    @Pointcut("@annotation(cn.microboat.annotation.PermissionAnnotation)")
    private void permissionCheck() {}

    /**
     * Around 环绕，在 permissionCheck 方法执行前后都会被执行
     * */
    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("==========第一个切面==========");

        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        Long id = ((JSONObject) args[0]).getLong("id");
        String name = ((JSONObject) args[0]).getString("name");
        System.out.println("id1 ======>" + id);
        System.out.println("name1 ======>" + name);

        // 修改入参
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", 8);
        jsonObject.put("name", "lisi");
        args[0] = jsonObject;

        // id 小于 0 则抛出非法 id 的异常
//        if (id < 0) {
//            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
//        }

        // 将修改后的参数传入
        return joinPoint.proceed(args);
    }
}
