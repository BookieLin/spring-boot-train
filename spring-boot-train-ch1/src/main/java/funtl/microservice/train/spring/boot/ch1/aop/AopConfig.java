package funtl.microservice.train.spring.boot.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 * (1) 使用 @EnableAspectJAutoProxy 注解开启 Spring 对 AspectJ 的支持
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch1.aop")
@EnableAspectJAutoProxy // 1
public class AopConfig {

}