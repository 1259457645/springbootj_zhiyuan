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
    String url_img;
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



    public void setPassword(String password){
        this.password=password;
    }


}
