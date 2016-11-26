package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行
 * (1) 使用 AnnotationConfigApplicationContext 作为 Spring 容器，接受输入一个配置类作为参数
 * (2) 获得声明配置的 UseFunctionService 的 Bean
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class); // 1

		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class); // 2
		System.out.println(useFunctionService.sayHello(" DI"));

		context.close();
	}
}
