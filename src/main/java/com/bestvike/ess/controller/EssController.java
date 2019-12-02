package com.bestvike.ess.controller;

import com.bestvike.commons.mybatis.QueryParam;
import com.bestvike.commons.support.ServiceException;
import com.bestvike.ess.service.EssService;
import com.bestvike.portal.controller.BaseController;
import com.bestvike.portal.data.DemoTable;
import com.bestvike.portal.data.EssAllot;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.service.DemoService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/ess")
public class EssController extends BaseController {

	@Autowired
	private EssService essService;

	public EssController() {
		super("01");
	}

	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public void upload(@RequestParam(name = "deviceId") String deviceId, HttpServletRequest httpServletRequest) {
		try {
			essService.importEssSeedFile(deviceId, httpServletRequest.getInputStream(), super.getUserIdAndName());
		} catch (Exception e) {
			throw new ServiceException("99", e.getMessage());
		}
	}

	/**
	 * 派发
	 * @param deviceSn
	 * @param userId
	 * @param devicePwd
	 */
	@RequestMapping(value = "/allot", method = RequestMethod.POST)
	public void allot(@RequestParam(name = "deviceSn") String deviceSn, @RequestParam(name = "userId") String userId, @RequestParam(name = "devicePwd") String devicePwd) {
	}
}
