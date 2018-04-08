package com.hbd.retrieval.data.domain;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 获得裁剪图片的信息，获取id  file->mongo_grid_f_s_file->file->_id->$id
 * @author sunyinhui
 *
 */
public class ImageCrop {
	private String pathIdCrop;
	/**
	 * 把传过来的cropJsonObj解析到的数据，通过构造函数，构造cropJsonObj对象，裁剪图片的信息。
	 * @param cropJsonObj  json中image_files数组中的crop文档信息。
	 * @throws JSONException
	 */
	public ImageCrop(JSONObject cropJsonObj) throws JSONException{
		this.pathIdCrop = cropJsonObj.getJSONObject("file").getJSONObject("mongo_grid_f_s_file").getJSONObject("file").getJSONObject("_id").getString("$id");
	}
	/**
	 * 获得裁剪图片的路径id
	 * @return
	 */
	public String getPathIdCrop() {
		return pathIdCrop;
	}
	/**
	 * 设置裁剪图片的路径id
	 * @param pathIdCrop
	 */
	public void setPathIdCrop(String pathIdCrop) {
		this.pathIdCrop = pathIdCrop;
	}
}
