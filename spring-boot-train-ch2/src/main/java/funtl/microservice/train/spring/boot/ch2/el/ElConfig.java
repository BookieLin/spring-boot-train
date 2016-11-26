package funtl.microservice.train.spring.boot.ch2.el;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * 配置类
 *
 * (1) 注入普通字符
 * (2) 注入操作系统属性
 * (3) 注入表达式运算结果
 * (4) 注入其他 Bean 的属性
 * (5) 注入文件资源
 * (6) 注入网址资源
 * (7) 注入配置文件
 *
 * 注入配置文件须使用 @PropertySource 指定文件地址，若使用 @Value 注入，则要配置一个 PropertySourcesPlaceholderConfigurer 的 Bean。
 * 注意，@Value("${user.name}")使用的是“$”，而不是“#”
 * 注入 Properties 还可以从 Environment 中获得
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch2.el")
@PropertySource("classpath:test.properties") // 7
public class ElConfig {
    @Value("I Love You") // 1
    private String normal;

    @Value("#{systemProperties['os.name']}") // 2
    private String osName;

    @Value("#{ T(java.lang.Math).random() * 100.0 }") // 3
    private double randomNumber;

    @Value("#{demoService.another}") // 4
    private String fromAnother;

    @Value("classpath:test.txt") // 5
    private Resource testFile;

    @Value("https://www.baidu.com") // 6
    private Resource testUrl;

    @Value("${user.name}") // 7
    private String userName;

    @Autowired
    private Environment environment; // 7

    @Bean // 7
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public void outputResource() {
        try {
            System.out.println(normal);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream()));
            System.out.println(IOUtils.toString(testUrl.getInputStream()));
            System.out.println(userName);
            System.out.println(environment.getProperty("user.sex"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
