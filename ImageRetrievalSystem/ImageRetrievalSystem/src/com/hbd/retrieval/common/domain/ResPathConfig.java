package com.hbd.retrieval.common.domain;
/**
 * 存储resPath-config.xml中信息，存储资源文件夹名称
 * @author Edward
 *
 */
public class ResPathConfig {
	//项目名称
	private String projectName;
	//资源文件夹名
	private String imgData;
	//索引文件夹名
	private String indexPath;
	//原图像文件夹名
	private String uploadSrcPath;
	//裁剪图像文件夹名
	private String uploadCropPath;
	//分割图像文件夹名
	private String uploadSegPath;
	//临时文件夹名
	private String tempImgPath;
	//获取项目名称
	public String getProjectName() {
		return projectName;
	}
	//设置项目名称
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	//获取资源文件夹名
	public String getImgData() {
		return imgData;
	}
	//设置资源文件夹名
	public void setImgData(String imgData) {
		this.imgData = imgData;
	}
	//获取索引文件夹名
	public String getIndexPath() {
		return indexPath;
	}
	//设置索引文件夹名
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
	//获取原图像文件夹名
	public String getUploadSrcPath() {
		return uploadSrcPath;
	}
	//设置原图像文件夹名
	public void setUploadSrcPath(String uploadSrcPath) {
		this.uploadSrcPath = uploadSrcPath;
	}
	//获取裁剪图像文件夹名
	public String getUploadCropPath() {
		return uploadCropPath;
	}
	//设置裁剪图像文件夹名
	public void setUploadCropPath(String uploadCropPath) {
		this.uploadCropPath = uploadCropPath;
	}
	//获取分割图像文件夹名
	public String getUploadSegPath() {
		return uploadSegPath;
	}
	//设置分割图像文件夹名
	public void setUploadSegPath(String uploadSegPath) {
		this.uploadSegPath = uploadSegPath;
	}
	//获取临时文件夹名
	public String getTempImgPath() {
		return tempImgPath;
	}
	//设置临时文件夹名
	public void setTempImgPath(String tempImgPath) {
		this.tempImgPath = tempImgPath;
	}
}
