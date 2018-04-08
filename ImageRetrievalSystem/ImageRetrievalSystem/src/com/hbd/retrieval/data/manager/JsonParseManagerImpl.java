package com.hbd.retrieval.data.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hbd.retrieval.data.domain.Disease;
import com.hbd.retrieval.data.domain.ImageStrc;
/**
 * 解析json文件获得的数据，构造Disease、ImageStrc对象或获得同一疾病的list、所有ImageStrc的list
 * @author sunyinhui
 *
 */
public class JsonParseManagerImpl implements JsonParseManager {

	public Disease parseJsonToDisease(String jsonStr) {
		Disease disease = null;
		try {
			JSONObject diseaseJsonObj = new JSONObject(jsonStr);
			disease = new Disease(diseaseJsonObj);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return disease;
	}

	public ImageStrc parseJsonToImageStrc(String jsonStr) {
		ImageStrc imageStrc = null;
			try {
				JSONObject rootJsonObj = new JSONObject(jsonStr);
				imageStrc = new ImageStrc(rootJsonObj);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		return imageStrc;
	}
	
	public List<ImageStrc> parseJsonToImagesList(String jsonStr) {
		List<ImageStrc> imageStrcList = new ArrayList<ImageStrc>();
		try {
			JSONArray jsonArray = new JSONArray(jsonStr);
			for(int indexNum = 0;indexNum<jsonArray.length();indexNum++){
				JSONObject rootJsonObj = jsonArray.getJSONObject(indexNum);
				ImageStrc imageStrc = new ImageStrc(rootJsonObj);
				imageStrcList.add(imageStrc);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return imageStrcList;
	}

	public List<ImageStrc> parseJsonToImageStrcRandomly(String jsonStr, int num) {
		List<ImageStrc> randomStrList = new ArrayList<ImageStrc>();
		try {
			JSONArray jsonArray = new JSONArray(jsonStr);
			int length = jsonArray.length();
			ImageStrc imageStrc = null;
			for(int i = 0;i<num;i++){
				Random r = new Random();
				JSONObject randomObj = jsonArray.getJSONObject(r.nextInt(length));
				imageStrc = new ImageStrc(randomObj);
				randomStrList.add(imageStrc);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return randomStrList;
	}

	public List<ImageStrc> parseJsonToImageStrcByDiseaseId(String jsonStr) {
		List<ImageStrc> imageByDiseaseIdList = new ArrayList<ImageStrc>();
		try {
			JSONArray jsonArray = new JSONArray(jsonStr);
			for(int indexNum = 0;indexNum<jsonArray.length();indexNum++){
				JSONObject rootJsonObj = jsonArray.getJSONObject(indexNum);
				ImageStrc imageStrc = new ImageStrc(rootJsonObj);
				imageByDiseaseIdList.add(imageStrc);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return imageByDiseaseIdList;
	}

}
