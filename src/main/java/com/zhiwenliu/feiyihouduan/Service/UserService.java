package com.zhiwenliu.feiyihouduan.Service;

import com.zhiwenliu.feiyihouduan.pojo.User;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     * @param email
     */
    void register(String username, String email,String password);
}
