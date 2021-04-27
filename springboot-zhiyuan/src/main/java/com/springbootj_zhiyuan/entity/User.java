package com.springbootj_zhiyuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jinbin
 * @date 2018-07-08 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String account;
    String username;
    String password;
    String role;
    short vTime;
    
    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return  password.getBytes();
    }
    public String getPasswordtoStr() {
        return  password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password){
        this.password=password;
    }
    public void setUser(String account,String password){
        this.account=account;
        this.password=password;
    }

}
