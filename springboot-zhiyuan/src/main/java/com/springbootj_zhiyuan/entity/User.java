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



}
