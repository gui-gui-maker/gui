package com.gui.hik.bean;

import javax.persistence.Entity;

@Entity
public class HikCamera {
	
	private String gateway = "127.0.0.1:443"; // artemis网关服务器ip端口
	private String appKey = "29180881";  // 秘钥appkey
	private String appSecret = "XO0wCAYGi4KV70ybjznx";// 秘钥appSecret
     
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
     
     

}
