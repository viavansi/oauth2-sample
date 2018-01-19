package com.viavansi.server.service;

import org.springframework.stereotype.Service;

import com.viavansi.server.controller.model.SystemInfoResponse;

@Service
public class SystemInfoService {

	public SystemInfoResponse adminSystemInfo() {
		return new SystemInfoResponse();
	}

	public SystemInfoResponse publicSystemInfo() {
		return new SystemInfoResponse();
	}
}
