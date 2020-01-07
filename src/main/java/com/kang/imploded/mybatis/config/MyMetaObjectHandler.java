package com.kang.imploded.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * MyMetaObjectHandler 自动填充处理器 实现MP提供的 MetaObjectHandler接口
 *
 * @author kangdongwei
 * @date 2019.11.18
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 该方法默认是所有类的添加方法都执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        /*
         * fieldName:写实体类中的属性名
         * fieldVal:需要填充的值
         */
        //该方法判断当前对象中是否含有insertTime属性，有就自动填充 没有就不会自动填充
        boolean hasSetter =metaObject.hasSetter("insertTime");
        if (hasSetter){
            setInsertFieldValByName("insertTime",LocalDateTime.now(),metaObject);
        }
        boolean hasSetter2 =metaObject.hasSetter("insertUser");
        if (hasSetter){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            Integer insertUser=new Long((Long)request.getSession().getAttribute("user_id")).intValue();
            setInsertFieldValByName("insertUser",insertUser,metaObject);
        }
    }

    /**
     * 该方法默认是所有类的更新方法都执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //该方法判断对象的对应属性是否赋值，如果没赋值就自动填充
        Object val=getFieldValByName("updateTime",metaObject);
        if (null==val){
            setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }

        Object val2=getFieldValByName("updateUser",metaObject);
        if (null==val2){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            Integer updateUser=new Long((Long)request.getSession().getAttribute("user_id")).intValue();
            setUpdateFieldValByName("updateUser",updateUser,metaObject);
        }
    }
}
