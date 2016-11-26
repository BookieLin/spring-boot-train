# Spring Boot 培训教程

## 前言

Spring 在 Java EE 开发中是实际意义上的标准，但我们在开发 Spring 的时候可能会遇到以下问题：
1. 大量配置文件的定义
2. 与第三方软件整合的技术问题

Spring 每个新版本的推出都以减少配置作为自己的主要目标，例如：
1. 推出 @Component、@Service、@Repository、@Controller 注解在类上申明Bean
2. 推出 @Configuration、@Bean 的Java配置来替代 xml 配置

Spring 在提升 Java EE 开发效率的脚步上从未停止过，而 Spring Boot 的推出是具有颠覆和划时代意义的。Spring Boot 具有以下特征：
1. 遵循“约定优于配置”原则，使用 Spring Boot 只需很少的配置，大部分时候可以使用默认配置
2. 项目快速搭建，可无配置整合第三方框架
3. 完全不使用 xml 配置，只使用自动配置和 Java Config
4. 内嵌 Servlet（如 Tomcat）容器，应用可用 jar 包运行（java -jar）
5. 运行中应用状态的监控