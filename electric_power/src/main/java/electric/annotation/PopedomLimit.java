package electric.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限限制的自定义注解
 *
 * @author near on 2016/3/20.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PopedomLimit {
    String mid() default "";

    String pid() default "";
}
