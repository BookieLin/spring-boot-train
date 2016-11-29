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
