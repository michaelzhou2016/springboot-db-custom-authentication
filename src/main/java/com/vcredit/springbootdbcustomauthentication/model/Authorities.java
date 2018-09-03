package com.vcredit.springbootdbcustomauthentication.model;

import java.io.Serializable;

/**
 * @Author zhouliliang
 * @Description:
 * @Date: Created in 2018/9/3 9:14
 */
public class Authorities implements Serializable {
    private static final long serialVersionUID = 2068112164546988172L;
    private String username;
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
