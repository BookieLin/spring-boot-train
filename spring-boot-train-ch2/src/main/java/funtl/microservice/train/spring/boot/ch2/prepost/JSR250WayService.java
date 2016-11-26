package funtl.microservice.train.spring.boot.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR-250 形式的 Bean
 * (1) @PostConstruct 在构造函数执行完之后执行
 * (2) @PreDestroy 在 Bean 销毁之前执行
 */
public class JSR250WayService {
    @PostConstruct // 1
    public void init() {
        System.out.println("jsr250-init-method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }

    @PreDestroy // 2
    public void destroy() {
        System.out.println("jsr250-destroy-method");
    }
}
