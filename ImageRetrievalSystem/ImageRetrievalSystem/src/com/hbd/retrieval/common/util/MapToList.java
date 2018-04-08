package com.hbd.retrieval.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hbd.retrieval.data.domain.ImageStrc;
import com.hbd.retrieval.data.manager.DataManager;
import com.hbd.retrieval.data.manager.DataManagerImpl;
import com.hbd.retrieval.search.domain.RetrievalResult;

public class MapToList {
	/**
	 * 把检索得到的结果Map<相似度，图片的Id>转换成List<ImageStrc> 
	 * @param resultmap:检索到的结果Map
	 * @return retrievalResult<RetrievalResult> :RetrievalResult包含相似度、ImageStrc、CropImgUrl(前端显示加载)
	 * 
	 */
	public List<RetrievalResult> mapToList(Map<Integer,String> resultmap){
		
		Map<Integer, String> resultMap = resultmap;

		List<RetrievalResult> retrievalResult = new ArrayList<RetrievalResult>();

		DataManager dataManager = new DataManagerImpl();

		for (Map.Entry<Integer, String> entry : resultMap.entrySet()) {
			int similarity = entry.getKey();
			String imageId = entry.getValue();

			ImageStrc imageStrc = dataManager.getImageById(imageId);
			String ip = ConfigManager.getDataServerConfig().getServerUrl()
					+ ":8081/images/";
			String cropImgUrl = ip
					+ imageStrc.getPathsStrc().getImageCrop().getPathIdCrop();
			RetrievalResult retrieval = new RetrievalResult();
			retrieval.setSimilarity(similarity);
			retrieval.setImageStrc(imageStrc);
			retrieval.setCropImgUrl(cropImgUrl);
			retrievalResult.add(retrieval);

		}
		return retrievalResult;
	}

}
