package com.kang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 主启动类
 *
 * @author kang
 * @date 2019/11/06
 */
@SpringBootApplication
@MapperScan("com.kang.*.mapper")
@EnableAsync
public class QuickBuildApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickBuildApplication.class, args);
    }

}
