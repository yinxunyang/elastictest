package com.bestvike.portal.controller;

import com.bestvike.bvch.BvchGlobal;
import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.commons.support.ServiceException;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.portal.data.*;
import com.bestvike.portal.entity.DicMenuInfo;
import com.bestvike.portal.entity.LoginParam;
import com.bestvike.portal.entity.User;
import com.bestvike.portal.service.AuthorityService;
import com.bestvike.portal.util.GCC;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class AuthorityController extends BaseController {

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private HttpServletResponse httpServletResponse;
	@Autowired
	private RedisTemplate redisTemplate;

	@Value("${app.authority.login.validate-show-times:3}")
	private Integer allowRetryTimes;
	@Value("${app.authority.menu.operate:false}")
    private Boolean authOperate;
	@Value("${app.layout.menu.expand-first:false}")
	private Boolean expandFirstMenu;

	public AuthorityController() {
		super("01");
	}

	/**
	 * 验证是否需要重新登录
	 * @param user
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/authority/check", method = RequestMethod.GET)
	public User check(Principal user) throws IOException {

		Integer failTimes = (Integer) httpSession.getAttribute("retryTimes");
		if (failTimes != null && failTimes > allowRetryTimes) {
			httpServletResponse.sendError(403, "需要输入验证码");
			return null;
		}

		if (user != null && !StringUtils.isEmpty(user.getName()) && httpSession.getAttribute("userId") != null) {
			User loginUser = new User();
			loginUser.setIsAdmin((String) httpSession.getAttribute("isAdmin"));
			loginUser.setApps(authorityService.authorityApps((String) httpSession.getAttribute("userId")));
			return loginUser;
			// return authorityService.authorityApps((String) httpSession.getAttribute("userId"));
		}

		httpServletResponse.sendError(401, "需要登录");
		return null;
	}

	@RequestMapping(value = "/authority/info", method = RequestMethod.GET)
	public Map<String, Object> info(Principal user) {
	    Map<String, Object> resultMap = new HashMap<>();
		String userId = (String) httpSession.getAttribute("userId");
		if (userId != null) {
			SysUser sysUser = authorityService.getUser(userId);
			if (sysUser != null) {
				// Map<String, Object> userMap = new HashMap<>();
				// userMap.put("user", sysUser);
				SysDept sysDept = authorityService.getDept(sysUser.getDeptId());
				if (sysDept != null) {
					// userMap.put("dept", sysDept);
					sysUser.setDeptName(sysDept.getDeptName());
					if (!StringUtils.isEmpty(sysDept.getDataRange()) && !StringUtils.isEmpty(sysUser.getAreaCode())) {
						String areaRange = "";
						String areaCode = sysUser.getAreaCode();
						switch (sysDept.getDataRange()) {
							case "9999":
								// 全部
								areaRange = "*";
								break;
							case "1000":
								// 本级及下级
								if (areaCode.endsWith("0000")) {
									areaRange = areaCode.substring(0, 2) + "%";
								} else if (areaCode.endsWith("00")) {
									areaRange = areaCode.substring(0, 4) + "%";
								} else {
									areaRange = areaCode;
								}
								break;
							case "0000":
								// 仅本级
								areaRange = areaCode;
								break;
						}
                        sysUser.setAreaRange(areaRange);
					}
				}
				sysUser.setUserPass("******");

                resultMap.put("user", sysUser);

                // app config
                Map<String, Object> appConfigMap = new HashMap<>();
                appConfigMap.put("authOperate", authOperate);
				appConfigMap.put("expandFirstMenu", expandFirstMenu);
                resultMap.put("config", appConfigMap);
				return resultMap;
			}
		}
		return resultMap;
	}

	/**
	 * 系统登录
	 * @param loginParam
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/authority/login", method = RequestMethod.POST)
	public User login(@RequestBody(required = false) LoginParam loginParam, Principal user) {

		if (user != null && !StringUtils.isEmpty(user.getName())) {

			String userId = user.getName();
			if (loginParam != null && !StringUtils.isEmpty(loginParam.getSimulate())) {
				// 判断是否为管理员用户
				SysUser sysUserTemp = authorityService.getUser(userId);
				if (sysUserTemp != null && sysUserTemp.getIsAdmin() != null && sysUserTemp.getIsAdmin().equals("Y")) {
					userId = loginParam.getSimulate();
				}
			}

			SysUser sysUser = authorityService.getUser(userId);
			if (sysUser == null) {
				throw new ServiceException("99", "登录失败");
			}
			if (StringUtils.isEmpty(sysUser.getDeptId())) {
				httpSession.setAttribute("corpType", GCC.BORTAL_DEPTID);
			} else {
				String corpType = authorityService.getCorpTypeByUser(sysUser);
				httpSession.setAttribute("corpType", corpType);
			}
			System.out.println(httpSession.getId());
			//用户id
			httpSession.setAttribute("userId", sysUser.getUserId());
			//用户别名
			httpSession.setAttribute("userAlias", sysUser.getUserAlias());
			//用户别名
			httpSession.setAttribute("aliasName", sysUser.getUserAlias());
			//用户名
			httpSession.setAttribute("userName", sysUser.getUserName());
			//城市代码
			httpSession.setAttribute("cityCode", authorityService.queryParentAreaByAreaCode(sysUser.getAreaCode()));
			//行政区代码
			httpSession.setAttribute("areaCode", sysUser.getAreaCode());
			//行政区代码
			httpSession.setAttribute("divisionCode", sysUser.getAreaCode());
			//机构编号
			httpSession.setAttribute("deptId", sysUser.getDeptId());
			//是否为超级管理员
			httpSession.setAttribute("isAdmin", sysUser.getIsAdmin());
			//数据平台ID
			httpSession.setAttribute("datacenterId", authorityService.queryDatacenterIdByDeptId(sysUser.getDeptId()));

			logger.info(sysUser.getUserName() + " log in");

			httpSession.removeAttribute("retryTimes");
			httpSession.removeAttribute("validateCode");
			// return authorityService.authorityApps(sysUser.getUserId());

			User loginUser = new User();
			loginUser.setIsAdmin(sysUser.getIsAdmin());
			loginUser.setApps(authorityService.authorityApps(sysUser.getUserId()));

			return loginUser;
			// String authority = StringUtils.collectionToCommaDelimitedString(((Authentication) user).getAuthorities());
		}
		return null;
	}

	/**
	 * 用户注册
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/authority/userRegister", method = RequestMethod.POST)
	public SysUser registerUser(@RequestBody SysUser sysUser) throws NoSuchAlgorithmException {
		if (sysUser != null && !StringUtils.isEmpty(sysUser.getUserAlias())) {
			SysUser dbUser = authorityService.getUser(sysUser.getUserAlias());
			if (dbUser != null) {
				throw new ServiceException("99", "用户已注册");
			}
			authorityService.insertSysUser(sysUser);
			return sysUser;
		}
		throw new ServiceException("99", "用户名不能为空");
	}

	@RequestMapping(value = "/authority/identity", method = RequestMethod.POST)
	public User identity(Principal user) {
		if (user != null && !StringUtils.isEmpty(user.getName())) {
			SysUser sysUser = authorityService.getUser(user.getName());
			if (sysUser == null) {
				throw new ServiceException("99", "登录失败");
			}
			if (StringUtils.isEmpty(sysUser.getDeptId())) {
				httpSession.setAttribute("corpType", "00");
			} else {
				String corpType = authorityService.getCorpTypeByUser(sysUser);
				httpSession.setAttribute("corpType", corpType);
			}

			// httpSession.setAttribute("cityCode", dicUserInfo.getCityCode());
			httpSession.setAttribute("userId", sysUser.getUserId());
			httpSession.setAttribute("userAlias", sysUser.getUserAlias());
			httpSession.setAttribute("userName", sysUser.getUserName());
			httpSession.setAttribute("areaCode", sysUser.getAreaCode());
			httpSession.setAttribute("deptId", sysUser.getDeptId());
			httpSession.setAttribute("isAdmin", sysUser.getIsAdmin());

			logger.info(sysUser.getUserName() + " log in");
			httpSession.removeAttribute("retryTimes");
			httpSession.removeAttribute("validateCode");
			// return authorityService.authorityApps(sysUser.getUserId());
			User loginUser = new User();
			loginUser.setUserId(sysUser.getUserId());
			loginUser.setUserAlias(sysUser.getUserAlias());
			loginUser.setUserName(sysUser.getUserName());
			return loginUser;
			// String authority = StringUtils.collectionToCommaDelimitedString(((Authentication) user).getAuthorities());
		}
		return null;
	}

	/**
	 * 退出登录
	 * @param user
	 */
	@RequestMapping(value = "/authority/logout", method = RequestMethod.POST)
	public void logout(Principal user) {
		// 执行不到
		logger.info("logout");
	}

	// oauth2用
	@RequestMapping(value = "/authority/register", method = RequestMethod.POST)
	public SysUserToken register(@RequestBody SysUser sysUser) {
		if (sysUser != null) {
			SysUser sysUserDb = authorityService.getUser(sysUser.getUserId());
			if (sysUserDb == null) {
				throw new ServiceException("99", "用户不存在");
			}
			if (!new BCryptPasswordEncoder().matches(sysUser.getUserPass(), sysUserDb.getUserPass())) {
				throw new ServiceException("99", "用户名或密码错");
			}
			SysUserToken sysUserToken = new SysUserToken();
			sysUserToken.setClientId(sysUser.getClientId());
			sysUserToken.setUserId(sysUser.getUserId());
			sysUserToken.setClientSecret(new BCryptPasswordEncoder().encode(sysUser.getClientId() + sysUser.getUserId()));
			sysUserToken.setRegisterTime(DateUtil.getDateDate());

			authorityService.saveSysUserToken(sysUserToken);

			return sysUserToken;
		}
		return null;
	}

	/**
	 * 查询系统对应的菜单
	 * @param appCode
	 * @return
	 */
	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	public List<SysMenu> selectMenus(@RequestParam String appCode) {
		return authorityService.selectMenus(appCode);
	}

	@RequestMapping(value = "/authority/menus", method = RequestMethod.GET)
	public List<SysMenu> menus(@RequestParam(value = "appCode", required = false) String appCode) {
		return authorityService.authorityMenus(super.getUserId(), appCode);
	}

	/**
	 * 老业务系统从portal中获取菜单
	 * @param appCode
	 * @return
	 */
	@RequestMapping(value = "/authority/bvmenus", method = RequestMethod.POST)
	public Map<String, Object> bvmenus(@RequestParam(value = "appCode") String appCode) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("menuList", authorityService.authorityBVMenus(super.getUserId(), appCode));

		return resultMap;
	}

	@RequestMapping(value = "/authority/triggerMenu", method = RequestMethod.POST)
	public Map<String, Object> triggerMenu(@RequestParam(value = "isMenu", required = false) String isMenu, @RequestParam(value = "menuId", required = false) String menuId,
		@RequestParam(value = "menuLink", required = false) String menuLink) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(isMenu) && isMenu.equals("true")) {
			DicMenuInfo dicMenuInfo = authorityService.triggerBVMenu(menuId);
			httpSession.setAttribute("curMenuId", menuId);
			resultMap.put("dicMenuInfo", dicMenuInfo);
		} else if (!StringUtils.isEmpty(menuLink)) {
			resultMap.put("menuLink", menuLink);
		}
		return resultMap;
	}

	@RequestMapping(value = "/authority/menu", method = RequestMethod.POST)
	public int insertMenu(@RequestBody SysMenu sysMenu) {
		return authorityService.insertMenu(sysMenu, super.getUserIdAndName());
	}

	@RequestMapping(value = "/authority/menu", method = RequestMethod.PUT)
	public int updateMenu(@RequestBody SysMenu sysMenu) {
		return authorityService.updateMenu(sysMenu, super.getUserIdAndName());
	}

	@RequestMapping(value = "/authority/menu/{menuId}", method = RequestMethod.DELETE)
	public int deleteMenu(@PathVariable String menuId) {
		return authorityService.deleteMenu(menuId, super.getUserIdAndName());
	}

	/**
	 * 获取用户能进入的系统列表
	 * @return
	 */
	@RequestMapping(value = "/authority/apps", method = RequestMethod.GET)
	public List<SysApp> apps() {
		return authorityService.authorityApps(super.getUserId());
	}

	/**
	 * jiankai add 获取用户系统角色放到redis里
	 * 进入系统
	 */
	@RequestMapping(value = "/authority/appRoles", method = RequestMethod.GET)
	public void appRoles(@RequestParam String appCode) {

		//判断是否是超级管理员用户
		String isAdmin = (String) httpSession.getAttribute("isAdmin");
		if (!StringUtils.isEmpty(isAdmin) && isAdmin.equals("Y")) {
			return;
		}

		//判断用户是否有进入系统的角色
		List<SysRole> listSysRoles = authorityService.authorityAppRoles(super.getUserId(), appCode);
		if (listSysRoles == null || listSysRoles.size() == 0) {
			throw new ServiceException("99", "当前用户无该系统可用角色，无法登入系统");
		}

		//将角色代码、角色名称放入Redis中
		redisTemplate.opsForValue().set(appCode + "_" + super.getUserId() + "_RoleCode", listSysRoles.get(0).getRoleId());
		redisTemplate.opsForValue().set(appCode + "_" + super.getUserId() + "_RoleName", listSysRoles.get(0).getRoleName());
	}

	/**
	 * 修改密码
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value = "/authority/changePassword", method = RequestMethod.POST)
	public int changePassword(@RequestBody SysUser sysUser) {

		sysUser.setUserId((String) httpSession.getAttribute("userId"));

		return authorityService.changePassword(sysUser);
	}

	/**
	 * 用户管理-查询用户列表
	 * @param queryParam
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public PaginationList<SysUser> listUser(@RequestBody QueryParam queryParam) {

		queryParam.setSessionMap(super.getUser());

		return authorityService.pageSysUser(queryParam);
	}

	/**
	 * 用户管理-新增用户
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public int insertUser(@RequestBody SysUser sysUser) throws NoSuchAlgorithmException {

		return authorityService.insertSysUser(sysUser);
	}

	/**
	 * 重置密码。
	 * @return
	 */
	@RequestMapping(value = "/authority/resetPassword", method = RequestMethod.POST)
	public int resetPassword(@RequestBody SysUser sysUser) throws NoSuchAlgorithmException{
		return authorityService.resetPassword(sysUser.getUserId());
	}

	/**
	 * 用户管理-修改用户
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public int updateUser(@RequestBody SysUser sysUser) {

		return authorityService.updateSysUser(sysUser);
	}

	@RequestMapping(value = "/user/modify", method = RequestMethod.PUT)
	public int modifyUser(@RequestBody SysUser sysUser) {
		return authorityService.updateSysUser(sysUser);
	}

	/**
	 * 维护角色信息
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public int saveSysRole(@RequestBody SysRole sysRole) {
		return authorityService.saveSysRole(sysRole);
	}

	@RequestMapping(value = "/authority/userRights", method = RequestMethod.GET)
	public Map<String, List<SysRole>> userRights(@RequestParam("userId") String userId) {
		return authorityService.userRights(userId);
	}

	@RequestMapping(value = "/authority/userRights", method = RequestMethod.POST)
	public int updateUserRights(@RequestBody Map<String, Object> parameterMap) {
		if (parameterMap != null) {
			authorityService.userRights(parameterMap);
		}
		return 0;
	}

	@RequestMapping(value = "/authority/roleRights", method = RequestMethod.GET)
	public List<SysRoleRight> roleRights(@RequestParam("roleId") String roleId) {
		return authorityService.roleRights(roleId);
	}

	@RequestMapping(value = "/authority/roleRights", method = RequestMethod.POST)
	public void updateRoleRights(@RequestBody Map<String, Object> parameterMap) {
		authorityService.roleRights(parameterMap);
	}

	@RequestMapping("/authority/generateCode")
	public void generateCode() throws IOException {
		httpServletResponse.setContentType("image/jpeg");
		// 验证码图片的宽度。
		int width = 100;
		// 验证码图片的高度。
		int height = 38;
		// 验证码字符个数
		int codeNum = 4;
		int xx = 0;
		// 字体高度
		int fontSize;
		int codeY;
		String codeSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		// 干扰线
		int lineNum = 0;

		// 初始化
		xx = (width - 2) / codeNum;
		fontSize = height - 2 < xx ? height - 2 : xx;
		codeY = height - 10;

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();

		// 创建一个随机数生成器类
		Random random = new Random();

		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, buffImg.getWidth(), buffImg.getHeight());

		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.PLAIN, fontSize);
		// 设置字体。
		g.setFont(font);

		// 画边框。
//        g.setColor(Color.BLACK);
//        g.drawRect(0, 0, width - 1, height - 1);

		// 随机产生n条干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.BLACK);
		for (int i = 0; i < lineNum; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < codeNum; i++) {
			// 得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence.charAt(random.nextInt(codeSequence.length())));
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);

			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, 1 + i * xx, codeY);

			// 将产生的四个随机数组合在一起。
			randomCode.append(strRand);
		}
		g.dispose();
		// 将四位数字的验证码保存到Session中。
		httpSession.setAttribute("validateCode", randomCode.toString());

		// 禁止图像缓存。
///     httpServletResponse.setHeader("Pragma", "no-cache");
///     httpServletResponse.setHeader("Cache-Control", "no-cache");
///     httpServletResponse.setDateHeader("Expires", 0);

//      httpServletResponse.setContentType("image/jpeg");

		// 将图像输出到Servlet输出流中。
		OutputStream output = httpServletResponse.getOutputStream();
		ImageIO.write(buffImg, "jpg", output);
		output.flush();
		output.close();
///     out.close();
	}

	@RequestMapping(value = "/authority/user", method = RequestMethod.GET)
	public SysUser user(@RequestParam(value = "userId") String userId) {
		if (!StringUtils.isEmpty(userId)) {
			return authorityService.getUser(userId);
		}
		return null;
	}
}
