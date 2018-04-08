package com.hbd.retrieval.common.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.hbd.retrieval.common.domain.ImgResourcePath;
import com.hbd.retrieval.common.domain.ResPathConfig;
import com.hbd.retrieval.data.manager.DataManager;
import com.hbd.retrieval.data.manager.DataManagerImpl;
import com.hbd.retrieval.image.util.ImageOperate;
import com.hbd.retrieval.image.util.ImageOperateImpl;
import com.hbd.retrieval.index.manager.IndexManager;
import com.hbd.retrieval.index.manager.IndexManagerImpl;
import com.hbd.retrieval.search.manager.SearchManager;
import com.hbd.retrieval.search.manager.SearchManagerImpl;
/**
 * 项目中所有Servlet的父类
 * @author Edward
 *
 */
public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//各种资源路径
	private ImgResourcePath imgResourcePath = null;
	//资源路径配置
	private ResPathConfig resPathConfig = null;
	//图片操作工具对象
	private ImageOperate imageOperate = null;
	//检索功能对象
	private SearchManager searchManager = null;
	//数据管理对象
	private DataManager dataManager = null;
	//建立索引功能对象
	private IndexManager indexManager = null;
	@Override
	public void init() throws ServletException {
		super.init();
		imgResourcePath = (ImgResourcePath) this.getServletContext().getAttribute("imgResourcePath");
		
		resPathConfig = (ResPathConfig) this.getServletContext().getAttribute("resPathConfig");
		
		imageOperate = new ImageOperateImpl();
		
		searchManager = new SearchManagerImpl();
		
		dataManager = new DataManagerImpl();
		
		indexManager = new IndexManagerImpl();
	}
	//获取图片资源路径信息
	public ImgResourcePath getImgResourcePath() {
		return imgResourcePath;
	}
	//获取图片操作对象
	public ImageOperate getImageOperate() {
		return imageOperate;
	}
	
	//获取图片资源路径配置信息
	public ResPathConfig getResPathConfig() {
		return resPathConfig;
	}
	//获取检索功能对象
	public SearchManager getSearchManager() {
		return searchManager;
	}
	//获取操作数据对象
	public DataManager getDataManager() {
		return dataManager;
	}
	//获取建立索引功能对象
	public IndexManager getIndexManager() {
		return indexManager;
	}
	
}
