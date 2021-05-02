package com.springbootj_zhiyuan.service;

import com.springbootj_zhiyuan.entity.Group;
import com.springbootj_zhiyuan.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GroupService")
public class GroupService {
    @Autowired
    GroupMapper groupMapper;
    public Group findByName(String groupname) {return groupMapper.findByName(groupname);}
    public int addGroup(String groupname,String company) {return groupMapper.addGroup(groupname,company);
    }
    public int updateGroup(Group group){
        return  groupMapper.updateGroup(group.getGroupid(),group.getGroupname(),group.getCompany());
    }
    public int deleteGroup(String groupid){
        return groupMapper.deleteGroup(groupid);
    }
    public Group findById(String groupid){return groupMapper.findById(groupid);}
}
