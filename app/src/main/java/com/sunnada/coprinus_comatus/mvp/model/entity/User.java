package com.sunnada.coprinus_comatus.mvp.model.entity;

/**
 * 作者: 张少林 on 2017/6/10 0010.
 * 邮箱:1083065285@qq.com
 */

public class User {

    /**
     * id : 1
     * username : 张少林
     * password : 123456
     */

    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
