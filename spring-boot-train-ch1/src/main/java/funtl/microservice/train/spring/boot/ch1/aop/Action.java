package funtl.microservice.train.spring.boot.ch1.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截规则的注解
 * 注解说明：注解本身是没有功能的，就和 xml 一样。注解和 xml 都是一种元数据。元数据即解释数据的数据，这就是所谓配置
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
	String name();
}
