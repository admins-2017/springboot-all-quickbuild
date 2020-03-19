package com.kang.sys.mapper;

import com.kang.sys.entity.UserDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.LoginSuccessVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface UserDetailsMapper extends BaseMapper<UserDetails> {

    /**
     * 根据用户id查询用户详情
     * @param userId
     * @return
     */
    @Select("select sud.user_details_url,sud.user_details_sex,sud.user_details_addr,sud.user_details_mail,\n" +
            "\t\tsud.user_details_tel,ms.shop_name from sys_user_details sud\n" +
            "\t\tLEFT JOIN merchant_shop ms \n" +
            "\t\tON sud.shop_id = ms.shop_id\n" +
            "\t\tWHERE sud.user_id =#{userId}")
    LoginSuccessVo getUserDetailsById(@Param("userId") Long userId);
}
