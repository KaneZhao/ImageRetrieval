package com.hbd.retrieval.search.domain;

import com.hbd.retrieval.data.domain.ImageStrc;
/**
 * 检索结果对象
 * @author Edward
 *
 */
public class RetrievalResult {
	//检索结果图片结构
	private ImageStrc imageStrc;
	//相似度
	private int similarity;
	//对应裁剪图片的URL
	private String cropImgUrl;

	public ImageStrc getImageStrc() {
		return imageStrc;
	}

	public void setImageStrc(ImageStrc imageStrc) {
		this.imageStrc = imageStrc;
	}

	public int getSimilarity() {
		return similarity;
	}

	public void setSimilarity(int similarity) {
		this.similarity = similarity;
	}

	public String getCropImgUrl() {
		return cropImgUrl;
	}

	public void setCropImgUrl(String cropImgUrl) {
		this.cropImgUrl = cropImgUrl;
	}
}
