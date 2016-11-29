package funtl.microservice.train.spring.boot.ch3.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计划任务执行类
 * (1) 通过 @Scheduled 声明该方法是计划任务，使用 fixRate 属性每隔固定时间执行
 * (2) 使用 cron 属性可按照指定时间执行，本例指定的是每天 19 点 47 分执行；cron 是 UNIX 和类 UNIX（Linux）系统下的定时任务
 */
@Service
public class ScheduledTaskService {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000) // 1
	public void reportCurrentTime() {
		System.out.println("每隔5秒执行一次：" + dateFormat.format(new Date()));
	}

	@Scheduled(cron = "0 47 19 ? * *") // 2
	public void fixTimeExecution() {
		System.out.println("在指定时间：" + dateFormat.format(new Date()) + " 执行");
	}
}
