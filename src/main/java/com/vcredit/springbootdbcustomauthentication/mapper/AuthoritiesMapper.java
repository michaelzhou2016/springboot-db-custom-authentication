package com.vcredit.springbootdbcustomauthentication.mapper;

import com.vcredit.springbootdbcustomauthentication.model.Authorities;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jitwxs
 * @date 2018/3/30 1:23
 */
@Mapper
public interface AuthoritiesMapper {
    @Select("SELECT * FROM authorities WHERE username = #{name}")
    List<Authorities> selectByName(String name);
}
