package funtl.microservice.train.spring.boot.ch2.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Prototype 的 Bean
 * (1) 声明 Scope 为 Prototype
 */
@Service
@Scope("prototype") // 1
public class DemoPrototypeService {
}
