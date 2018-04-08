package com.hbd.retrieval.data.domain;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 获取原图信息id   file->mongo_grid_f_s_file->file->_id->$id
 * @author sunyinhui
 *
 */
public class ImageSrc {
	
	private String pathIdSrc;
	/**
	 * 把传过来的srcJsonObj解析到的数据，通过构造函数构造ImageSrc,原图的信息。
	 * @param srcJsonObj
	 * @throws JSONException
	 */
	public ImageSrc(JSONObject srcJsonObj) throws JSONException{
		this.pathIdSrc = srcJsonObj.getJSONObject("file").getJSONObject("mongo_grid_f_s_file").getJSONObject("file").getJSONObject("_id").getString("$id");
	}
	/**
	 * 获得原图的路径id
	 * @return
	 */
	public String getPathIdSrc() {
		return pathIdSrc;
	}
	/**
	 * 设置原图的路径id
	 * @param pathIdSrc
	 */
	public void setPathIdSrc(String pathIdSrc) {
		this.pathIdSrc = pathIdSrc;
	}

}
