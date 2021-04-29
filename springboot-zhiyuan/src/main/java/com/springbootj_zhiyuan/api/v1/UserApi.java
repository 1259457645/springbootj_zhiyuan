package com.springbootj_zhiyuan.api.v1;

import com.alibaba.fastjson.JSONObject;
import com.springbootj_zhiyuan.annotation.UserLoginToken;
import com.springbootj_zhiyuan.entity.User;
import com.springbootj_zhiyuan.service.TokenService;
import com.springbootj_zhiyuan.service.UrAndGpService;
import com.springbootj_zhiyuan.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jinbin
 * @date 2018-07-08 20:45
 */
@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    UrAndGpService urAndGpService;

    //登录
    @PostMapping("/userLogin")
    public Object login( @RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByAccount(user);
        if(userForBase==null){
            jsonObject.put("code","500");
            jsonObject.put("message","登录失败,用户不存在");
            return jsonObject;
        }else {
            if (!BCrypt.checkpw(user.getPasswordtoStr(), userForBase.getPasswordtoStr())){
                jsonObject.put("code","500");
                jsonObject.put("message","登录失败,密码错误");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("code","200");
                jsonObject.put("token", token);
                jsonObject.put("data", userForBase);
                return jsonObject;
            }
        }
    }
    //添加单个用户
    @UserLoginToken
    @PostMapping("/user/addUser")
    public Object addUser(@RequestBody User user){
        String password="123456";
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByAccount(user);
        if(userForBase==null){
            String encodePwd = BCrypt.hashpw(password, BCrypt.gensalt()); // 加密
            user.setPassword(encodePwd);
            userService.insertUser(user);
            jsonObject.put("code","200");
            jsonObject.put("message","添加成功");
            return jsonObject;
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","添加失败,用户已存在");
            return jsonObject;
        }
    }
    //添加多个用户
    @UserLoginToken
    @PostMapping("/user/addUsers")
    public Object addMoreUser(@RequestBody List<User> list) {

        String password = "123456";
        for (User user : list) {
            JSONObject jsonObject=new JSONObject();
            User userForBase=userService.findByAccount(user);
            if(userForBase==null){
                String encodePwd = BCrypt.hashpw(password, BCrypt.gensalt()); // 加密
                user.setPassword(encodePwd);
                userService.insertUser(user);
                jsonObject.put("code","200");
                jsonObject.put("message","添加成功");
                return jsonObject;
            }
            else {
                jsonObject.put("code","500");
                jsonObject.put("message","添加失败,用户已存在");
                return jsonObject;
            }
        }
        return  new JSONObject();
    }
    @UserLoginToken
    @DeleteMapping("/user/deleteUser/{id}")
    public Object deletePollutionById(@PathVariable("id")String id){
        JSONObject jsonObject=new JSONObject();
        User user=userService.findUserById(id);
        int aa=userService.deleteUser(id);
        if(aa>0){
            jsonObject.put("code","200");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","删除失败");
            return jsonObject;
        }
        aa=urAndGpService.deleteByAc(user.getAccount());
        if(aa>0){
            jsonObject.put("message","删除掉其他残余信息");
        }
        else
            jsonObject.put("message","未发现其他残余信息");
        return jsonObject;
    }
    @UserLoginToken
    @PostMapping("/user/changePw/{id}")
    public Object updatePassword(@PathVariable("id")String id,@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        String encodePwd = BCrypt.hashpw(user.getPasswordtoStr(), BCrypt.gensalt()); // 加密
        user.setPassword(encodePwd);
        int res=userService.updatePw(id,user.getPasswordtoStr());
        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message","修改成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }
    @UserLoginToken
    @PostMapping("/user/change")
    public Object change_u(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        int res=0;
        if(user.getRole()==null){
            user.setRole("3");
        }
        if(res>0){
            jsonObject.put("code","200");
            jsonObject.put("message","修改成功");
        }
        else {
            jsonObject.put("code","500");
            jsonObject.put("message","删除失败");
        }
        return jsonObject;
    }
    @UserLoginToken
    @GetMapping("/user")
    public Object sendUserTable(@RequestParam("pagesize") int pagesize,@RequestParam("pagenum") int pagenum){
        JSONObject jsonObject=new JSONObject();
        List<User> queryResult = userService.findByPage(pagesize, pagenum);
        jsonObject.put("code","200");
        jsonObject.put("data",queryResult);
        return jsonObject;
    }


}
