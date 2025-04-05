package com.zhiwenliu.feiyihouduan.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeritageVO {
    private Long id;  // 对应 BIGINT 类型
    //非遗姓名
    private String name;  // 对应 VARCHAR(255)
    //非遗种类
    private String category;  // 对应 VARCHAR(255)
    //非遗地址
    private String applicationArea;  // 对应 VARCHAR(255)
    //非遗保护单位
    private String protectionUnit;  // 对应 VARCHAR(255)

    //非遗简介
    private String project_description;

}
