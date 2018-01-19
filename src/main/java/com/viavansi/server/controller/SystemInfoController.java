package com.viavansi.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.viavansi.server.controller.model.SystemInfoResponse;
import com.viavansi.server.service.SystemInfoService;

@RestController
public class SystemInfoController {
	@Autowired
	SystemInfoService systemService;
	
	@PreAuthorize("#oauth2.hasScope('admin')")
	@RequestMapping(value="/rest/system/admin/info",method=RequestMethod.GET)
	@ResponseBody
	public SystemInfoResponse healthcheck() {
		return systemService.adminSystemInfo();
		
	}
	
	
	@RequestMapping(value="/rest/system/public/info",method=RequestMethod.GET)
	@ResponseBody
	public SystemInfoResponse publicHealthcheck() {
		return systemService.publicSystemInfo();
		
	}
}
