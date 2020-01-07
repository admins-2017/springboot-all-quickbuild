package com.kang.imploded.quartz.config;

import com.kang.imploded.quartz.beans.QuartzService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务器启动时开启执行任务配置类
 * @author kang
 */
@Component
public class JobSchedule implements CommandLineRunner {

    @Resource
    private QuartzService taskService;

    /**
     * 任务调度开始
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("任务调度开始==============任务调度开始");
        taskService.timingTask();
        System.out.println("任务调度结束==============任务调度结束");
    }
}