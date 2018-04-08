package com.hbd.retrieval.data.domain;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * 获取分割图片的id  file->mongo_grid_f_s_file->file->_id->$id
 * @author sunyinhui
 *
 */
public class ImageSeg {
	private String pathIdSeg;
	/**
	 * 把传过来的segJsonObj解析到的数据，通过构造函数，构造ImageSeg对象，分割图像的信息。
	 * @param segJsonObj  
	 * @throws JSONException
	 */
	public ImageSeg(JSONObject segJsonObj) throws JSONException{
		this.pathIdSeg = segJsonObj.getJSONObject("file").getJSONObject("mongo_grid_f_s_file").getJSONObject("file").getJSONObject("_id").getString("$id");
	}
	
	/**
	 * 获得分割图片的路径id
	 * @return
	 */
	public String getPathIdSeg() {
		return pathIdSeg;
	}
	/**
	 * 设置分割图片的路径id
	 * @param pathIdSeg
	 */
	public void setPathIdSeg(String pathIdSeg) {
		this.pathIdSeg = pathIdSeg;
	}
}
