# 第一章 Spring 基础

## Spring 简史

Spring 的发展过程：

第一阶段：xml配置

在 Spring 1.x 时代，使用 Spring 开发全部都是 xml 配置的 Bean，随着项目的扩大，我们需要把 xml 配置文件分放到不同的配置文件里，那时候需要频繁地在开发的类和配置文件之间切换

第二阶段：注解配置

在 Spring 2.x 时代，随着 JDK 1.5 带来的注解支持，Spring 提供了申明 Bean 的注解（如 @Component、@Service），大大减少了配置量。这时 Spring 圈子里存在着一种争论：注解配置和 xml 配置究竟哪个更好？我们最终的选择是应用的基本配置（如数据库配置）用 xml ，业务配置用注解

第三阶段：Java配置

从 Spring 3.x 到现在，Spring 提供了 Java 配置的能力，使用 Java 配置可以让你更理解你配置的 Bean 。Spring 4.x 和 Spring Boot 都推荐使用 Java 配置

## Spring 概述

Spring 框架是一个轻量级的企业级开发的一站式解决方案。所谓解决方案就是可以基于 Spring 解决 Java EE 开发的所有问题。Spring 框架主要提供了 IoC 容器、AOP、数据访问、Web开发、消息、测试等相关技术的支持

Spring 使用简单的POJO（Plain Old Java Object，即无任何限制的普通 Java 对象）来进行企业级开发。每一个被 Spring 管理的 Java 对象都称之为 Bean；而 Spring 提供了一个 IoC 容器用来初始化对象，解决对象间的依赖管理和对象的使用

### 1. Spring 的模块

Spring 是模块化的，这意味着你可以只使用你需要的 Spring 模块。如图 1-1 所示。

![图 1-1](http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/images/spring-overview.png)

图 1-1 中的每一个最小单元，Spring 都至少有一个对应的 jar 包。

(1) 核心容器（Core Container）

Spring-Core：核心工具类，Spring 其他模块大量使用 Spring-Core

Spring-Beans：Spring 定义 Bean 的支持

Spring-Context：运行时 Spring 容器

Spring-Context-Support：Spring 容器对第三方包的集成支持

Spring-Expression：使用表达式语言在运行时查询和操作对象

(2) AOP

Spring-AOP：基于代理的 AOP 支持

Spring-Aspects：基于 AspectJ 的 AOP 支持

(3) 消息（Messaging）

Spring-Messaging：对消息架构和协议的支持

(4) Web

Spring-Web：提供基础的 Web 集成功能，在 Web 项目中提供 Spring 的容器

Spring-Webmvc：提供基于 Servlet 的 Spring MVC

Spring-WebSocket：提供 WebSocket 功能

Spring-Webmvc-Portlet：提供 Portlet 环境支持

(5) 数据访问/集成（Data Access/Integration）

Spring-JDBC：提供以 JDBC 访问数据库的支持

Spring-TX：提供编程式和申明式的事务支持

Spring-ORM：提供对对象/关系映射技术的支持

Spring-OXM：提供对对象/xml映射技术的支持

Spring-JMS：提供对JMS的支持

### 2. Spring 的生态

Spring 发展到现在已经不仅仅是 Spring 框架本身的内容，Spring 目前提供了大量的基于 Spring 的项目，可以用来更深入地降低我们的开发难度，提高开发效率

目前 Spring 的生态里主要有以下项目，我们可以根据自己项目的需要来选择相应的项目。

Spring Boot：使用默认开发配置来实现快速开发

Spring XD：用来简化大数据应用开发

Spring Cloud：为分布式系统开发提供工具集

Spring Data：对主流的关系型和 NoSQL 数据库的支持

Spring Integration：通过消息机制对企业集成模式（EIP）的支持

Spring Batch：简化及优化大量数据的批处理操作

Spring Security：通过认证和授权保护应用

Spring HATEOAS：基于 HATEOAS 原则简化 REST 服务开发

Spring Social：与社交网络 API （如 Facebook、新浪微博等）的集成

Spring AMQP：对基于 AMQP 的消息的支持

Spring Mobile：提供对手机设备检测的功能，给不同的设备返回不同的页面的支持

Spring for Android：主要提供在 Android 上消费 RESTFul API 的功能

Spring Web Flow：基于 Spring MVC 提供基于向导流程式的 Web 应用开发

Spring Web Services：提供了基于协议有限的 SOAP/Web 服务

Spring LDAP：简化实用 LDAP 开发

Spring Session：提供一个 API 及实现来管理用户会话信息

### 3. Spring 基础配置

Spring 框架本身有四大原则：

(1) 使用 POJO 进行轻量级和最小侵入式开发

(2) 通过依赖注入和基于接口编程实现松耦合

(3) 通过 AOP 的默认习惯进行声明式编程

(4) 使用 AOP 和模版（template）减少模式化代码

Spring 所有功能的设计和实现都是基于此四大原则的

#### 3.1 依赖注入

##### 3.1.1 说明

控制翻转（Inversion of Control-IOC）和依赖注入（dependency injection-DI）在 Spring 环境下是等同的概念，控制翻转是通过依赖注入实现的。所谓依赖注入指的是容器负责创建对象和维护对象间的依赖关系，而不是通过对象本身负责自己的创建和解决自己的依赖。

依赖注入的主要目的是为了解耦，提现一种“组合”的理念。如果你希望你的类具备某项功能的时候，是继承自一个具有此类功能的父类好呢？还是组合另外一个具有这个功能的类好呢？答案是不言而喻的，继承一个父类，子类将于父类耦合，组合另外一个类则使耦合度大大降低

Spring IoC 容器（ApplicationContext）负责创建 Bean，并通过将功能类 Bean 注入到你需要的 Bean 中。Spring 提供使用 xml、注解、Java 配置、groovy 配置实现 Bean 的创建和注入

无论是 xml 配置、注解配置还是 Java 配置、都被称为配置元数据，所谓元数据即描述数据的数据。元数据本身不具备任何可执行的能力，只能通过外界代码来对这些元数据进行解析后进行一些有意义的操作。Spring 容器解析这些元数据进行 Bean 初始化、配置和管理依赖。

声明 Bean 的注解：

* @Component 组件，没有明确的角色
* @Service 在业务逻辑层（service 层）使用
* @Repository 在数据访问层（dao 层）使用
* @Controller 在展现层（MVC -> Spring MVC）使用

注入 Bean 的注解，一般情况下通用

* @Autowired：Spring 提供的注解
* @Inject：JSR-330 提供的注解
* @Resource：JSR-250 提供的注解

**注：请将注解注解在属性上，优点是代码更少，层次更清晰**

**本节演示基于注解的 Bean 的初始化和依赖注入，Spring 容器类选用 AnnotationConfigApplicationContext**

##### 3.1.2 示例

**编写功能类的 Bean**

```java

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

```

**使用功能类的 Bean**

```java

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

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) @Configuration 声明当前类是一个配置类
 * (2) @ComponentScan 自动扫描包下所有使用 @Service、@Component、@Repository、@Controller 的类，并注册为 Bean
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch1.di")
public class DiConfig {
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch1.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行
 * (1) 使用 AnnotationConfigApplicationContext 作为 Spring 容器，接受输入一个配置类作为参数
 * (2) 获得声明配置的 UseFunctionService 的 Bean
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
		System.out.println(useFunctionService.sayHello(" DI"));

		context.close();
	}
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch1-001.png">

#### 3.2 Java 配置

##### 3.2.1 说明

Java 配置是 Spring 4.x 推荐的配置方式，可以完全替代 xml 配置

Java 配置也是 Spring Boot 推荐的配置方式

Java 配置是通过 @Configuration 和 @Bean 来实现的

* @Configuration 声明当前类是一个配置类，相当于一个 Spring 配置的 xml 文件
* @Bean 注解在方法上，声明当前方法的返回值为一个 Bean

##### 3.2.2 示例

**编写功能类的 Bean**

```java

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

```

**使用功能类的 Bean**

```java

package funtl.microservice.train.spring.boot.ch1.javaconfig;

/**
 * 使用功能类的 Bean
 * (1) 此处没有使用 @Service 声明 Bean
 * (2) 此处没有使用 @Autowired 注解注入 Bean
 */
// 1
public class UseFunctionService {
	// 2
	FunctionService functionService;

	public void setFunctionService(FunctionService functionService) {
		this.functionService = functionService;
	}

	public String sayHello(String word) {
		return functionService.sayHello(word);
	}
}

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch1.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 * (1) 使用 @Configuration 注解表明当前类是一个配置类，这意味着这个类里面可能有 0 个或者多个 @Bean 注解，此处没有使用包扫描，是因为所有的 Bean 都在此类中定义了
 * (2) 使用 @Bean 注解声明当前方法 FunctionService 的返回值是一个 Bean ，Bean 的名称是方法名
 * (3) 注入 FunctionService 的 Bean 时候直接调用 functionService()
 * (4) 另外一种注入方式，直接将 FunctionService 作为参数给 useFunctionService()，这也是 Spring 容器提供的极好的功能。在 Spring 容器中，只要容器中存在某个 Bean，就可以在另外一个 Bean 的声明方法的参数中注入
 */
@Configuration // 1
public class JavaConfig {
	@Bean // 2
	public FunctionService functionService() {
		return new FunctionService();
	}

	@Bean
	public UseFunctionService useFunctionService() {
		UseFunctionService useFunctionService = new UseFunctionService();
		useFunctionService.setFunctionService(functionService()); // 3
		return useFunctionService;
	}

//	@Bean
//	public UseFunctionService useFunctionService(FunctionService functionService) { // 4
//		UseFunctionService useFunctionService = new UseFunctionService();
//		useFunctionService.setFunctionService(functionService);
//		return useFunctionService;
//	}
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch1.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);

		UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
		System.out.println(useFunctionService.sayHello(" Java Config"));

		context.close();
	}
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch1-002.png">

#### 3.3 AOP

##### 3.3.1 说明

AOP：面向切面编程，相对于 OOP 面向对象编程

Spring 的 AOP 存在的目的是为了解耦，AOP 可以让一组类共享相同的行为。在 OOP 中只能通过继承类和实现接口，来使代码的耦合度增强，且类继承只能为单继承，阻碍更多行为添加到一组类上，AOP 弥补了 OOP 的不足

Spring 支持 AspectJ 的注解式切面编程

* 使用 @Aspect 声明是一个切面
* 使用 @After、@Before、@Around 定义建言（advice），可直接将拦截规则（切点）作为参数
* 其中 @After、@Before、@Around 参数的拦截规则为切点（PointCut），为了使切点复用，可使用 @PointCut 专门定义拦截规则，然后在 @After、@Before、@Around 的参数中调用
* 其中符合条件的每一个被拦截处为连接点（JoinPoint）

**本节示例将演示基于注解拦截和基于方法规则拦截两种方式，演示一种模拟记录操作的日志系统的实现。其中注解式拦截能够很好地控制要拦截的粒度和获得更丰富的信息，Spring 本身在事务处理（@Transcational）和数据缓存（@Cacheable）等上面都使用此种形式的拦截**

##### 3.3.2 示例

**添加 Spring AOP 支持及 AspectJ 依赖**

```xml

<!-- Spring AOP 支持 -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
</dependency>

<!-- AspectJ 支持 -->
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
</dependency>

```

**拦截规则的注解**

```java

package funtl.microservice.train.spring.boot.ch1.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 拦截规则的注解
 * 注解说明：注解本身是没有功能的，就和 xml 一样。注解和 xml 都是一种元数据。元数据即解释数据的数据，这就是所谓配置
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
	String name();
}

```

**使用注解的被拦截类**

```java

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

```

**使用方法规则的被拦截类**

```java

package funtl.microservice.train.spring.boot.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * 使用方法规则的被拦截类
 */
@Service
public class DemoMethodService {
	public void add() {}
}

```

**编写切面**

```java

package funtl.microservice.train.spring.boot.ch1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 编写切面
 * (1) 通过 @Aspect 注解声明一个切面
 * (2) 通过 @Component 让此切面称为 Spring 容器管理的 Bean
 * (3) 通过 @Pointcut 注解声明切点
 * (4) 通过 @After 注解声明一个建言，并使用 @Pointcut 定义的切点
 * (5) 通过反射可获得注解上的属性，然后做日志记录相关的操作，下面的相同
 * (6) 通过 @Before 注解声明一个建言，此建言直接使用拦截规则作为参数
 */
@Aspect // 1
@Component // 2
public class LogAspect {
	@Pointcut("@annotation(funtl.microservice.train.spring.boot.ch1.aop.Action)") // 3
	public void annotationPointCut() {};

	@After("annotationPointCut()") // 4
	public void after(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Action action = method.getAnnotation(Action.class);
		System.out.println("注解式拦截：".concat(action.name())); // 5
	}

	@Before("execution(* funtl.microservice.train.spring.boot.ch1.aop.DemoMethodService.*(..))") // 6
	public void before(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		System.out.println("方法规则式拦截：".concat(method.getName()));
	}
}

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 * (1) 使用 @EnableAspectJAutoProxy 注解开启 Spring 对 AspectJ 的支持
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch1.aop")
@EnableAspectJAutoProxy // 1
public class AopConfig {

}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch1.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 运行类
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

		DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
		DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);

		demoAnnotationService.add();

		demoMethodService.add();

		context.close();
	}
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch1-003.png">