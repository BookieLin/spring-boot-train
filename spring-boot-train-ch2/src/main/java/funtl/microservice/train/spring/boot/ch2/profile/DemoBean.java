package funtl.microservice.train.spring.boot.ch2.profile;

/**
 * 示例 Bean
 */
public class DemoBean {
    private String content;

    public DemoBean(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
