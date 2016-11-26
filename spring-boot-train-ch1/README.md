# 第一章 Spring 基础

## Spring 概述

### Spring 简史

Spring 的发展过程：

第一阶段：xml配置

在 Spring 1.x 时代，使用 Spring 开发全部都是 xml 配置的 Bean，随着项目的扩大，我们需要把 xml 配置文件分放到不同的配置文件里，那时候需要频繁地在开发的类和配置文件之间切换

第二阶段：注解配置

在 Spring 2.x 时代，随着 JDK 1.5 带来的注解支持，Spring 提供了申明 Bean 的注解（如 @Component、@Service），大大减少了配置量。这时 Spring 圈子里存在着一种争论：注解配置和 xml 配置究竟哪个更好？我们最终的选择是应用的基本配置（如数据库配置）用 xml ，业务配置用注解

第三阶段：Java配置

从 Spring 3.x 到现在，Spring 提供了 Java 配置的能力，使用 Java 配置可以让你更理解你配置的 Bean 。Spring 4.x 和 Spring Boot 都推荐使用 Java 配置

### Spring 概述

Spring 框架是一个轻量级的企业级开发的一站式解决方案。所谓解决方案就是可以基于 Spring 解决 Java EE 开发的所有问题。Spring 框架主要提供了 IoC 容器、AOP、数据访问、Web开发、消息、测试等相关技术的支持

Spring 使用简单的POJO（Plain Old Java Object，即无任何限制的普通 Java 对象）来进行企业级开发。每一个被 Spring 管理的 Java 对象都称之为 Bean；而 Spring 提供了一个 IoC 容器用来初始化对象，解决对象间的依赖管理和对象的使用

#### 1. Spring 的模块

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

#### 2. Spring 的生态

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