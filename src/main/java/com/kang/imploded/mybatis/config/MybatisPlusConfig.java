package com.kang.imploded.mybatis.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.until.SecurityUntil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Properties;

/**
 * mybatis-plus 配置类
 * @author kang
 * @date 2019/11/06
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.kang.sys.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //分页插件
        PaginationInterceptor paginationInterceptor =  new PaginationInterceptor();
        //多租户解析器开始
        ArrayList<ISqlParser> sqlParserList= new ArrayList<>();
        TenantSqlParser tenantSqlParser=new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {

            @Override
            public Expression getTenantId() {
                //从session中获取当前租户id
                SecurityUser user = SecurityUntil.getUserInfo();
                return new LongValue(user.getTenantId());
            }
            //getTenantId()方法 标示多租户字段，返回租户id的字段名，不是类的属性名
            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            /**
             * 租户过滤器
             * @param tableName
             * @return
             */
            @Override
            public boolean doTableFilter(String tableName) {
                String tableUser="sys_user";
                String tableJob="schedule_job";
                String tableSysUserRole="sys_user_role";
                String tableSysRoleMenu="sys_role_menu";
                String tableSysRole="sys_role";
                String tableSysUserDetails="sys_user_details";
                String merchantPurchaseDetails="merchant_purchase_details";
                String merchantOrderDetails="merchant_order_details";
                //过滤掉user表 不做多租户查询
                if (tableUser.equals(tableName)){
                    return true;
                }else if (tableJob.equals(tableName)){
                    return true;
                }else if (tableSysUserRole.equals(tableName)){
                    return true;
                }else if (tableSysRoleMenu.equals(tableName)){
                    return true;
                }else if (tableSysRole.equals(tableName)){
                    return true;
                }else if (tableSysUserDetails.equals(tableName)){
                    return true;
                }else if (merchantOrderDetails.equals(tableName)){
                    return true;
                }
                else if (merchantPurchaseDetails.equals(tableName)){
                    return true;
                }
                return false;
            }
        });
        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        //过滤方法级的租户 不添加租户查询
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
                //如果方法名一样不增加租户信息
                if ("com.mybatis.sys.mapper.UserMapper.lists".equals(mappedStatement.getId())){
                    return true;
                }
                return false;
            }
        });
        return  paginationInterceptor;
    }

    /**
     * 打印 sql
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //格式化sql语句
        Properties properties = new Properties();
        properties.setProperty("format", "true");
        performanceInterceptor.setProperties(properties);
        return performanceInterceptor;
    }
}
