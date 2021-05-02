package com.springbootj_zhiyuan.mapper;

import com.springbootj_zhiyuan.entity.UrAndAc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UrAndAcMapper {
    List<UrAndAc> findByAccount(@Param("account") String account);
    List<UrAndAc> findByActivityId(@Param("activityid") String activityid);
    List<UrAndAc> findboolean(@Param("account") String account,@Param("activityid") String activityid);
    int addUrAndAc(@Param("account") String account,@Param("activityid") String activityid);
    int deleteUrAndAc(@Param("account") String account,@Param("activityid") String activityid);
    int deleteByAccount(@Param("account") String account);
    int deleteByActivity(@Param("activityid") String activityid);
}
