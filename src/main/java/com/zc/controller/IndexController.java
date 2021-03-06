package com.zc.controller;

import com.zc.domain.*;
import com.zc.service.*;
import com.zc.utils.CommonResult;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @Autowired
    private GlobalNavService globalNavService;

    @Autowired
    private GlobalBannerService globalBannerService;

    @Autowired
    private GlobalAccessService globalAccessService;


    /*
    * 获取首页所有配置信息
    * */
    @GetMapping(value = "/list")
    public CommonResult getGlobalList() {

        Map<String, Object> map = new HashMap<String, Object>();

        List<GlobalNav> navList = globalNavService.getGlobalNavList();
        List<GlobalBanner> bannerList = globalBannerService.getGlobalBannerList();
        int allVisitCount = globalAccessService.getAllVisitCount();

        map.put("navList", navList);
        map.put("bannerList", bannerList);
        map.put("allVisitCount", allVisitCount);

        return CommonResult.success(map);
    }

    /*
    * 获取单独导航栏列表
    * */
    @GetMapping(value = "/navList")
    public CommonResult getNavList() {

        return CommonResult.success(globalNavService.getGlobalNavList());
    }

    /*
    * 获取访问量列表
    * @params startTime
    * @params endTime
    * */
    @GetMapping(value = "/visitCountList")
    public CommonResult getGlobalAssessList(@RequestParam(name = "startTime", required = false) String startTime,
                                            @RequestParam(name = "endTime", required = false) String endTime) {

        return CommonResult.success(globalAccessService.getGlobalAccessList(startTime, endTime));
    }

    /*
    * 根据文章类型id查询所有文章列表
    * @params articleTypeId
    * */
    @GetMapping(value = "/allArticleList")
    public CommonResult findByArticleTypeId(HttpServletRequest request) {

        return indexService.findByArticleTypeId(request.getParameter("articleTypeId"));
    }

    /*
    * 根据文章标题模糊查询
    * @params articleTitle
    * */
    @GetMapping(value = "/findByArticleTitle")
    public CommonResult findByArticleTitle (HttpServletRequest request) {

        return indexService.findByArticleTitle(request.getParameter("articleTitle"));
    }
}
