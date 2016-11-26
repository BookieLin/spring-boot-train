package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) @Configuration 声明当前类是一个配置类
 * (2) @ComponentScan 自动扫描包下所有使用 @Service、@Component、@Repository、@Controller 的类，并注册为 Bean
 */
@Configuration // 1
@ComponentScan("funtl.microservice.train.spring.boot.ch1.di") // 2
public class DiConfig {
}
