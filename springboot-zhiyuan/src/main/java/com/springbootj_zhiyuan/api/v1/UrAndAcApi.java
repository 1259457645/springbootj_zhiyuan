package com.springbootj_zhiyuan.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.springbootj_zhiyuan.annotation.UserLoginToken;
import com.springbootj_zhiyuan.entity.Active;
import com.springbootj_zhiyuan.entity.UrAndAc;
import com.springbootj_zhiyuan.entity.User;
import com.springbootj_zhiyuan.service.ActiveService;
import com.springbootj_zhiyuan.service.UrAndAcService;
import com.springbootj_zhiyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UrAndAcApi {
    @Autowired
    UrAndAcService urAndAcService;
    @Autowired
    UserService userService;
    @Autowired
    ActiveService activeService;

    @UserLoginToken
    @GetMapping("/active/findusers")
    public Object findByActivityId(@RequestParam("activityid") String activityid){
        JSONObject jsonObject=new JSONObject();
        List<UrAndAc> urAndAcs=urAndAcService.findByActivityId(activityid);
        if(urAndAcs.size()==0) {
            jsonObject.put("code","500");
            jsonObject.put("message","无法查询到该活动");
            return jsonObject;
        }
        List<User> users=new ArrayList<>();
        for(UrAndAc urAndAc:urAndAcs){
            User user=new User();
            user.setAccount(urAndAc.getAccount());
            user = userService.findByAccount(user);
            users.add(user);
        }
        jsonObject.put("code","200");
        jsonObject.put("data",users);
        return  jsonObject;
    }

    @UserLoginToken
    @GetMapping("/user/findacs")
    public Object findUsersActivity(@RequestParam("account") String account){
        JSONObject jsonObject=new JSONObject();
        List<UrAndAc> urAndAcs=urAndAcService.findByAccount(account);
        if(urAndAcs.size()==0) {
            jsonObject.put("code","500");
            jsonObject.put("message","无法查询到参加任何志愿服务活动");
            return jsonObject;
        }
        List<Active> actives = new ArrayList<>();
        for(UrAndAc urAndAc:urAndAcs){
            Active active=new Active();
            active= activeService.findById(urAndAc.getActivityid());
            actives.add(active);
        }
        jsonObject.put("code","200");
        jsonObject.put("data",actives);
        return  jsonObject;
    }
    @UserLoginToken
    @GetMapping("/user/addacs")
    public Object addUrAndAc(@RequestParam("account")String account,
                             @RequestParam("activityid")String activityid){
        JSONObject jsonObject=new JSONObject();
        Active active= activeService.findById(activityid);
        if(active==null){
            jsonObject.put("code","500");
            jsonObject.put("message","无法查询到该活动");
        }
        else{
            if(urAndAcService.findBoolean(account, activityid).size()==0){
                int num = urAndAcService.addUrAndAc(account,activityid);
                if(num>0){
                    jsonObject.put("code","200");
                    jsonObject.put("message","添加成功");
                }
            }
            else
            {
                jsonObject.put("code","500");
                jsonObject.put("message","添加资源服务活动失败，请重新添加");
            }

        }
        return  jsonObject;
    }

}
