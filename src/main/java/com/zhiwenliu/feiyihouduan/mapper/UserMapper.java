package com.zhiwenliu.feiyihouduan.mapper;


import com.zhiwenliu.feiyihouduan.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    /**
     * 添加用户
     * @param username
     * @param password
     * @param email
     */
    @Insert("insert into users(username,password,email,createTime,updatedTime)" +
            " values(#{username},#{password},#{email},now(),now())")
    void add(String username, String email,String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    User findByUserName(String username);


    /**
     * 更新用户信息
     * @param user
     */
    @Update("update users set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Update("update users set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl,Integer id);

    @Update("update users set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
