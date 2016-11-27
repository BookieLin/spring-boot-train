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
