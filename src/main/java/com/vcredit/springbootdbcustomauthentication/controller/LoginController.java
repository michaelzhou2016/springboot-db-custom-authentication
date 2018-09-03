package com.vcredit.springbootdbcustomauthentication.controller;

import com.vcredit.springbootdbcustomauthentication.mapper.UsersMapper;
import com.vcredit.springbootdbcustomauthentication.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jitwxs
 * @date 2018/3/30 1:30
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("/")
    public String showHome() {
        return "home.html";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login.html";
    }

    @RequestMapping("/login/error")
    @ResponseBody
    public Msg<?> loginError(HttpServletRequest request) {
        AuthenticationException exception = (AuthenticationException)request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");

        return new Msg<>(false, exception.getMessage(), exception.toString());
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register.html";
    }

    @PostMapping("/register")
    public Object register() throws Exception{
        usersMapper.updatePassword(new BCryptPasswordEncoder().encode("admin@password"), "admin");
        return "redirect:/login";
    }

    @GetMapping("/userInfo")
    @ResponseBody
    public Msg<?> getSelfUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(authentication.toString());
        logger.info(authentication.getAuthorities().toString());
        return new Msg<>(true,null,authentication);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String printAdmin() {
        return "如果你看见这句话，说明你有ROLE_ADMIN角色";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public String printUser() {
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @ResponseBody
    public String printTeacher() {
        return "如果你看见这句话，说明你有ROLE_TEACHER角色";
    }

    @GetMapping("/adminOrTeacher")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    @ResponseBody
    public String printAdminAndTeacher() {
        return "如果你看见这句话，说明你有ROLE_ADMIN或ROLE_TEACHER和角色";
    }
}