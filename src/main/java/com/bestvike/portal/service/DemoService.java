package com.bestvike.portal.service;

import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.portal.data.DemoTable;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.stereotype.Service;

/**
 * Created by lihua on 2017/9/19.
 */
@Service
public interface DemoService {
    public PaginationList<DemoTable> pageDemoTable(QueryParam queryParam);
}
