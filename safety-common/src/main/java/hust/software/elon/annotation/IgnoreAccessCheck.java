package hust.software.elon.annotation;

import java.lang.annotation.*;

/**
 * @author elon
 * @date 2022/4/4 20:43
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAccessCheck {
}
