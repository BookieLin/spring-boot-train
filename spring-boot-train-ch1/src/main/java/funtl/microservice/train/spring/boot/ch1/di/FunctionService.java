package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.stereotype.Service;

/**
 * 功能类的 Bean
 * (1) 使用 @Service 注解声明当前 FunctionService 类是 Spring 管理的一个 Bean。其中，使用@Component、@Service、@Repository、@Controller是等效的，可根据需要选用
 */
@Service
public class FunctionService {
	public String sayHello(String word) {
		return "Hello".concat(word).concat("!");
	}
}
