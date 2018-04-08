package com.hbd.retrieval.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbd.retrieval.common.servlet.BaseServlet;
import com.hbd.retrieval.data.domain.ImageStrc;
import com.hbd.retrieval.data.manager.DataManager;

/**
 * 显示主页Servlet
 * @author Edward
 *
 */
public class ShowHomeServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private DataManager dataManager = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dataManager = this.getDataManager();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = 0;
		num = Integer.parseInt(this.getServletContext().getInitParameter("randomImgNum"));
		//随机获取num个图片
		List<ImageStrc> imagesList = dataManager.getShowImagesRandomly(num);
		
		String[] imagesUrlArr = dataManager.getImagesUrlArr(imagesList);
		
		request.setAttribute("imagesUrlArr", imagesUrlArr);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
	}
}
