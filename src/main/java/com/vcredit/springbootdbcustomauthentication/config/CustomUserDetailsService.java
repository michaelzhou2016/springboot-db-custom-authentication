package com.vcredit.springbootdbcustomauthentication.config;

import com.vcredit.springbootdbcustomauthentication.mapper.AuthoritiesMapper;
import com.vcredit.springbootdbcustomauthentication.mapper.UsersMapper;
import com.vcredit.springbootdbcustomauthentication.model.Authorities;
import com.vcredit.springbootdbcustomauthentication.model.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jitwxs
 * @date 2018/3/30 9:17
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private AuthoritiesMapper authoritiesMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Users user = usersMapper.selectByName(s);

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 添加权限
        List<Authorities> userRoles = authoritiesMapper.selectByName(s);
        if (!CollectionUtils.isEmpty(userRoles)) {
            authorities.addAll(AuthorityUtils.createAuthorityList(userRoles.stream().map(Authorities::getAuthority).collect(Collectors.toList()).toArray(new String[userRoles.size()])));
        }

        // 返回UserDetails实现类
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
