package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.RecodeTime;
import org.springframework.stereotype.Repository;

@Repository
public interface RecodeTimeDao {
    void saveRecode(RecodeTime recodeTime);
    void updateRecode(RecodeTime recodeTime);

    /**
     * @Author: yinxunyang
     * @Description: 查询RecodeTime日期最新的数据
     * @Date: 2019/9/17 9:51
     */
    RecodeTime selectRecodeTimeByLastTime();
}
