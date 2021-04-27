package com.springbootj_zhiyuan.mapper;

import com.springbootj_zhiyuan.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jinbin
 * @date 2018-07-08 20:44
 */
public interface UserMapper {
    User findByAccount(@Param("account") String account);
    User findUserById(@Param("id") String id);
    User findUserByAccount(@Param("account") String account);
    void insertUser(@Param("account") String account,@Param("username") String username,@Param("password") String password);
    int deleteUser(@Param("id") String id);
    int updatePw(@Param("id") String id,@Param("password") String password);
    List<User> findbyPage();
}
