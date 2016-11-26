package funtl.microservice.train.spring.boot.ch1.javaconfig;

/**
 * 使用功能类的 Bean
 * (1) 此处没有使用 @Service 声明 Bean
 * (2) 此处没有使用 @Autowired 注解注入 Bean
 */
// 1
public class UseFunctionService {
	// 2
	FunctionService functionService;

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String sayHello(String word) {
		return functionService.sayHello(word);
	}
}
