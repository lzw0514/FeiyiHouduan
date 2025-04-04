package com.zhiwenliu.feiyihouduan.Controller;

import com.zhiwenliu.feiyihouduan.Service.UserService;
import com.zhiwenliu.feiyihouduan.pojo.User;
import com.zhiwenliu.feiyihouduan.pojo.dto.userLoginDTO;
import com.zhiwenliu.feiyihouduan.utils.JwtUtil;
import com.zhiwenliu.feiyihouduan.utils.Md5Util;
import com.zhiwenliu.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        String username=user.getUsername();
        String email=user.getEmail();
        String password=user.getPassword();
        //查询用户是否存在
        User u=userService.findByUserName(username);
        if(u==null){
            userService.register(username,email,password);
            return Result.success();
        }
        else{
            return Result.error("用户名已存在");
        }
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody userLoginDTO userLoginDTO){
        //查询用户是否存在
        String username= userLoginDTO.getUsername();
        String password= userLoginDTO.getPassword();
        System.out.println("用户名："+username);
        User logionUser=userService.findByUserName(username);
        if(logionUser==null){
            return Result.error("用户名错误");
        }
        log.debug("登录密码1：{}",Md5Util.getMD5String(password));
        log.debug("登录密码2：{}",logionUser.getPassword());
        //判断密码是否正确
        if(Md5Util.getMD5String(password).equals(logionUser.getPassword())){
            //登录成功，生成token
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",logionUser.getId());
            claims.put("username",logionUser.getUsername());
            String token= JwtUtil.genToken(claims);
            ValueOperations<String,String> ops=stringRedisTemplate.opsForValue();
            ops.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

}
