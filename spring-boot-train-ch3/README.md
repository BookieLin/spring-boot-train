# 第三章 Spring 高级话题

## Spring Aware

### 说明

Spring 的依赖注入最大亮点就是你所有的 Bean 对 Spring 容器的存在是没有意识的。即你可以将你的容器替换成别的容器，如 Google Guice，这时 Bean 之间的耦合度很低。

但是在实际项目中，你不可避免的要用到 Spring 容器本省的功能资源，这时你的 Bean 必须要意识到 Spring 容器的存在，才能调用 Spring 所提供的资源，这就是所谓的 Spring Aware。其实 Spring Aware 本来就是 Spring 设计用来框架内部使用的，若使用了 Spring Aware，你的 Bean 将会和 Spring 框架耦合。

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

**运行接口**

[](url "title")
<img src="https://raw.githubusercontent.com/topsale/spring-boot-train/master/screenshots/ch2-006.png">