package com.bestvike.bvch.controller;


import com.bestvike.portal.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ZjbController extends BaseController {

    public ZjbController() {
        super("21");
    }

    //存量房查询接口
    @RequestMapping(value = "/indexCLF",method = RequestMethod.GET )
    //页面应该会传过来数据用于填充查询
    public Map<String,Object> queryCLF(@RequestParam Integer begin_xh, @RequestParam Integer count, @RequestParam String password) {
        return null;
    }
}
