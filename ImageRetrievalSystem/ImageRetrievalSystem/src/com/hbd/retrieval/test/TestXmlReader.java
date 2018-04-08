package com.hbd.retrieval.test;

import java.util.Iterator;
import java.util.List;

import com.hbd.retrieval.common.domain.DataServerConfig;
import com.hbd.retrieval.common.domain.DocBuilderConfig;
import com.hbd.retrieval.common.domain.ResPathConfig;
import com.hbd.retrieval.common.util.ConfigManager;

import junit.framework.TestCase;

public class TestXmlReader extends TestCase {
	public void testXMLReader(){
		DataServerConfig config = ConfigManager.getDataServerConfig();
		System.out.println(config.getServerUrl());
		System.out.println(config.getAuthorization());
		System.out.println(config.getFormat());
	}
	
	public void testDocXmlReader(){
		List<DocBuilderConfig> configList = ConfigManager.getConfigList();
		for(Iterator<DocBuilderConfig> iterator = configList.iterator(); iterator.hasNext();){
			DocBuilderConfig builderConfig = iterator.next();
			System.out.println(builderConfig.getBuilderName());
			System.out.println(builderConfig.getWeight());
		}
	}
	
	public void testResXmlReader(){
		ResPathConfig config = ConfigManager.getResPathConfig();
		System.out.println(config.getProjectName());
	}
}
