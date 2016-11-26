package funtl.microservice.train.spring.boot.ch2.el;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 需被注入的 Bean
 * (1) 此处为注入普通字符串
 */
@Service
public class DemoService {
    @Value("其他类的属性") // 1
    private String another;

    public String getAnother() {
        return another;
    }

    public void setAnother(String another) {
        this.another = another;
    }
}
