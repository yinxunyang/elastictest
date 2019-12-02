package com.bestvike.portal.dao;

import com.bestvike.datacenter.entity.Version;
import com.bestvike.portal.data.SysDept;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDeptDao extends BaseMapper<SysDept> {
    public String selectMaxDeptId(String parentDept);
    public List<SysDept> selectByAreaCode(String areaCode);
    public int insertFromDatacenter(SysDept sysDept);
    public int updateVersion(Version version);
    public int updateFromDatacenter(SysDept sysDept);
    public int updateForDelete(String deptId);
    public String getCorpTypeByDeptId(String deptId);

    /**
     * @Title：queryDatacenterIdByDeptId
     * @Description：根据机构ID查询用户对应的数据集中平台ID
     * @Author：ybb
     * @Date：2018/2/26 10:29
     * @param：deptId 机构ID
     * @return datacenterId 数据集中平台ID
     */
    public String queryDatacenterIdByDeptId(String deptId);
}
