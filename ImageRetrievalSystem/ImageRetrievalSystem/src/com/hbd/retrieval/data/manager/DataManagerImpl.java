package com.hbd.retrieval.data.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;


import com.hbd.retrieval.common.util.CommonUtils;
import com.hbd.retrieval.common.util.ConfigManager;
import com.hbd.retrieval.common.util.HttpClientUtils;
import com.hbd.retrieval.data.domain.Disease;
import com.hbd.retrieval.data.domain.ImageStrc;
import com.hbd.retrieval.search.domain.RetrievalResult;
/**
 * 获得json,调用jsonParseManageImpl解析json.
 * @author sunyinhui
 *
 */
public class DataManagerImpl implements DataManager {

	private JsonParseManager jsonParseManager = null;
	
	public DataManagerImpl(){
		this.jsonParseManager = new JsonParseManagerImpl();
	}
	public List<ImageStrc> getAllImages() {
		List<ImageStrc> imageStrcList = new ArrayList<ImageStrc>();
		String jsonStr = HttpClientUtils.getJsonStr("/api/images");
		//System.out.println(jsonStr);
		imageStrcList = jsonParseManager.parseJsonToImagesList(jsonStr);
		return imageStrcList;
	}

	public Disease getDiseaseById(String diseaseId) {
		Disease disease = null;
		String jsonStr = HttpClientUtils.getJsonStr("/api/diseases/" + diseaseId);
		//System.out.println(jsonStr);
		disease = jsonParseManager.parseJsonToDisease(jsonStr);
		
		return disease;
	}
	
	public ImageStrc getImageById(String id) {
		ImageStrc imageStrc = null;
		String jsonStr = HttpClientUtils.getJsonStr("/api/images/" + id);
		imageStrc = jsonParseManager.parseJsonToImageStrc(jsonStr);
		return imageStrc;
	}

	public List<ImageStrc> getShowImagesRandomly(int num) {
		List<ImageStrc> list = new ArrayList<ImageStrc>();
		String jsonStr = HttpClientUtils.getJsonStr("/api/images");
		list = jsonParseManager.parseJsonToImageStrcRandomly(jsonStr, num);
		return list;
	}
	/**
	 * 1.key :ImageStrc.rootId
	 * 2.value :segImgPath
	 * 建立索引的时候，url获得segImagePath , 标识符设为ImageStrc.rootId
	 */
	public Map<String, String> getAllSegImgMap() {
		Map<String,String>  rootId_segPathId = new HashMap<String,String>();
		List<ImageStrc> imageStrcList = getAllImages();
		Iterator<ImageStrc> iter = imageStrcList.iterator();
		//int i = 0;
		ImageStrc imageStrc = null;
		while(iter.hasNext()){
			imageStrc = (ImageStrc)iter.next();
			String rootId = imageStrc.getId();
			//有的图片可能没有相应的裁剪和分割图片，当有相应的分割图片才加到map中，待以后建立索引。(用分割的图片建立索引)
			if(imageStrc.getPathsStrc().getImageSeg()!=null){
				String segPath = imageStrc.getPathsStrc().getImageSeg().getPathIdSeg();
				rootId_segPathId.put(rootId, segPath);
				//System.out.println(++i + "----" + "rootId:"+rootId + "----" + "segPath:" + segPath);
			}
		}
		return rootId_segPathId;
	}
	

	public List<ImageStrc> getDisesesListById(String diseaseId) {
		List<ImageStrc> sameDiseaseImageList = new ArrayList<ImageStrc>();
		String jsonStr = HttpClientUtils.getJsonStr("/api/diseases/" + diseaseId + "/images"); 
		sameDiseaseImageList = jsonParseManager.parseJsonToImageStrcByDiseaseId(jsonStr);
		return sameDiseaseImageList;
	}
	
	public String[] getSimilarImgUrls(String diseaseId,int num) {
		String[] strs = new String[num];
		List<ImageStrc>  imageListByDiseaseId = getDisesesListById(diseaseId);
		ImageStrc imageStrc =null;
		String ip  = ConfigManager.getDataServerConfig().getServerUrl() + ":8081/images/";
		for(int i = 0;i<num;i++){
			int length = imageListByDiseaseId.size();
			Random rand = new Random();
			int randNum = rand.nextInt(length-num);
			imageStrc = imageListByDiseaseId.get(randNum);
			if(imageStrc.getPathsStrc().getImageCrop() != null){
				String cropUrl = ip + imageStrc.getPathsStrc().getImageCrop().getPathIdCrop();
				strs[i]=cropUrl;
				imageListByDiseaseId.remove(randNum);
			}else{
				 while(true){
					 length = imageListByDiseaseId.size();
					 randNum = rand.nextInt(length);
					 imageStrc = imageListByDiseaseId.get(randNum);
					 if(imageStrc.getPathsStrc().getImageCrop() != null){
							String cropUrl = ip + imageStrc.getPathsStrc().getImageCrop().getPathIdCrop();
							strs[i]=cropUrl;
							imageListByDiseaseId.remove(randNum);
							break;
						}
				 }
			}
		}
		return strs;
	}
	
	
	public String[] getImagesUrlArr(List<ImageStrc> imagesList) {
		String[] imagesUrlArr = new String[imagesList.size()];
		String dataServerUrl = ConfigManager.getDataServerConfig().getServerUrl();
		int i = 0;
		for (Iterator<ImageStrc> iterator = imagesList.iterator(); iterator.hasNext();) {
			ImageStrc imageStrc = iterator.next();
			if(imageStrc.getPathsStrc().getImageCrop()!=null){
				String cropImgId = imageStrc.getPathsStrc().getImageCrop().getPathIdCrop();
				String imageUrl =  dataServerUrl + ":8081/images/" + cropImgId;
				imagesUrlArr[i++] = imageUrl;
			}
		}
		return imagesUrlArr;
	}
	
	public String[] getDescContent(List<RetrievalResult> resultList) {
		String[] descContent = new String[resultList.size()];
		RetrievalResult result = null;
		String desc = null;
		int i = 0;
		for(Iterator<RetrievalResult> iterator = resultList.iterator(); iterator.hasNext();){
			result = iterator.next();
			desc = CommonUtils.removeHtmlTag(result.getImageStrc().getDisease().getDesc());
			descContent[i++] = desc;
		}
		return descContent;
	}
	
}
