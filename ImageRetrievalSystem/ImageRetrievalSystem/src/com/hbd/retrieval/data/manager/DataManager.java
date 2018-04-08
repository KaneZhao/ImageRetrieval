package com.hbd.retrieval.data.manager;

import java.util.List;
import java.util.Map;

import com.hbd.retrieval.data.domain.Disease;
import com.hbd.retrieval.data.domain.ImageStrc;
import com.hbd.retrieval.search.domain.RetrievalResult;

public interface DataManager {
	/**
	 * 获取所有图片
	 * @return	所有图片对象列表
	 */
	public List<ImageStrc> getAllImages();
	
	/**
	 * 根据id查找某张图片的所有相关信息
	 * @param id
	 * @return	图片结构对象
	 */
	public ImageStrc getImageById(String id);
	
	/**
	 * 随机获取num个图片结构
	 * @return num个图片结构
	 */
	public List<ImageStrc> getShowImagesRandomly(int num);
	
	/**
	 * 根据图片id获取所属病种
	 * @param id
	 * @return 疾病对象
	 */
	public Disease getDiseaseById(String id);
	
	/**
	 * 返回所有分割图片的map,其中key:ImageStrc.id; value:segImgUrl
	 * @return 返回map对象
	 */
	public Map<String, String> getAllSegImgMap();
	
	/**
	 * 查询与图像相似的num个图像
	 * @param imageStrcId	图片结构的id
	 * @param diseaseId		疾病的id
	 * @param num	相似图像的个数
	 * @return	相似图像URL数组
	 */
	
	/**
	 * 根据疾病Id返回属于该疾病下的所有的ImageStrc
	 * @param diseaseId 疾病Id
	 */
	public List<ImageStrc> getDisesesListById(String diseaseId);
	
	
	/**
	 * 根据疾病Id, 返回num个属于同一种疾病的裁剪后的图片url
	 * @param diseaseId
	 * @param num
	 * @return
	 */
	public String[] getSimilarImgUrls(String diseaseId, int num);
	
	/**
	 * 根据imageStrc列表获取相应裁剪图片的URL
	 * @param imagesList
	 * @return
	 */
	public String[] getImagesUrlArr(List<ImageStrc> imagesList);
	
	
	/**
	 * 获取所有检索图片结果对应疾病的简介内容
	 * @param resultList	检索结构列表
	 * @return	简介内容数组
	 */
	public String[] getDescContent(List<RetrievalResult> resultList);
}
