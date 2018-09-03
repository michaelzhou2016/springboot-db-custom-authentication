package com.vcredit.springbootdbcustomauthentication.model;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/9/3 9:12
 */
public class Users implements Serializable {
    private static final long serialVersionUID = 7857387955068619317L;
    private String username;
    private String password;
    private Boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
