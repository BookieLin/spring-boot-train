# 第三章 Spring 高级话题

## Spring Aware

### 说明

Spring 的依赖注入最大亮点就是你所有的 Bean 对 Spring 容器的存在是没有意识的。即你可以将你的容器替换成别的容器，如 Google Guice，这时 Bean 之间的耦合度很低。

但是在实际项目中，你不可避免的要用到 Spring 容器本身的功能资源，这时你的 Bean 必须要意识到 Spring 容器的存在，才能调用 Spring 所提供的资源，这就是所谓的 Spring Aware。其实 Spring Aware 本来就是 Spring 设计用来框架内部使用的，若使用了 Spring Aware，你的 Bean 将会和 Spring 框架耦合。

Spring 提供的 Aware 接口如表 3-1 所示

**表 3-1 Spring 提供的 Aware 接口**

| 接口                           | 说明                                                                              |
| ------------------------------ | --------------------------------------------------------------------------------- |
| BeanNameAware                  | 获得到容器中 Bean 的名称                                                          |
| BeanFactoryAware               | 获得当前 bean factory，这样可以调用容器的服务                                     |
| ApplicationContextAware*       | 当前的 application context，这样可以调用容器的服务                                |
| MessageSourceAware             | 获得 message source，这样可以获得文本信息                                         |
| ApplicationEventPublisherAware | 应用时间发布器，可以发布事件，第二章的 DemoPublisher 也可以实现这个接口来发布事件 |
| ResourceLoaderAware            | 获得资源加载器，可以获得外部资源文件                                              |

Spring Aware 的目的是为了让 Bean 获得 Spring 容器的服务。因为 Application Context 接口集成了 MessageSource 接口、ApplicationEventPublisher 接口和 ResourceLoader 接口，所以 Bean 继承 ApplicationContextAware 可以获得 Spring 容器的所有服务，但原则上我们还是用到什么接口就实现什么接口

### 示例

**resources 目录下增加 test.txt 内容随意**

**增加 commons-io 依赖**

```xml

<dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
</dependency>

```

**Spring Aware 演示 Bean**

```java

package funtl.microservice.train.spring.boot.ch3.aware;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Spring Aware 演示 Bean
 * (1) 实现 BeanNameAware、ResourceLoaderAware 接口，获得 Bean 名称和资源加载的服务
 * (2) 实现 ResourceLoaderAware 需重写 setResourceLoader
 * (3) 实现 BeanNameAware 需重写 setBeanName
 */
@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware { // 1
    private String beanName;
    private ResourceLoader loader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) { // 2
        this.loader = resourceLoader;
    }

    @Override
    public void setBeanName(String name) { // 3
        this.beanName = name;
    }

    public void outputResult() {
        System.out.println("Bean 的名称为：" + beanName);

        Resource resource = loader.getResource("classpath:test.txt");
        try {
            System.out.println("ResourceLoader 加载的文件内容为：" + IOUtils.toString(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

**配置类**

```java

package funtl.microservice.train.spring.boot.ch3.aware;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch3.aware")
public class AwareConfig {
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch3.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AwareConfig.class);

        AwareService awareService = context.getBean(AwareService.class);
        awareService.outputResult();

        context.close();
    }
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch3-001.png">

## 多线程

### 说明

Spring 通过任务执行器（TaskExecutor）来实现多线程和并发编程。使用 ThreadPoolTaskExecutor 可实现一个基于线程池的 TaskExecutor 。而实际开发中任务一般是非阻碍的，即异步的，所以我们要在配置类中通过 @EnableAsync 开启对异步任务的支持，并通过在实际执行的 Bean 的方法中使用 @Async 注解来声明其是一个异步任务

### 示例

**配置类**

```java

package funtl.microservice.train.spring.boot.ch3.taskexecutor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 配置类
 * (1) 利用 @EnableAsync 注解开启异步支持
 * (2) 配置类实现 AsyncConfigurer 接口并重写 getAsyncExecutor()，并返回一个 ThreadPoolTaskExecutor，这样我们就获得了一个基于线程池的 TaskExecutor
 */
@Configuration
@ComponentScan("funtl.microservice.train.spring.boot.ch3.taskexecutor")
@EnableAsync // 1
public class TaskExecutorConfig implements AsyncConfigurer { // 2
	@Override
	public Executor getAsyncExecutor() { // 2
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(5);
		taskExecutor.setMaxPoolSize(10);
		taskExecutor.setQueueCapacity(25);
		taskExecutor.initialize();
		return taskExecutor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}
}

```

**任务执行类**

```java

package funtl.microservice.train.spring.boot.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 任务执行类
 * (1) 通过 @Async 注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的方法都是异步方法，而这里方法自动被注入使用 ThreadPoolTaskExecutor 作为 TaskExecutor
 */
@Service
public class AsyncTaskService {
	@Async // 1
	public void executorAsyncTask(Integer i) {
		System.out.println("执行异步任务：" + i);
	}

	@Async
	public void executorAsyncTaskPlus(Integer i) {
		System.out.println("执行异步任务+1：" + i);
	}
}

```

**运行**

```java

package funtl.microservice.train.spring.boot.ch3.taskexecutor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 结果是并发执行而不是顺序执行的
 */
public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

		AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);

		for (int i = 0 ; i < 10 ; i++) {
			asyncTaskService.executorAsyncTask(i);
			asyncTaskService.executorAsyncTaskPlus(i);
		}

		context.close();
	}
}

```

**运行结果**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch3-002.png">