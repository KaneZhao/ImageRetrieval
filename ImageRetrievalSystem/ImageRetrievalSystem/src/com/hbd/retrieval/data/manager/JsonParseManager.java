package com.hbd.retrieval.data.manager;

import java.util.List;

import com.hbd.retrieval.data.domain.Disease;
import com.hbd.retrieval.data.domain.ImageStrc;

public interface JsonParseManager {
	/**
	 * 将“/api/images” API获取到的json解析成对应的对象列表
	 * @param jsonStr	json字符串
	 * @return	图片结构对象的列表
	 */
	public List<ImageStrc> parseJsonToImagesList(String jsonStr);
	
	/**
	 * 将“/api/diseases/{id}” API获取的json解析成对应的disease对象
	 * @param diseaseId	疾病id
	 * @return	疾病对象
	 */
	public Disease parseJsonToDisease(String diseaseId);
	
	/**
	 * 将“/api/images/{id}” API获取的json解析成对应的imageStrc对象
	 * @param imageStrcId	图像结构id
	 * @return	图片结构对象
	 */
	public ImageStrc parseJsonToImageStrc(String imageStrcId);
	
	/**
	 * 随机获取num个图片结构
	 * @param jsonStr	访问获取图片API的返回的json字符串
	 * @param num	随机获取图片的个数
	 * @return	返回图片结构对象的列表
	 */
	public List<ImageStrc> parseJsonToImageStrcRandomly(String jsonStr, int num);
	
	/**
	 * 根据json解析所传的diseaseId 获得属于该疾病的所有图片
	 * @param jsonStr
	 * @return
	 */
	public List<ImageStrc>  parseJsonToImageStrcByDiseaseId(String jsonStr);
	
	
	
}
