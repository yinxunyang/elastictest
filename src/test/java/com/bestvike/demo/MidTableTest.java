package com.bestvike.demo;

import com.bestvike.portal.dao.SysUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yinxunyang
 * @Description: 中间表单元测试类
 * @Date: 2019/9/9 17:06
 * @param:
 * @return:
 */
@Slf4j
public class MidTableTest extends BaseTest {

	@Autowired
	private SysUserDao sysUserDao;


	@Test
	public void test10() {
		int ss = sysUserDao.selectId();
		log.info("ss:" + ss);
	}

}
