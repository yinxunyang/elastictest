package com.bestvike.portal.service;

import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.portal.data.SysApp;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.data.SysMenu;
import com.bestvike.portal.data.SysRole;
import com.bestvike.portal.data.SysRoleRight;
import com.bestvike.portal.data.SysUser;
import com.bestvike.portal.data.SysUserToken;
import com.bestvike.portal.entity.DicMenuInfo;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.stereotype.Service;

@Service
public interface AuthorityService {

    /**
     * 修改用户密码
     * @param sysUser
     * @return
     */
    public int changePassword(SysUser sysUser);

    public SysUser getUser(String userId);
    public SysDept getDept(String deptId);
    public int insertMenu(SysMenu sysMenu, String userIdAndName);
    public int updateMenu(SysMenu sysMenu, String userIdAndName);
    public int deleteMenu(String menuId, String userIdAndName);

    /**
     * 查询系统对应的菜单
     * @param appCode
     * @return
     */
    public List<SysMenu> selectMenus(String appCode);

    public List<SysMenu> authorityMenus(String userId, String appCode);

    /**
     * 获取用户能进入的系统列表
     * @param userId
     * @return
     */
    public List<SysApp> authorityApps(String userId);

    /**
     * 分页查询用户列表
     * @param queryParam
     * @return
     */
    public PaginationList<SysUser> pageSysUser(QueryParam queryParam);

    /**
     * 新增用户
     * @param sysUser
     * @return
     * @throws NoSuchAlgorithmException
     */
    public int insertSysUser(SysUser sysUser) throws NoSuchAlgorithmException;

    /**
     * 重置密码。
     * @param userId
     * @return
     */
    public int resetPassword(String userId) throws NoSuchAlgorithmException;

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    public int updateSysUser(SysUser sysUser);

    public int modifySysUser(SysUser sysUser);

    /**
     * 维护角色信息
     * @param sysRole
     * @return
     */
    public int saveSysRole(SysRole sysRole);

    public Map<String, List<SysRole>> userRights(String userId);
    public void userRights(Map<String, Object> parameterMap);
    public List<SysRoleRight> roleRights(String roleId);
    public void roleRights(Map<String, Object> parameterMap);
    public int saveSysUserToken(SysUserToken sysUserToken);
    public String getCorpTypeByUser(SysUser sysUser);

    /**
     * 查询用户对应的系统列表
     * @param userId
     * @param appCode
     * @return
     */
    public List<SysRole> authorityAppRoles(String userId, String appCode);

    /**
     * 获取用户对应的系统菜单
     * @param userId
     * @param appCode
     * @return
     */
    public List<DicMenuInfo> authorityBVMenus(String userId, String appCode);
    public DicMenuInfo triggerBVMenu(String menuId);
    
    /**
     * @Title：queryParentAreaByAreaCode
     * @Description：根据区域编号查询上级区域代码
     * @Author：ybb
     * @Date：2018/2/24 16:28
     * @param：areaCode 区域编号
     * @return parentArea 上级区域代码
     */
    public String queryParentAreaByAreaCode(String areaCode);

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
