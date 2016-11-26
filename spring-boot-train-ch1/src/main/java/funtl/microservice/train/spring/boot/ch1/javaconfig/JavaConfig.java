package funtl.microservice.train.spring.boot.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) 使用 @Configuration 注解表明当前类是一个配置类，这意味着这个类里面可能有 0 个或者多个 @Bean 注解，此处没有使用包扫描，是因为所有的 Bean 都在此类中定义了
 * (2) 使用 @Bean 注解声明当前方法 FunctionService 的返回值是一个 Bean ，Bean 的名称是方法名
 * (3) 注入 FunctionService 的 Bean 时候直接调用 functionService()
 * (4) 另外一种注入方式，直接将 FunctionService 作为参数给 useFunctionService()，这也是 Spring 容器提供的极好的功能。在 Spring 容器中，只要容器中存在某个 Bean，就可以在另外一个 Bean 的声明方法的参数中注入
 */
@Configuration // 1
public class JavaConfig {
	@Bean // 2
	public FunctionService functionService() {
		return new FunctionService();
	}

	@Bean
	public UseFunctionService useFunctionService() {
		UseFunctionService useFunctionService = new UseFunctionService();
		useFunctionService.setFunctionService(functionService()); // 3
		return useFunctionService;
	}

//	@Bean
//	public UseFunctionService useFunctionService(FunctionService functionService) { // 4
//		UseFunctionService useFunctionService = new UseFunctionService();
//		useFunctionService.setFunctionService(functionService);
//		return useFunctionService;
//	}
}
