package com.hbd.retrieval.image.util;

import com.hbd.retrieval.search.domain.ImageInfo;
/**
 * 图像操作类
 * @author Edward
 *
 */
public interface ImageOperate {
	
	/**
	 * 根据imageInfo信息进行裁剪图像，并将其保存在uploadCropPath
	 * @param srcImgPath 原图像路径，可通过opencv读入
	 * @param imageInfo		原图像的一些信息，如左上角、右下角左边、部分信息
	 * @param uploadCropPath	保存裁剪图像的目录
	 * @return	返回分割图像的路径
	 */
	public String getCropImgPath(String srcImgPath, ImageInfo imageInfo, String uploadCropPath);
	
	
	/**
	 * 对分割后图像进行分割操作，并将结果图像保存到uploadSegPath目录中
	 * @param cropImgPath	裁剪后图像的路径
	 * @param uploadSegPath		保存分割图像的路径
	 * @return	返回分割后图像的路径
	 */
	public String getSegImgPath(String cropImgPath, String uploadSegPath);
	
}
