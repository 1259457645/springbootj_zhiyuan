package com.springbootj_zhiyuan.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.springbootj_zhiyuan.annotation.UserLoginToken;
import com.springbootj_zhiyuan.entity.UrAndGp;
import com.springbootj_zhiyuan.entity.User;
import com.springbootj_zhiyuan.service.GroupService;
import com.springbootj_zhiyuan.service.UrAndGpService;
import com.springbootj_zhiyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/UrAndGp")
public class UrAndGpApi {
    @Autowired
    UrAndGpService urAndGpService;
    @Autowired
    UserService userService;
    @Autowired
    GroupService groupService;

    @UserLoginToken
    @PostMapping("/add")
    public Object addUrAndGp(@RequestBody List<UrAndGp> urAndGps){
        JSONObject jsonObject=new JSONObject();
        int res=0;
        for(UrAndGp urAndGp:urAndGps){
            User user=new User();
            user.setAccount(urAndGp.getAccount());
            if(userService.findByAccount(user)!=null&&
            groupService.findById(urAndGp.getGroupid())!=null){
                UrAndGp ur_search=urAndGpService.findByAll(urAndGp.getAccount(),urAndGp.getGroupid());
                if(ur_search==null){
                    res+=urAndGpService.addUrAndGp(urAndGp.getAccount(),urAndGp.getGroupid());
                }
            }
            else{
                jsonObject.put("code","500");
                jsonObject.put("message","未找到组织或者用户，请重新查看相关信息");
                return jsonObject;
            }

        }
        if(res==urAndGps.size()){
            jsonObject.put("code","200");
            jsonObject.put("message","添加成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","未能完全添加，有重复数据");
        }
        return jsonObject;
    }

    @UserLoginToken
    @DeleteMapping("/delete/{account}&{groupid}")
    public Object deleteGroup(@PathVariable("account")String account,@PathVariable("groupid")String groupid)
    {
        JSONObject jsonObject=new JSONObject();
        int res =urAndGpService.deleteUrAndGp(account,groupid);
        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message","删除成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","删除失败，数据库未改变");
        }
        return jsonObject;
    }
}
