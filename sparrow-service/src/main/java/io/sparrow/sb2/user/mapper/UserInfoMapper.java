package io.sparrow.sb2.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.sparrow.sb2.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户信息DAO
 *
 * @author Aaric, created on 2019-12-04T15:23.
 * @version 1.4.1-SNAPSHOT
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据用户ID查询角色权限列表
     *
     * @param userId 用户ID
     * @return
     */
    @Select("select distinct a.authority_code from user_info ui "
            + "left join user_role ur on ur.user_id = ui.id "
            + "left join role r on r.id = ur.role_id "
            + "left join role_authority ra on ra.role_id = ur.role_id "
            + "left join authority a on a.id = ra.authority_id "
            + "where ui.id = #{userId}")
    List<String> queryAuthorityList(@Param("userId") Integer userId);
}
