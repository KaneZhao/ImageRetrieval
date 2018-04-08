package com.hbd.retrieval.common.domain;
/**
 * 服务器配置信息
 * @author Edward
 *
 */
public class DataServerConfig {
	//服务器地址
	private String serverUrl;
	//访问令牌
	private String authorization;
	//返回结果格式
	private String format;
	//获取服务器地址
	public String getServerUrl() {
		return serverUrl;
	}
	//设置服务器地址
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	//获取令牌
	public String getAuthorization() {
		return authorization;
	}
	//设置令牌
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	//获取返回结果格式
	public String getFormat() {
		return format;
	}
	//设置返回结果格式
	public void setFormat(String format) {
		this.format = format;
	}
}
