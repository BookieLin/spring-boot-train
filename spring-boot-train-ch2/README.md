# 第二章 Spring 常用配置

## Bean 的 Scope

### 说明

Scope 描述的是 Spring 容器如何新建 Bean 的实例的。Spring 的 Scope 有以下几种，通过 @Scope 注解来实现。

* Singleton：一个 Spring 容器中只有一个 Bean 的实例，此为 Spring 的默认配置，全容器共享一个实例
* Prototype：每次调用新建一个 Bean 的实例
* Request：Web 项目中，给每一个 http request 新建一个 Bean 实例
* Session：Web 项目中，给每一个 http session 新建一个 Bean 实例
* GlobalSession：这个只在 portal 应用中有用，给每一个 global http session 新建一个 Bean 实例

**本例简单演示默认的 Singleton 和 Prototype，分别从 Spring 容器中获得 2 次 Bean，判断 Bean 的实例是否相等**

### 示例

**Singleton 的 Bean**

```java

package funtl.microservice.train.spring.boot.ch2.scope;

import org.springframework.stereotype.Service;

/**
 * Singleton 的 Bean
 * (1) 默认为 Singleton ，相当于 @Scope("singleton")
 */
@Service // 1
public class DemoSingletonService {
}

```

**Prototype 的 Bean**

```java

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

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch2.scope;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch2.scope")
public class ScopeConfig {
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch2.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);

        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);

        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);

        System.out.println("s1 与 s2 是否相等：" + s1.equals(s2));
        System.out.println("p1 与 p2 是否相等：" + p1.equals(p2));

        context.close();
    }
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch2-001.png">

## Spring EL 和资源调用

### 说明

Spring EL - Spring 表达式语言，支持在 xml 和注解中使用表达式，类似于 JSP 的 EL 表达式语言

Spring 开发中经常涉及调用各种资源的情况，包含普通文件、网址、配置文件、系统环境变量等，我们可以使用 Spring 的表达式语言实现资源的注入

Spring 主要在注解 @Value 的参数中使用表达式

本节演示实现以下几种情况：

* 注入普通字符
* 注入操作系统属性
* 注入表达式运算结果
* 注入其他 Bean 的属性
* 注入文件内容
* 注入网址内容
* 注入属性文件

### 示例

**增加 commons-io 可简化文件相关操作，本例中使用 commons-io 将 file 转换成字符串**

```xml

<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
</dependency>

```

在 resources 目录下新建 test.txt，内容随意

在 resources 目录下新建 test.properties，内容如下：

```
user.name=Lusifer
user.sex=Male
```

**需被注入的 Bean**

```java

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

```

**配置类**

```java

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

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch2.el;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ElConfig.class);

        ElConfig resourceService = context.getBean(ElConfig.class);

        resourceService.outputResource();

        context.close();
    }
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch2-002.png">

## Bean 的初始化和销毁

### 说明

在我们实际开发的时候，经常会遇到在 Bean 使用之前或者之后做些必要的操作，Spring 对 Bean 的生命周期的操作提供了支持。在使用 Java 配置和注解配置下提供如下两种方式：

* Java 配置方式：使用 @Bean 的 initMethod 和 destroyMethod（相当于 xml 配置的 init-method 和 destroy-method）
* 注解方式：利用 JSR-250 的 @PostConstruct 和 @PreDestroy

### 示例

**使用 @Bean 形式的 Bean**

```java

package funtl.microservice.train.spring.boot.ch2.prepost;

/**
 * 使用 @Bean 形式的 Bean
 */
public class BeanWayService {
    public void init() {
        System.out.println("@Bean-init-method");
    }

    public BeanWayService() {
        super();
        System.out.println("初始化构造函数-BeanWayService");
    }

    public void destroy() {
        System.out.println("@Bean-destroy-method");
    }
}

```

**JSR-250 形式的 Bean**

```java

package funtl.microservice.train.spring.boot.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * JSR-250 形式的 Bean
 * (1) @PostConstruct 在构造函数执行完之后执行
 * (2) @PreDestroy 在 Bean 销毁之前执行
 */
public class JSR250WayService {
    @PostConstruct // 1
    public void init() {
        System.out.println("jsr250-init-method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }

    @PreDestroy // 2
    public void destroy() {
        System.out.println("jsr250-destroy-method");
    }
}

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) initMethod 和 destroyMethod 指定 BeanWayService 类的 init 和 destroy 方法在构造之后、Bean 销毁之前执行
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch2.prepost")
public class PrePostConfig {
    @Bean(initMethod = "init", destroyMethod = "destroy") // 1
    BeanWayService beanWayService() {
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService() {
        return new JSR250WayService();
    }
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch2.prepost;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);

        BeanWayService beanWayService = context.getBean(BeanWayService.class);
        JSR250WayService jsr250WayService = context.getBean(JSR250WayService.class);

        context.close();
    }
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch2-003.png">