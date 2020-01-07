package com.kang.imploded.aspect;

import java.lang.annotation.*;

/**
 * @author kang
 * @date 2019-11-22
 *
 * 操作记录的注解
 *
 * @Target({ElementType.METHOD,ElementType.TYPE}) 表示修饰的注解可以用在类/接口上，也可以用在方法上
 *
 * @Retention(RetentionPolicy.RUNTIME) 元注解，定义注解被保留策略，一般有三种策略
 * 1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
 * 2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候北欧抛弃，这个是默认的声明周期
 * 3、RetentionPolicy.RUNTIME 表示这是一个运行时注解，即运行起来之后，才可以获取注解中相关的信息
 *
 * @Documented 表示当执行javadoc的时候，该注解会生成相关文档
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String description() default "";
}
