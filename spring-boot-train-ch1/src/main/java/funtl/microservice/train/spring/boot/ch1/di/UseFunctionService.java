package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用功能类的 Bean
 * (1) 使用 @Service 注解声明当前 UseFunctionService 类是 Spring 管理的一个 Bean。
 * (2) 使用 @Autowired 将 FunctionService 的实体 Bean 注入到 UseFunctionService 中，让 UseFunctionService 具备 FunctionService 的功能
 *      此处使用：
 *          JSR-330的：@Inject
 *          JSR-250的：@Resource
 *      是等效的
 */
@Service
public class UseFunctionService {
	@Autowired
	FunctionService functionService;

	public String sayHello(String word) {
		return functionService.sayHello(word);
	}
}
