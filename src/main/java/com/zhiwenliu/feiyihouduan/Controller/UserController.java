package com.zhiwenliu.feiyihouduan.Controller;

import com.zhiwenliu.feiyihouduan.Service.UserService;
import com.zhiwenliu.feiyihouduan.pojo.Result;
import com.zhiwenliu.feiyihouduan.pojo.User;
import com.zhiwenliu.feiyihouduan.pojo.UserDTO;
import com.zhiwenliu.feiyihouduan.utils.JwtUtil;
import com.zhiwenliu.feiyihouduan.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
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
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        //查询用户是否存在
        User u=userService.findByUserName(username);
        if(u==null){
            userService.register(username,password);
            return Result.success();
        }
        else{
            return Result.error("用户名已存在");
        }
    }

    /**
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserDTO userDTO){
        //查询用户是否存在
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        System.out.println("用户名："+username);
        User logionUser=userService.findByUserName(username);
        if(logionUser==null){
            return Result.error("用户名错误");
        }
        log.debug("登录密码1：{}",Md5Util.getMD5String(password));
        log.debug("登录密码2：{}",logionUser.getPassword());
        //判断密码是否正确
        if(password.equals(logionUser.getPassword())){
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
