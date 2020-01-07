package com.kang.imploded.quartz.jobs;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demo class
 *
 * @author 康东伟
 * @date 2019/05/14
 */
@Component("testJob")
@Transactional(rollbackFor = Exception.class)
public class TestJob {

    /**
     * 默认执行方法
     */
    public void execute(){
        System.out.println("执行了默认的方法");
    }
 }
