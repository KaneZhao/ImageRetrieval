package com.hbd.retrieval.search.manager;

import java.util.List;

import com.hbd.retrieval.search.domain.RetrievalResult;


public interface SearchManager {

	/**
	 * 获得检索结果
	 * @param segImgPath	分割图片的路径
	 * @param indexPath		索引文件夹
	 * @return	检索结果列表
	 */
	public List<RetrievalResult> getRetrievalResults(String segImgPath, String indexPath);
}
