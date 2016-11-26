package funtl.microservice.train.spring.boot.ch1.javaconfig;

/**
 * 功能类的 Bean
 * (1) 此处没有使用 @Service 声明 Bean
 */
// 1
public class FunctionService {
	public String sayHello(String word) {
		return "Hello".concat(word).concat("!");
	}
}
