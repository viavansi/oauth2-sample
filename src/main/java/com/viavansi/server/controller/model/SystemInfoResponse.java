package com.viavansi.server.controller.model;


public class SystemInfoResponse {
	private SystemInfoStatusCode status=SystemInfoStatusCode.OK;	
	public SystemInfoStatusCode getStatus() {
		return status;
	}

	public void setStatus(SystemInfoStatusCode status) {
		this.status = status;
	}
	
}
