package com.springbootj_zhiyuan.mapper;


import com.springbootj_zhiyuan.entity.UrAndGp;
import org.apache.ibatis.annotations.Param;

public interface UrAndGpMapper {
    UrAndGp findByAll(@Param("account") String account,@Param("groupid") String groupid);
    int addUrAndGp(@Param("account") String account,@Param("groupid") String groupid);
    int deleteUrAndGp(@Param("account") String account,@Param("groupid") String groupid);
    int deleteByGp(@Param("groupid") String groupid);
    int deleteByAc(@Param("account") String account);
}
