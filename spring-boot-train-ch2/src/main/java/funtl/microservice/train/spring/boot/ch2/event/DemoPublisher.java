package funtl.microservice.train.spring.boot.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件发布类
 * (1) 注入 ApplicationContext 用来发布事件
 * (2) 使用 ApplicationContext 的 publishEvent 方法来发布
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext applicationContext; // 1

    public void publisher(String msg) {
        applicationContext.publishEvent(new DemoEvent(this, msg)); // 2
    }
}
