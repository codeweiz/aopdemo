package cn.microboat.annotation;

import java.lang.annotation.*;

/**
 * 作用在方法上
 * 生命周期持续到运行时
 *
 * @author zhouwei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnnotation {
}
