package com.bestvike.bvch.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Map;

@Service
public interface ZjbService {

    Map<String,String> fillData() throws ParseException;
    /*预售网签查询*/
    Map<String, Object> query(Integer begin_xh, Integer count, String password);
    /*存量房查询*/
    Map<String, Object> queryCLF(Integer begin_xh, Integer count, String password);
}
