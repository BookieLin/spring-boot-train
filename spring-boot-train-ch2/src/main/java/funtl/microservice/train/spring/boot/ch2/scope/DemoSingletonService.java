package funtl.microservice.train.spring.boot.ch2.scope;

import org.springframework.stereotype.Service;

/**
 * Singleton 的 Bean
 * (1) 默认为 Singleton ，相当于 @Scope("singleton")
 */
@Service // 1
public class DemoSingletonService {
}
