package com.hbd.retrieval.index.manager;

import java.util.Map;

public interface IndexManager {
	/**
	 * 在indexPath目录下建立索引
	 * @param allSegImgMap	所有图像结构id为key，对应分割图片url为value
	 * @param indexPath	建立索引的目录
	 */
	public void createIndex(Map<String, String> allSegImgMap, String indexPath);
}
