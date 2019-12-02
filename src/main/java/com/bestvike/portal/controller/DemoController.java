package com.bestvike.portal.controller;

import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.portal.data.DemoTable;
import com.bestvike.portal.service.DemoService;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
public class DemoController extends BaseController {
    @Autowired
    private DemoService demoService;
    @Autowired
    private RestTemplate restTemplate;

    public DemoController() {
        super("99");
    }

    @RequestMapping(value = "/pageDemoTable", method = RequestMethod.POST)
    public PaginationList<DemoTable> pageDemoTable(@RequestBody QueryParam queryParam) {
        return demoService.pageDemoTable(queryParam);
    }

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public String demo1() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        Date data = new Date();
        HttpEntity<Object> reqEntity = new HttpEntity<Object>(data, headers);
        ResponseEntity<Date> result = restTemplate.exchange("http://localhost:8000/api/demo/demo2", HttpMethod.POST, reqEntity, Date.class);
        System.out.println(result);
        return "1-" + result.getBody();
    }

    @RequestMapping(value = "/demo2", method = RequestMethod.POST)
    public Date demo2(@RequestBody Date date) {
        return new Date();
    }
}
