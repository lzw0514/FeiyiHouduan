package com.zhiwenliu.feiyihouduan.pojo;




import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;//密码

    @NotEmpty
    @Email
    private String email;//邮箱

    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
