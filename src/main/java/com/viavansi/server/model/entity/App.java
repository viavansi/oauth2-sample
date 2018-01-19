package com.viavansi.server.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app")
public class App {
	
	@Id
	@Column(name="app_id",nullable=false,updatable=false)
	private String appId;
	
	@Column(name="name")	
	private String name;
	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
