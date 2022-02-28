package cn.microboat.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhouwei
 */
@Aspect
@Component
@Slf4j
public class LogAspectHandler {

    /**
     * 定义切面，就在注解使用的位置
     * */
    @Pointcut("@annotation(cn.microboat.annotation.PermissionAnnotation)")
    private void pointCut() {}

    /**
     * 在上面定义的切面方法之前执行该方法
     *
     * @param joinPoint 切面
     * */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("=========doBefore方法进入了=======");

        // 获取签名，签名中可以获取请求的包名、方法名、参数
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为：{}，属于{}包", funcName, declaringTypeName);

        // 获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 获取请求的 URl
        String url = request.getRequestURL().toString();
        // 获取请求的 IP
        String ip = request.getRemoteAddr();
        log.info("用户请求的 url 为{}，ip 地址为：{}", url, ip);
    }

    @After("pointCut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("==========doAfter 方法进入了=========");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}已经执行完毕!", method);
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("==========doAfterReturning 方法进入了=========");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}执行完毕，返回参数为{}：", method, result);

        // 实际项目中可以根据业务做具体的返回值增强
        log.info("对返回的参数进行业务上的增强：{}", result + "增强版");
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.info("==========doAfterThrowing 方法进入了=========");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("执行方法{}出错，异常为：{}", method, ex);
    }

}
