package com.vcredit.springbootdbcustomauthentication.mapper;

import com.vcredit.springbootdbcustomauthentication.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author jitwxs
 * @date 2018/3/30 1:21
 */
@Mapper
public interface UsersMapper {
    @Select("SELECT * FROM users WHERE username = #{name}")
    Users selectByName(String name);

    @Update("UPDATE users SET password = #{password} WHERE username = #{username}")
    void updatePassword(@Param("password") String password, @Param("username") String username);
}
