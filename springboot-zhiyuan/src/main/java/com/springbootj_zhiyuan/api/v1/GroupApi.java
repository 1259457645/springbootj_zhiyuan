package com.springbootj_zhiyuan.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.springbootj_zhiyuan.annotation.UserLoginToken;
import com.springbootj_zhiyuan.entity.Group;
import com.springbootj_zhiyuan.service.GroupService;
import com.springbootj_zhiyuan.service.TokenService;
import com.springbootj_zhiyuan.service.UrAndGpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
public class GroupApi {
    @Autowired
    GroupService groupService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UrAndGpService urAndGpService;

    @UserLoginToken
    @PostMapping("/addGroup")
    public Object addGroup(@RequestBody List<Group> list)
    {
        JSONObject jsonObject=new JSONObject();
        int res=0;
        for(Group group:list){
            Group useG= groupService.findByName(group.getGroupname());
            if(useG==null){
                res += groupService.addGroup(group.getGroupname(),group.getCompany());

            }
            else {
                jsonObject.put("message","添加失败，已存在该组织名"+group.getGroupname());
            }
        }
        if(res==list.size()){
            jsonObject.put("code","200");
            jsonObject.put("message","添加成功");
        }
        else{
            jsonObject.put("code","500");
            jsonObject.put("message","添加失败");
        }
        return jsonObject;
    }
    @UserLoginToken
    @PostMapping("/updateGroup")
    public Object updateGroup(@RequestBody Group group)
    {
        JSONObject jsonObject=new JSONObject();
        int res=groupService.updateGroup(group);
        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message","添加成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","添加失败");
        }
        return jsonObject;
    }
    @UserLoginToken
    @DeleteMapping("/deleteGroup/{groupid}")
    public Object deleteGroup(@PathVariable("groupid")String groupid)
    {
        JSONObject jsonObject=new JSONObject();
        int res =groupService.deleteGroup(groupid);
        String out="";
        if(res>0){
            jsonObject.put("code","200");
            out="删除组织成功\n";
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","删除失败，不存在该组织");
            return jsonObject;
        }
        res=urAndGpService.deleteByGp(groupid);
        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message",out+"成功删除用户和组织表中该组织信息");
        }
        return jsonObject;
    }
}
