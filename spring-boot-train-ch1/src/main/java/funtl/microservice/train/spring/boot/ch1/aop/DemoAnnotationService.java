package funtl.microservice.train.spring.boot.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * 使用注解的被拦截类
 */
@Service
public class DemoAnnotationService {
	@Action(name = "注解式拦截的 add 操作")
	public void add() {}
}
