package com.zhiwenliu.feiyihouduan.Service.impl;

import com.zhiwenliu.feiyihouduan.Service.UserService;
import com.zhiwenliu.feiyihouduan.mapper.UserMapper;
import com.zhiwenliu.feiyihouduan.pojo.User;
import com.zhiwenliu.feiyihouduan.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUserName(String username) {
        User u= userMapper.findByUserName(username);
        return u;
    }

    public void register(String username,String email, String password) {
        //加密密码
        String md5String = Md5Util.getMD5String(password);
        //添加用户
        userMapper.add(username,email,md5String);
    }
}
