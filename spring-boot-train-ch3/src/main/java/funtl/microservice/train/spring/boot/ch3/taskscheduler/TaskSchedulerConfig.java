package funtl.microservice.train.spring.boot.ch3.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 配置类
 * (1) 通过 @EnableScheduling 注解开启对计划任务的支持
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch3.taskscheduler")
@EnableScheduling // 1
public class TaskSchedulerConfig {
}
