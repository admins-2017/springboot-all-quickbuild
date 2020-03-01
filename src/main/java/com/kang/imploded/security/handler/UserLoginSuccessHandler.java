package com.kang.imploded.security.handler;

import com.kang.imploded.json.JSONResult;
import com.kang.imploded.redis.RedisOperator;
import com.kang.imploded.redis.entity.SecurityWithToken;
import com.kang.imploded.security.entity.SecurityUser;
import com.kang.imploded.security.jwt.JWTConfig;
import com.kang.imploded.security.jwt.JWTTokenUtil;
import com.kang.sys.vo.LoginSuccessVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理类
 * @Author Sans
 * @CreateTime 2019/10/3 9:13
 */
@Slf4j
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private RedisOperator redisOperator;

    /**
     * 登录成功返回结果
     * @Author kang
     * @CreateTime 2019/10/3 9:27
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // 组装JWT
        SecurityUser selfUserEntity =  (SecurityUser) authentication.getPrincipal();
        String token = JWTTokenUtil.createAccessToken(selfUserEntity);
        token = JWTConfig.tokenPrefix + token;
        //为前端提供返回vo
        LoginSuccessVo successVo = new LoginSuccessVo();
        BeanUtils.copyProperties(selfUserEntity,successVo);
        successVo.setToken(token);
        //暂时写死
        successVo.setEmail("11067985926@qq.com");
        successVo.setMobile("15149633654");
        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("meta",JSONResult.loginOk());
        resultData.put("data",successVo);

        //封装对象保存到redis
        SecurityWithToken securityWithToken=new SecurityWithToken();
        BeanUtils.copyProperties(selfUserEntity,securityWithToken);
        securityWithToken.setToken(token);
        request.getSession().setAttribute("user",selfUserEntity);
        request.getSession().setAttribute("user_id",selfUserEntity.getUserId());
        request.getSession().setAttribute("tenant_id",selfUserEntity.getTenantId());
        String userKey="user:token:"+selfUserEntity.getUserId();
        redisOperator.setObj(userKey,securityWithToken,604800);

        JSONResult.responseJson(response,resultData);
    }
}