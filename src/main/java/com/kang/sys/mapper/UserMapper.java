package com.kang.sys.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kang.sys.dto.UserWithDetails;
import com.kang.sys.entity.Menu;
import com.kang.sys.entity.Role;
import com.kang.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kang.sys.vo.MenuTreeVo;
import com.kang.sys.vo.db.UserWithDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-06
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户ID查询角色集合
     * @Author Sans
     * @CreateTime 2019/9/18 18:01
     * @Param  userId 用户ID
     * @Return List<Role> 角色名集合
     */
    List<Role> selectRoleByUserId(Long userId);

    /**
     * 通过用户ID查询权限集合
     * @Param  userId 用户ID
     * @Return List<Menu> 角色名集合
     */
    List<Menu> selectMenuByUserId(Long userId);


    /**
     * 生成用户功能树
     * @param userId
     * @return
     */
    List<MenuTreeVo> selectMenuTreeByUserId(Long userId);

    /**
     * 查询对应角色权限
     * @param roleId
     * @return
     */
    List<MenuTreeVo> selectMenuTreeByRoleId(Long roleId);


    /**
     *  查询当前租户下用户基本信息
     * @param page
     * @param tenantId
     * @return
     */
    @Select("select su.user_id,su.username,su.`status`,sr.role_id, sr.role_name,sr.role_code,sr.del_flag,sr.role_description\n" +
            "\t\t\t,sud.user_details_sex,sud.user_details_mail,sud.user_details_tel\n" +
            "\t\tfrom sys_user_role sur\n" +
            "\t\tLEFT JOIN sys_user su\n" +
            "\t\tON su.user_id = sur.user_id\n" +
            "\t\tLEFT JOIN sys_role sr\n" +
            "\t\ton sr.role_id = sur.role_id\n" +
            "\t\tLEFT JOIN sys_user_details sud\n" +
            "\t\ton su.user_id = sud.user_id\n" +
            "\t\twhere su.tenant_id = #{tenantId}")
    List<UserWithDetailsVo> queryUserWithDetails(Page<UserWithDetailsVo> page, @Param("tenantId")Long tenantId);


    /**
     * 模糊查询 根据用户名，邮箱，电话
     * @param tenantId
     * @param likeName
     * @return
     */
    @Select("select su.user_id,su.username,su.`status`,sr.role_id, sr.role_name,sr.role_code,sr.del_flag,sr.role_description\n" +
            "            ,sud.user_details_sex,sud.user_details_mail,sud.user_details_tel\n" +
            "            from sys_user_role sur\n" +
            "            LEFT JOIN sys_user su\n" +
            "            ON su.user_id = sur.user_id\n" +
            "            LEFT JOIN sys_role sr\n" +
            "            on sr.role_id = sur.role_id\n" +
            "            LEFT JOIN sys_user_details sud\n" +
            "            on su.user_id = sud.user_id\n" +
            "            where su.tenant_id =#{tenantId} and CONCAT(su.username,sud.user_details_mail,sud.user_details_tel) LIKE #{likeName} ")
    List<UserWithDetailsVo> findUserWithLikeName(@Param("tenantId")Long tenantId,@Param("likeName")String likeName);
}
