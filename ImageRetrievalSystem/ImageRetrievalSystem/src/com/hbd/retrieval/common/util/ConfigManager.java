package com.hbd.retrieval.common.util;

import java.util.List;

import com.hbd.retrieval.common.domain.DataServerConfig;
import com.hbd.retrieval.common.domain.DocBuilderConfig;
import com.hbd.retrieval.common.domain.ResPathConfig;

/**
 * 采用ThreadLocal封装配置特征配置信息、数据库服务器配置信息和资源文件夹信息
 * @author Edward
 *
 */
public class ConfigManager {
	private static ThreadLocal<List<DocBuilderConfig>> configHolder = new ThreadLocal <List<DocBuilderConfig>>();
	
	private static ThreadLocal<DataServerConfig> serverConfigHolder = new ThreadLocal<DataServerConfig>();
	
	private static ThreadLocal<ResPathConfig> resPathConfigHolder = new ThreadLocal<ResPathConfig>();
	
	/**
	 * 读取特征和权重配置信息
	 * @return	特征和权重信息配置信息列表
	 */
	public static List<DocBuilderConfig> getConfigList(){
		List<DocBuilderConfig> configList = configHolder.get();
		//如果当前县城中没有绑定相应的Config
		if(configList == null){
			configList = XmlConfigReader.getDocBuilderConfigFromXML();
			configHolder.set(configList);
		}
		return configList;
	}
	
	/**
	 * 读取图像数据库配置信息
	 * @return	图像数据库配置信息
	 */
	public static DataServerConfig getDataServerConfig(){
		DataServerConfig serverConfig = serverConfigHolder.get();
		if(serverConfig == null){
			serverConfig = XmlConfigReader.getServerConfigFromXML();
			serverConfigHolder.set(serverConfig);
		}
		return serverConfig;
	}
	
	/**
	 * 读取资源文件目录配置信息
	 * @return	资源文件目录配置信息
	 */
	public static ResPathConfig getResPathConfig(){
		ResPathConfig config = resPathConfigHolder.get();
		if(config == null){
			config = XmlConfigReader.getResPathConfigFromXML();
			resPathConfigHolder.set(config);
		}
		return config;
	}
}
