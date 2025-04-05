package com.zhiwenliu.feiyihouduan.Controller;

import com.github.pagehelper.Page;
import com.zhiwenliu.feiyihouduan.Service.HeritageService;
import com.zhiwenliu.feiyihouduan.pojo.vo.HeritageVO;
import com.zhiwenliu.result.PageResult;
import com.zhiwenliu.result.Result;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heritage")
@Api(tags="非遗信息管理")
public class HeritageController {

    @Autowired
    private HeritageService heritageService;
    /**
     * 查询所有非遗信息
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询所有非遗信息")
    public Result<PageResult> list(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   @RequestParam(required = false) String category,
                                   @RequestParam(required = false) String region){
        PageResult pageResult = heritageService.pageQuery(page, pageSize, category, region);

        return Result.success(pageResult);
    }

    @GetMapping("/details/{id}")
    @ApiOperation("查询单个非遗信息")
    public Result<HeritageVO> details(@PathVariable Long id){
        HeritageVO heritageVO = heritageService.getById(id);
        return Result.success(heritageVO);
    }
}
