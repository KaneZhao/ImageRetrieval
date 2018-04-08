package com.hbd.retrieval.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbd.retrieval.common.domain.ImgResourcePath;
import com.hbd.retrieval.common.servlet.BaseServlet;
import com.hbd.retrieval.data.manager.DataManager;
import com.hbd.retrieval.index.manager.IndexManager;
/**
 * ½¨Á¢Ë÷ÒýServlet
 * @author Edward
 *
 */
public class IndexServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImgResourcePath imgResourcePath = null;
	
	private DataManager dataManager = null;
	
	private IndexManager indexManager = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		imgResourcePath = this.getImgResourcePath();
		
		dataManager = this.getDataManager();
		indexManager = this.getIndexManager();
	}

	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> allSegImgMap = dataManager.getAllSegImgMap();
		
		String indexPath = imgResourcePath.getIndexPath();
		
		indexManager.createIndex(allSegImgMap, indexPath);
		
		response.sendRedirect(request.getContextPath() + "/home");
	}
}
