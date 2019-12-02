package com.bestvike.portal.service.impl;

import com.bestvike.commons.mybatis.QueryColumn;
import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.commons.mybatis.Select;
import com.bestvike.commons.support.ServiceException;
import com.bestvike.portal.dao.DemoTableDao;
import com.bestvike.portal.data.DemoTable;
import com.bestvike.portal.service.DemoService;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lihua on 2017/9/19.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoTableDao demoTableDao;

    @Override
    public PaginationList<DemoTable> pageDemoTable(QueryParam queryParam) {
        Select select = new Select("DemoTable");
        List<QueryColumn> filterMoreList = queryParam.getParamList();
        if (filterMoreList != null) {
            for (int i=0; i<filterMoreList.size(); i++) {
                QueryColumn queryColumn = filterMoreList.get(i);
                // 判断字段名
                if (queryColumn.getName().equals("name")) {
                    select.getParameterMap().put("name", queryColumn.getValue());
                }
                filterMoreList.remove(i);
            }
            queryParam.setParamList(filterMoreList);
        }
        select.init(queryParam);
        if (queryParam.getFilter() != null) {
            if (queryParam.getFilter().getValue().equals("abc")) {
                throw new ServiceException("01", "查询条件异常");
            }
        }
        return demoTableDao.pageDemoTable(select.getParameterMap(), new RowBounds(queryParam.getOffset(), queryParam.getLimit(), true));
    }

    // @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
}
