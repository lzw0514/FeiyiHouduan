package com.zhiwenliu.feiyihouduan.mapper;

import com.github.pagehelper.Page;
import com.zhiwenliu.feiyihouduan.pojo.vo.HeritageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface HeritageMapper {


    /**
     * 分页查询
     * @param category
     * @param region
     * @return
     */
    Page<HeritageVO> pageQuery(String category, String region);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from heritage where id = #{id}")
    HeritageVO getById(Long id);
}
