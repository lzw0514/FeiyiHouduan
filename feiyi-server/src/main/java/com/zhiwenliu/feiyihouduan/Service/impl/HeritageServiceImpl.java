package com.zhiwenliu.feiyihouduan.Service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhiwenliu.feiyihouduan.Service.HeritageService;
import com.zhiwenliu.feiyihouduan.mapper.HeritageMapper;
import com.zhiwenliu.feiyihouduan.pojo.vo.HeritageVO;
import com.zhiwenliu.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HeritageServiceImpl implements HeritageService {

    @Autowired
    private HeritageMapper heritageMapper;

    /**
     * 条件查询
     * @param page
     * @param pageSize
     * @param category
     * @param region
     * @return
     */
    public PageResult pageQuery(int page, int pageSize, String category, String region) {
        log.info("分页查询，页码：{}，每页记录数：{}，分类：{}，地区：{}", page, pageSize, category, region);
        PageHelper.startPage(page, pageSize);
        Page<HeritageVO> pages=heritageMapper.pageQuery(category,region);
        return new PageResult(pages.getTotal(),pages.getResult());

    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public HeritageVO getById(Long id) {
        HeritageVO heritageVO = heritageMapper.getById(id);
        return heritageVO;
    }
}
