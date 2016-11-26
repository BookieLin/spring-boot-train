package funtl.microservice.train.spring.boot.ch2.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * (1) 先将活动的 Profile 设置为 prod
 * (2) 后置注册 Bean 配置类，不然会报 Bean 未定义错误
 * (3) 刷新容器
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.getEnvironment().setActiveProfiles("prod"); // 1
        context.register(ProfileConfig.class); // 2
        context.refresh(); // 3

        DemoBean demoBean = context.getBean(DemoBean.class);

        System.out.println(demoBean.getContent());

        context.close();
    }
}
