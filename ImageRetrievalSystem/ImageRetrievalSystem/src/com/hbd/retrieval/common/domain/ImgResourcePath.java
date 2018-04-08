package com.hbd.retrieval.common.domain;
/**
 * 存储各个资源的目录
 * @author Edward
 *
 */
public class ImgResourcePath {
	//资源总目录
	private String imgDataPath;
	//索引文件目录
	private String indexPath;
	//存储原图目录
	private String uploadSrcPath;
	//存储裁剪图片目录
	private String uploadCropPath;
	//存储分割图片目录
	private String uploadSegPath;
	//临时文件目录
	private String tempImgPath;
	//获取资源总目录
	public String getImgDataPath() {
		return imgDataPath;
	}
	//设置资源总目录
	public void setImgDataPath(String imgDataPath) {
		this.imgDataPath = imgDataPath;
	}
	//获取索引文件目录
	public String getIndexPath() {
		return indexPath;
	}
	//设置索引文件目录
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
	//获取存储原图文件目录
	public String getUploadSrcPath() {
		return uploadSrcPath;
	}
	//设置存储原图文件目录
	public void setUploadSrcPath(String uploadSrcPath) {
		this.uploadSrcPath = uploadSrcPath;
	}
	//获取存储裁剪图片文件目录
	public String getUploadCropPath() {
		return uploadCropPath;
	}
	//设置存储裁剪图片文件目录
	public void setUploadCropPath(String uploadCropPath) {
		this.uploadCropPath = uploadCropPath;
	}
	//获取存储分割图片文件目录
	public String getUploadSegPath() {
		return uploadSegPath;
	}
	//设置存储分割图片文件目录
	public void setUploadSegPath(String uploadSegPath) {
		this.uploadSegPath = uploadSegPath;
	}
	//获取临时文件目录
	public String getTempImgPath() {
		return tempImgPath;
	}
	//设置临时文件目录
	public void setTempImgPath(String tempImgPath) {
		this.tempImgPath = tempImgPath;
	}
}
