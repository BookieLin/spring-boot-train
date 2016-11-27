package funtl.microservice.train.spring.boot.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 * (1) 实现 ApplicationListener 接口，并指定监听的事件类型
 * (2) 使用 onApplicationEvent 方法对消息进行接受处理
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> { // 1
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) { // 2
        String msg = demoEvent.getMsg();

        System.out.println("我(bean-demoListener)接收到了bean-demoPublisher发布的消息：" + msg);
    }
}
