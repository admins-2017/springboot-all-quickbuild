package com.kang.imploded.quartz.jobs;

import com.kang.imploded.redis.RedisOperator;
import com.kang.sys.enums.RedisIndexEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kang
 * @version 1.0
 * @date 2020/3/18 16:44
 */
@Component("indexJob")
@Transactional(rollbackFor = Exception.class)
public class IndexDetailsDayJob {

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 默认执行方法
     */
    public void execute(){
        System.out.println("执行了清空当日登录ip数");

    }

}
