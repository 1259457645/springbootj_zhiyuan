package com.springbootj_zhiyuan.mapper;

import com.springbootj_zhiyuan.entity.Group;
import org.apache.ibatis.annotations.Param;

public interface GroupMapper {
    Group findByName(@Param("groupname") String groupname);
    int addGroup(@Param("groupname") String groupname,@Param("company") String company);
    int updateGroup(@Param("groupid") String groupid,@Param("groupname") String groupname,@Param("company") String company);
    int deleteGroup(@Param("groupid") String groupid);
    Group findById(@Param("groupid") String groupid);
}
