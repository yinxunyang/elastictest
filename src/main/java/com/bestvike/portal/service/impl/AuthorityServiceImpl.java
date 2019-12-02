package com.bestvike.portal.service.impl;

import com.bestvike.commons.dao.AbstractDao;
import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.commons.mybatis.Select;
import com.bestvike.commons.support.ServiceException;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.commons.util.EncryptUtil;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.frame.dao.DicUserInfoDao;
import com.bestvike.frame.data.DicUserInfo;
import com.bestvike.portal.dao.*;
import com.bestvike.portal.data.*;
import com.bestvike.portal.entity.DicMenuInfo;
import com.bestvike.portal.service.AuthorityService;
import com.bestvike.portal.service.BaseService;
import com.bestvike.portal.util.GCC;
import org.apache.ibatis.cache.Global;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class AuthorityServiceImpl extends BaseService implements AuthorityService {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysDeptDao sysDeptDao;
    @Autowired
    private SysUserRightDao sysUserRightDao;
    @Autowired
    private SysRoleRightDao sysRoleRightDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysMenuOperateDao sysMenuOperateDao;
    @Autowired
    private SysAppDao sysAppDao;
    @Autowired
    private SysUserTokenDao sysUserTokenDao;
    @Autowired
    private SysAreaDao sysAreaDao;
    @Autowired
    private DicUserInfoDao dicUserInfoDao;

    @Value("${app.authority.menuAuthority:true}")
    private Boolean menuAuthority;
    @Value("${app.authority.defaultRole:}")
    private String defaultRole;

    /**
     * 修改用户密码
     * @param sysUser
     * @return
     */
    @Override
    public int changePassword(SysUser sysUser) {
        String userId = sysUser.getUserId();
        SysUser userInDb = sysUserDao.selectByPrimaryKey(userId);
        if (userInDb == null) {
            throw new ServiceException("01", "用户非法");
        }

        try {
            if (!new BCryptPasswordEncoder().matches(EncryptUtil.base64Decode(sysUser.getOldUserPass()), userInDb.getUserPass())) {
                throw new ServiceException("02", "原密码不正确");
            }

            if("02".equals(userId.substring(0, 2)) || "07".equals(userId.substring(0, 2))){
                DicUserInfo dicUserInfo = dicUserInfoDao.selectByPrimaryKey(userId);
                dicUserInfo.setUserPass(EncryptUtil.base64Decode(sysUser.getUserPass()));
                dicUserInfoDao.updateByPrimaryKey(dicUserInfo);
            }

            sysUser.setUserPass(new BCryptPasswordEncoder().encode(EncryptUtil.base64Decode(sysUser.getUserPass())));


        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            throw new ServiceException("03", "原密码不正确");
        }

        return sysUserDao.updateNotNullByPrimaryKey(sysUser);
    }

    @Override
    public SysUser getUser(String userId) {
        Example example = new Example(SysUser.class);
        example.createCriteria().andEqualTo("userId", userId);
        example.or().andEqualTo("userAlias", userId);
        List<SysUser> sysUserList = sysUserDao.selectByExample(example);
        if (sysUserList != null && sysUserList.size() == 1) {
            return sysUserList.get(0);
        }
        return null;
        // return sysUserDao.selectByPrimaryKey(userId);
    }

    @Override
    public SysDept getDept(String deptId) {
        return sysDeptDao.selectByPrimaryKey(deptId);
    }

    @Override
    public int insertMenu(SysMenu sysMenu, String userIdAndName) {
        if (sysMenu != null) {
            sysMenu.setManageTime(DateUtil.getDateDate());
            sysMenu.setManageUser(userIdAndName);
            int ret = sysMenuDao.insert(sysMenu);
            if (sysMenu.getMenuOperates() != null && sysMenu.getMenuOperates().size() > 0) {
                for (SysMenuOperate sysMenuOperate : sysMenu.getMenuOperates()) {
                    sysMenuOperate.setOperateId(StringUtil.getId());
                    sysMenuOperate.setAppCode(sysMenu.getAppCode());
                    sysMenuOperate.setMenuId(sysMenu.getMenuId());
                    sysMenuOperateDao.insert(sysMenuOperate);
                }
            }
            return ret;
        }
        return 0;
    }

    @Override
    public int updateMenu(SysMenu sysMenu, String userIdAndName) {
        if (sysMenu != null) {
            sysMenu.setManageTime(DateUtil.getDateDate());
            sysMenu.setManageUser(userIdAndName);
            sysMenuDao.updateByPrimaryKey(sysMenu);
            Example example = new Example(SysMenuOperate.class);
            example.createCriteria().andEqualTo("menuId", sysMenu.getMenuId());
            int ret = sysMenuOperateDao.deleteByExample(example);
            if (sysMenu.getMenuOperates() != null && sysMenu.getMenuOperates().size() > 0) {
                for (SysMenuOperate sysMenuOperate : sysMenu.getMenuOperates()) {
                    sysMenuOperate.setOperateId(StringUtil.getId());
                    sysMenuOperate.setAppCode(sysMenu.getAppCode());
                    sysMenuOperate.setMenuId(sysMenu.getMenuId());
                    sysMenuOperateDao.insert(sysMenuOperate);
                }
            }
            return ret;
        }
        return 0;
    }

    @Override
    public int deleteMenu(String menuId, String userIdAndName) {
        if (!StringUtils.isEmpty(menuId)) {
            sysMenuDao.deleteByPrimaryKey(menuId);
            Example example = new Example(SysMenuOperate.class);
            example.createCriteria().andEqualTo("menuId", menuId);
            return sysMenuOperateDao.deleteByExample(example);
        }
        return 0;
    }

    /**
     * 查询系统对应的菜单
     * @param appCode
     * @return
     */
    @Override
    public List<SysMenu> selectMenus(String appCode) {

        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("appCode", appCode);
        example.orderBy("showOrder");

        List<SysMenu> menuList = sysMenuDao.selectByExample(example);
        if (menuList != null && menuList.size() > 0) {

            example = new Example(SysMenuOperate.class);
            example.createCriteria().andEqualTo("appCode", appCode);

            List<SysMenuOperate> operateList = sysMenuOperateDao.selectByExample(example);
            if (operateList != null && operateList.size() > 0) {

                for (SysMenuOperate sysMenuOperate : operateList) {

                    SysMenu sysMenu = new SysMenu();
                    sysMenu.setMenuType("button");
                    sysMenu.setMenuId(sysMenuOperate.getResourceId());
                    sysMenu.setMenuName(sysMenuOperate.getResourceName());
                    sysMenu.setParentMenu(sysMenuOperate.getMenuId());

                    menuList.add(sysMenu);
                }
            }
            return menuList;
        }
        return null;
    }

    @Override
    public List<SysMenu> authorityMenus(String userId, String appCode) {
        SysUser sysUser = sysUserDao.selectByPrimaryKey(userId);
        if (sysUser != null) {
            if (StringUtils.isEmpty(appCode)) {
                appCode = Global.appCode;
            }
            if (sysUser.getIsAdmin() != null && sysUser.getIsAdmin().equals("Y")) {
                Example example = new Example(SysMenu.class);
                example.createCriteria().andEqualTo("appCode", appCode);
                example.orderBy("showOrder");
                return sysMenuDao.selectByExample(example);
            }
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("userId", userId);
            parameterMap.put("appCode", appCode);
            return sysMenuDao.authorityMenus(parameterMap);
        }
        return null;
    }

    /**
     * 获取用户能进入的系统列表
     * @param userId
     * @return
     */
    @Override
    public List<SysApp> authorityApps(String userId) {

        SysUser sysUser = sysUserDao.selectByPrimaryKey(userId);
        if (sysUser != null) {

            if (sysUser.getIsAdmin() != null && sysUser.getIsAdmin().equals("Y")) {
                Example example = new Example(SysApp.class);
                example.orderBy("showOrder");

                return sysAppDao.selectByExample(example);
            }

            return sysAppDao.authorityApps(userId);
        }

        return null;
    }

    /**
     * 分页查询用户列表
     * @param queryParam
     * @return
     */
    public PaginationList<SysUser> pageSysUser(QueryParam queryParam) {

        return sysUserDao.pageSysUser(new Select(SysUser.class).isCustom(true).init(queryParam).getParameterMap(), new RowBounds(queryParam.getOffset(), queryParam.getLimit(), true));
    }

    /**
     * 新增用户
     * @param sysUser
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertSysUser(SysUser sysUser) throws NoSuchAlgorithmException {
        sysUser.setUserState(GCC.USER_STATE_NORMAL);

        if (StringUtils.isEmpty(sysUser.getUserId())) {
            sysUser.setUserId(String.valueOf(sysUserDao.selectId()));
        }

        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("userAlias", sysUser.getUserAlias());
        parameterMap.put("userId", sysUser.getUserId());
        List<SysUser> otherUserList = sysUserDao.selectOtherUserAlias(parameterMap);
        if (otherUserList != null && otherUserList.size() > 0) {
            throw new ServiceException("99", "登录名已存在！");
        }

        if (StringUtils.isEmpty(sysUser.getUserPass())) {
            sysUser.setUserPass(EncryptUtil.MD5Encode(sysUser.getUserId()));
        }
        sysUser.setUserPass(new BCryptPasswordEncoder().encode(sysUser.getUserPass()));

        return sysUserDao.insert(sysUser);
    }

    /**
     * 重置密码。
     *
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int resetPassword(String userId)  throws NoSuchAlgorithmException{
        SysUser sysUser = this.sysUserDao.selectByPrimaryKey(userId);
        sysUser.setUserPass(new BCryptPasswordEncoder().encode(EncryptUtil.MD5Encode(userId)));
        return sysUserDao.updateNotNullByPrimaryKey(sysUser);
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateSysUser(SysUser sysUser) {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("userAlias", sysUser.getUserAlias());
        parameterMap.put("userId", sysUser.getUserId());
        List<SysUser> otherUserList = sysUserDao.selectOtherUserAlias(parameterMap);
        if (otherUserList != null && otherUserList.size() > 0) {
            throw new ServiceException("99", "登录名已存在！");
        }

        return sysUserDao.updateNotNullByPrimaryKey(sysUser);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int modifySysUser(SysUser sysUser) {
        return sysUserDao.updateNotNullByPrimaryKey(sysUser);
    }

    /**
     * 维护角色信息
     * @param sysRole
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveSysRole(SysRole sysRole) {

        if (StringUtils.isEmpty(sysRole.getRoleId())) {

            Integer showOrder = sysRoleDao.selectOrder();
            sysRole.setRoleId(StringUtil.random(2) + StringUtil.fillLeft(showOrder, 2));
            sysRole.setRoleState("0000");
            sysRole.setShowOrder(showOrder);

            return sysRoleDao.insert(sysRole);

        } else {

            return sysRoleDao.updateNotNullByPrimaryKey(sysRole);
        }
    }

    @Override
    public Map<String, List<SysRole>> userRights(String userId) {
        //Example example = new Example(SysRole.class);
        //example.orderBy("showOrder");
        List<SysRole> sysRoleList = sysRoleDao.selectAllExtend();
        Map<String, List<SysRole>> map = new HashMap<>();
        if (sysRoleList != null && sysRoleList.size() > 0) {
            Example example = new Example(SysUserRight.class);
            example.createCriteria().andEqualTo("userId", userId);
            /// example.orderBy("roleCode");
            List<SysUserRight> sysUserRightList = sysUserRightDao.selectByExample(example);
            List<SysRole> roles = new ArrayList<>();
            List<SysRole> authed = new ArrayList<>();
            if (sysUserRightList != null && sysUserRightList.size() > 0) {
                for (SysRole sysRole : sysRoleList) {
                    boolean find = false;
                    for (SysUserRight sysUserRight : sysUserRightList) {
                        if (sysUserRight.getRoleId().equals(sysRole.getRoleId())) {
                            find = true;
                            authed.add(sysRole);
                            break;
                        }
                    }
                    if (!find) {
                        roles.add(sysRole);
                    }
                }
            } else {
                roles = sysRoleList;
            }
            map.put("roles", roles);
            map.put("authed", authed);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void userRights(Map<String, Object> parameterMap) {
        Example example = new Example(SysUserRight.class);
        example.createCriteria().andEqualTo("userId", parameterMap.get("userId"));
        sysUserRightDao.deleteByExample(example);
        List<String> roles = (List<String>) parameterMap.get("roles");
        if (roles != null && roles.size() > 0) {
            sysUserRightDao.insertSysUserRights(parameterMap);
        }
    }

    @Override
    public List<SysRoleRight> roleRights(String roleId) {
        Example example = new Example(SysRoleRight.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        return sysRoleRightDao.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void roleRights(Map<String, Object> parameterMap) {
        Example example = new Example(SysRoleRight.class);
        example.createCriteria().andEqualTo("appCode", parameterMap.get("appCode")).andEqualTo("roleId", parameterMap.get("roleId"));
        sysRoleRightDao.deleteByExample(example);
        List<Map<String, String>> menus = (List<Map<String, String>>) parameterMap.get("menus");
        if (menus != null && menus.size() > 0) {
            for (Map<String, String> map : menus) {
                SysRoleRight sysRoleRight = new SysRoleRight();
                sysRoleRight.setRightId(StringUtil.guid());
                sysRoleRight.setRoleId((String) parameterMap.get("roleId"));
                sysRoleRight.setMenuId(map.get("menuId"));
                sysRoleRight.setMenuOperates(map.get("menuOperates"));
                sysRoleRight.setAppCode((String) parameterMap.get("appCode"));
                sysRoleRightDao.insert(sysRoleRight);
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveSysUserToken(SysUserToken sysUserToken) {
        if (sysUserToken != null && sysUserToken.getClientId() != null) {
            if (sysUserTokenDao.selectByPrimaryKey(sysUserToken.getClientId()) == null) {
                sysUserTokenDao.insert(sysUserToken);
            } else {
                sysUserTokenDao.updateByPrimaryKey(sysUserToken);
            }
        }
        return -1;
    }

	@Override
	public String getCorpTypeByUser(SysUser sysUser) {
    	String corpType = sysDeptDao.getCorpTypeByDeptId(sysUser.getDeptId());
		return StringUtils.isEmpty(corpType) ? "00" : corpType;
	}

    /**
     * 查询用户对应的系统列表
     * @param userId
     * @param appCode
     * @return
     */
	@Override
	public List<SysRole> authorityAppRoles(String userId, String appCode) {

		Example example = new Example(SysUserRight.class);
		example.createCriteria().andEqualTo("userId", userId).andEqualTo("appCode", appCode);

		List<SysUserRight> listSysUserRight = sysUserRightDao.selectByExample(example);
		for (SysUserRight sysUserRight : listSysUserRight) {

			String roleId = sysUserRight.getRoleId();
			example = new Example(SysRole.class);
			example.createCriteria().andEqualTo("appCode", appCode).andEqualTo("roleId", roleId);

			List<SysRole> listSysRole = sysRoleDao.selectByExample(example);

			return listSysRole;
		}

		return null;
	}

    /**
     * 获取用户对应的系统菜单
     * @param userId
     * @param appCode
     * @return
     */
	@Override
	public List<DicMenuInfo> authorityBVMenus(String userId, String appCode) {

        Map<String, Object> parameterMap = new HashMap<String, Object>();

        parameterMap.put("userId", userId);
        parameterMap.put("appCode", appCode);

		return sysMenuDao.authorityBVMenus(parameterMap);
	}

	@Override
    public DicMenuInfo triggerBVMenu(String menuId) {
        DicMenuInfo dicMenuInfo = new DicMenuInfo();
        Example example = new Example(SysMenu.class);
        example.createCriteria().andEqualTo("menuId", menuId);
        List<SysMenu> listSysMenu = sysMenuDao.selectByExample(example);
        if (listSysMenu != null && listSysMenu.size() > 0) {
            SysMenu sysMenu = listSysMenu.get(0);
            dicMenuInfo.setMenuId(menuId);
            dicMenuInfo.setMenuLink(sysMenu.getMenuAddress());
            dicMenuInfo.setMenuName(sysMenu.getMenuName());
            dicMenuInfo.setMenuLevel(String.valueOf(sysMenu.getMenuLevel()));
            dicMenuInfo.setDisOrder(sysMenu.getShowOrder());
            dicMenuInfo.setParentMenuId(sysMenu.getParentMenu());
            dicMenuInfo.setModuleNo("01");
            dicMenuInfo.setIsShow("Y");
            dicMenuInfo.setIsLeaf("Y");
            dicMenuInfo.setOperateType(sysMenu.getUserOperates());
        }
        return dicMenuInfo;
    }

    /**
     * @Title：queryParentAreaByAreaCode
     * @Description：根据区域编号查询上级区域代码
     * @Author：ybb
     * @Date：2018/2/24 16:28
     * @param：areaCode 区域编号
     * @return parentArea 上级区域代码
     */
    @Override
    public String queryParentAreaByAreaCode(String areaCode) {

        //根据区域编号查询上级区域代码
        String parentArea = sysAreaDao.queryParentAreaByAreaCode(areaCode);
        if (StringUtils.isEmpty(parentArea) || parentArea.equals(GCC.BORTAL_PARENTAREA_ROOTNODE)){
            return null;
        }

        return parentArea;
    }

    /**
     * @Title：queryDatacenterIdByDeptId
     * @Description：根据机构ID查询用户对应的数据集中平台ID
     * @Author：ybb
     * @Date：2018/2/26 10:29
     * @param：deptId 机构ID
     * @return datacenterId 数据集中平台ID
     */
    @Override
    public String queryDatacenterIdByDeptId(String deptId) {

        String datacenterId = sysDeptDao.queryDatacenterIdByDeptId(deptId);

        return StringUtils.isEmpty(datacenterId) ? GCC.BORTAL_DEPTID : datacenterId;
    }
}
