package com.hbd.retrieval.common.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hbd.retrieval.common.domain.DataServerConfig;
import com.hbd.retrieval.common.domain.DocBuilderConfig;
import com.hbd.retrieval.common.domain.ResPathConfig;

/**
 * 读取XML配置文件
 * @author Edward
 *
 */
public class XmlConfigReader {
	/**
	 * 读取多特征以及权重xml配置文件
	 * @return	特征和权重配置信息列表
	 */
	public static List<DocBuilderConfig> getDocBuilderConfigFromXML(){
		List<DocBuilderConfig> configList = new ArrayList<DocBuilderConfig>();
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("docBuilder-config.xml");
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> iterator = root.elementIterator();
			while(iterator.hasNext()){
				Object node = iterator.next();
				Element el_row = (Element)node;
				
				@SuppressWarnings("unchecked")
				Iterator<Element> it_row = el_row.elementIterator();
				
				DocBuilderConfig config = new DocBuilderConfig();
				while(it_row.hasNext()){
					Element el_ename = (Element) it_row.next();
					String name = el_ename.getText();
					if(name.length() > 3){
						config.setBuilderName(name);
					}else{
						config.setWeight(Float.parseFloat(name));
					}				
				}
				configList.add(config);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return configList;
	}
	
	/**
	 * 读取数据库服务器xml配置文件
	 * @return	图像数据库服务器配置信息
	 */
	public static DataServerConfig getServerConfigFromXML(){
		DataServerConfig config = new DataServerConfig();
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("dataServer-config.xml");
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			Iterator<Element> iterator = root.elementIterator();
			while(iterator.hasNext()){
				Object node = iterator.next();
				Element el_row = (Element)node;
				
				@SuppressWarnings("unchecked")
				Iterator<Element> it_row = el_row.elementIterator();
				
				while(it_row.hasNext()){
					Element el_ename = (Element) it_row.next();
					String name = el_ename.getText();
					if(name.startsWith("http")){
						config.setServerUrl(name);
					}else if(name.contains("format")){
						config.setFormat(name);
					}else{
						config.setAuthorization(name);
					}				
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return config;
	}
	
	/**
	 * 读取图片资源存放路径信息xml配置文件
	 * @return	资源文件目录配置信息
	 */
	public static ResPathConfig getResPathConfigFromXML(){
		ResPathConfig config = new ResPathConfig();
		SAXReader reader = new SAXReader();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("resPath-config.xml");
		try {
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			Element config_info = root.element("config-info");
			config.setProjectName(config_info.element("projectName").getText());
			config.setImgData(config_info.element("imgData").getText());
			config.setIndexPath(config_info.element("indexPath").getText());
			config.setUploadSrcPath(config_info.element("uploadSrcPath").getText());
			config.setUploadCropPath(config_info.element("uploadCropPath").getText());
			config.setUploadSegPath(config_info.element("uploadSegPath").getText());
			config.setTempImgPath(config_info.element("tempImgPath").getText());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return config;		
	}
}
