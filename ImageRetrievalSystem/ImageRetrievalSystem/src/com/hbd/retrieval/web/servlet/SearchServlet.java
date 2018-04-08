package com.hbd.retrieval.web.servlet;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hbd.retrieval.common.domain.ImgResourcePath;
import com.hbd.retrieval.common.domain.ResPathConfig;
import com.hbd.retrieval.common.servlet.BaseServlet;
import com.hbd.retrieval.common.util.CommonUtils;
import com.hbd.retrieval.data.manager.DataManager;
import com.hbd.retrieval.image.util.ImageOperate;
import com.hbd.retrieval.search.domain.ImageInfo;
import com.hbd.retrieval.search.domain.RetrievalResult;
import com.hbd.retrieval.search.manager.SearchManager;

/**
 * 用于检索的Servlet
 * @author Edward
 *
 */
public class SearchServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 用于存放用来图片的目录
	private File uploadSrcPathFile = null;
	
	// 用于存放临时文件的目录
	private File tempImgPathFile = null;
	
	//图像资源的存储路径对象
	private ImgResourcePath imgResourcePath = null;

	private ResPathConfig resPathConfig = null;
	
	//用户上传的图像的信息
	ImageInfo imageInfo = null;
	
	private ImageOperate imageOperate = null;
	
	private SearchManager searchManager = null;
	
	private DataManager dataManager = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		imgResourcePath = this.getImgResourcePath();
	
		resPathConfig = this.getResPathConfig();
		
		uploadSrcPathFile = new File(imgResourcePath.getUploadSrcPath());
		tempImgPathFile = new File(imgResourcePath.getTempImgPath());
		
		imageInfo = new ImageInfo();
		
		imageOperate = this.getImageOperate();
		searchManager = this.getSearchManager();
		dataManager = this.getDataManager();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		File file = null;
		int maxFileSize = 10000 * 1024;
		int maxMemSize = 10000 * 1024;
		// 验证上传内容类型
		boolean isMutipart = ServletFileUpload.isMultipartContent(request);
		if (isMutipart == true) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存中的存储文件的最大值
			factory.setSizeThreshold(maxMemSize);

			factory.setRepository(tempImgPathFile);

			// 创建一个新的文件上传处理程序
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 设置最大上传的文件大小
			upload.setSizeMax(maxFileSize);
			try {
				// 解析获取的文件
				List<FileItem> fileItems = upload.parseRequest(request);

				// 处理上传的文件
				Iterator<FileItem> iterator = fileItems.iterator();

				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (!item.isFormField()) {
						String fileName = item.getName();
						fileName = CommonUtils.getNewName(fileName);
						// 写入文件
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(uploadSrcPathFile,
									fileName.substring(fileName
											.lastIndexOf("\\")));
						} else {
							file = new File(uploadSrcPathFile,
									fileName.substring(fileName
											.lastIndexOf("\\") + 1));
						}
						item.write(file);
					}else{
						String name = item.getFieldName();
						String valueStr = item.getString();
						float value = Float.parseFloat(valueStr);
						if("x1".equals(name)){
							imageInfo.setLeftTopX((int)value);
						}else if("y1".equals(name)){
							imageInfo.setLeftTopY((int)value);
						}else if("x2".equals(name)){
							imageInfo.setRightBottomX((int)value);
						}else if("y2".equals(name)){
							imageInfo.setRightBottomY((int)value);
						}else if("positionFlag".equals(name)){
							imageInfo.setPositionFlag((int)value);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		//对上传文件进行重命名，并返回去路径
		String srcImgPath = CommonUtils.renameSrcFile(file, imgResourcePath.getUploadSrcPath(), imageInfo);
		String srcServerPath = resPathConfig.getImgData() + "/" + resPathConfig.getUploadSrcPath() + "/" + CommonUtils.getFileName(srcImgPath);
		
		//对原图像进行裁剪操作，并将其保存到imgResourcePath.getUploadCropPath()目录下，返回其路径
		String cropImgPath = imageOperate.getCropImgPath(srcImgPath, imageInfo, imgResourcePath.getUploadCropPath());
		String cropServerPath = resPathConfig.getImgData() + "/" + resPathConfig.getUploadCropPath() + "/" + CommonUtils.getFileName(cropImgPath);
		
		//对裁剪图像进行分割操作，并将其保存到imgResourcePath.getUploadSegPath()目录下，返回其路径
		String segImgPath = imageOperate.getSegImgPath(cropImgPath, imgResourcePath.getUploadSegPath());
		String segServerPath = resPathConfig.getImgData() + "/" + resPathConfig.getUploadSegPath() + "/" + CommonUtils.getFileName(segImgPath);
		
		//使用分割后图像进行检索
		List<RetrievalResult> resultList = searchManager.getRetrievalResults(segImgPath, imgResourcePath.getIndexPath());
		
		//简洁内容
		String[] descContent = dataManager.getDescContent(resultList);
		
		
		//将原图像、裁剪图像路径以及检索结果进行转发
		request.setAttribute("srcServerPath", srcServerPath);
		request.setAttribute("cropServerPath", cropServerPath);
		request.setAttribute("segServerPath", segServerPath);
		request.setAttribute("resultList", resultList);
		request.setAttribute("descContent", descContent);
		
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
}
