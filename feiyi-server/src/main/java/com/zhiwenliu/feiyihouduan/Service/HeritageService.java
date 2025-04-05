package com.zhiwenliu.feiyihouduan.Service;
import com.zhiwenliu.feiyihouduan.pojo.vo.HeritageVO;
import com.zhiwenliu.result.PageResult;
public interface HeritageService {
     /**
     * 条件查询
     * @param page
     * @param pageSize
     * @param category
     * @param region
     * @return
     */
    PageResult pageQuery(int page, int pageSize, String category, String region);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    HeritageVO getById(Long id);
}
