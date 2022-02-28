package cn.microboat.advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhouwei
 */
@Aspect
@Component
public class LogAdvice {

    /**
     * 定义一个切点，所有被 GetMapping 注解修饰的方法会织入 advice
     * */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointcut() {}

    /**
     * Before 表示 logAdvice 将在目标方法 logAdvicePointcut 执行前执行
     * */
    @Before("logAdvicePointcut()")
    public void logAdvice() {
        System.out.println("get 请求的 advice 触发了！");
    }
}
