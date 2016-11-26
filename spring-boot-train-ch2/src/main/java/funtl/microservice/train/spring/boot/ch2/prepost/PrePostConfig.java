package funtl.microservice.train.spring.boot.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) initMethod 和 destroyMethod 指定 BeanWayService 类的 init 和 destroy 方法在构造之后、Bean 销毁之前执行
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch2.prepost")
public class PrePostConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy") // 1
    BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService() {
        return new JSR250WayService();
    }
}
