package com.bestvike.bvch.controller;


import com.bestvike.bvch.service.ZjbService;
import com.bestvike.portal.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ZjbController extends BaseController {

    public ZjbController() {
        super("21");
    }

    @Autowired
    private ZjbService zjbService;

    public Map<String,String> fillData() throws ParseException {
        return zjbService.fillData();
    }


    //查询接口
    @RequestMapping(value = "/index",method = RequestMethod.GET )
    //页面应该会传过来数据用于填充查询
    public Map<String,Object> query(@RequestParam Integer begin_xh, @RequestParam Integer count, @RequestParam String password) {
        return zjbService.query(begin_xh, count, password);
    }


    //存量房查询接口
    @RequestMapping(value = "/indexCLF",method = RequestMethod.GET )
    //页面应该会传过来数据用于填充查询
    public Map<String,Object> queryCLF(@RequestParam Integer begin_xh, @RequestParam Integer count, @RequestParam String password) {
        return zjbService.queryCLF(begin_xh, count, password);
    }
}
