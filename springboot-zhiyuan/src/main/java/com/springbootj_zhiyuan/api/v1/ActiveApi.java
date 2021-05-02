package com.springbootj_zhiyuan.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.springbootj_zhiyuan.annotation.UserLoginToken;
import com.springbootj_zhiyuan.entity.Active;
import com.springbootj_zhiyuan.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/active")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ActiveApi {
    @Autowired
    ActiveService activeService;

    @UserLoginToken
    @GetMapping("/find")
    public Object findActive(@RequestParam("title")String title,@RequestHeader("token") String token){
        JSONObject jsonObject=new JSONObject();
        List<Active> ur_search=activeService.findByTitle(title);
        if(ur_search.size()==0){
            jsonObject.put("code","500");
            jsonObject.put("message","活动不存在");
        }
        else{
            jsonObject.put("code","200");
            jsonObject.put("message",ur_search);
        }
        return jsonObject;
    }
    @UserLoginToken
    @PostMapping("/add")
    public Object addActive(@RequestBody List<Active> actives){
        JSONObject jsonObject=new JSONObject();
        int res=0;
        for(Active active:actives){
            res+=activeService.addActive(active);
        }
        if(res==actives.size()){
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
    @PostMapping("/update")
    public Object updateActive(@RequestBody Active active,@RequestParam("activityid") String activityid){
        JSONObject jsonObject=new JSONObject();
        int res=0;
        active.setActivityid(activityid);
        res+=activeService.updateActive(active);

        if(res==1){
            jsonObject.put("code","200");
            jsonObject.put("message","添加成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","没有更新");
        }
        return jsonObject;
    }
    //@UserLoginToken
    @DeleteMapping("/delete/{activityid}")
    public Object deleteActive(@PathVariable("activityid")String activityid){
        JSONObject jsonObject=new JSONObject();
        int res=0;
        res+=activeService.deleteActive(activityid);

        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message","删除成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","没有更新");
        }
        return jsonObject;
    }

    @UserLoginToken
    @GetMapping("")
    public Object sendUserTable(@RequestParam("pagesize") int pagesize,@RequestParam("pagenum") int pagenum){
        JSONObject jsonObject=new JSONObject();
        List<Active> queryResult = activeService.findByPage(pagesize, pagenum);
        jsonObject.put("code","200");
        jsonObject.put("data",queryResult);
        return jsonObject;
    }
}
