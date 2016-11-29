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
