package com.zhiwenliu.feiyihouduan.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名

    @JsonIgnore
    private String password;//密码

    @NotEmpty
    @Email
    private String email;//邮箱
    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$")
    private String phone;//手机号

    private enum role{
        admin,
        user
    }
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
