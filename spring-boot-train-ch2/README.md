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