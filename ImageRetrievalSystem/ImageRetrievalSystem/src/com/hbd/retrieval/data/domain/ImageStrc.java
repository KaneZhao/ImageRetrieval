package com.hbd.retrieval.data.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * 获取根节点的信息,对应json数组中的元素。
 * 
 * @author sunyinhui
 *
 */
public class ImageStrc {
	private String id;
	//图像数组对象
	private PathsStrc pathsStrc;
	//疾病
	private Disease disease;
	//是否裁剪
	private boolean cropped;
	//部位
	private int location;
	//创建时间
	private Date createdTime;
	//更新时间
	private Date updatedTime;
	//状态
	private int state;
	/**
	 * 从获取的rootJsonObj中解析的数据，通过构造函数构造ImageStrc对象。
	 * @param rootJsonObj   json数组中的元素(文档)
	 * @throws JSONException
	 * @throws ParseException
	 */
	public ImageStrc(JSONObject rootJsonObj) throws JSONException, ParseException{
		if(!rootJsonObj.isNull("id")){
			this.id = rootJsonObj.getString("id");
		}
		if(!rootJsonObj.isNull("disease")){
			this.disease = new Disease(rootJsonObj.getJSONObject("disease"));
		}
		if(!rootJsonObj.isNull("cropped")){
			this.cropped = rootJsonObj.getBoolean("cropped");
		}
		if(!rootJsonObj.isNull("location")){
			this.location = rootJsonObj.getInt("location");
		}
		if(!rootJsonObj.isNull("created_at")){
			this.createdTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(rootJsonObj.getString("created_at").replace("Z", "+0800"));
		}
		if(!rootJsonObj.isNull("updated_at")){
			this.updatedTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(rootJsonObj.getString("updated_at").replace("Z", "+0800"));;
		}
		if(!rootJsonObj.isNull("state")){
			this.state  = rootJsonObj.getInt("state");
		}
		if(!rootJsonObj.isNull("image_files")){
			this.pathsStrc =new PathsStrc(rootJsonObj.getJSONArray("image_files"));
		}
	}
	/**
	 * 获得id
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获得PathsStrc对象，包含ImageSrc、ImageCrop、ImageSeg对象
	 * @return
	 */
	public PathsStrc getPathsStrc() {
		return pathsStrc;
	}
	/**
	 * 设置PathStrc对象
	 * @param pathsStrc
	 */
	public void setPathsStrc(PathsStrc pathsStrc) {
		this.pathsStrc = pathsStrc;
	}
	/**
	 * 获得疾病对象
	 * @return
	 */
	public Disease getDisease() {
		return disease;
	}
	/**
	 * 设置疾病对象
	 * @param disease
	 */
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
	//判断是否裁剪过
	public boolean isCropped() {
		return cropped;
	}
	/**
	 * 设置裁剪标识
	 * @param cropped
	 */
	public void setCropped(boolean cropped) {
		this.cropped = cropped;
	}
	/**
	 * 取得位置信息
	 * @return
	 */
	public int getLocation() {
		return location;
	}
	/**
	 * 设置位置信息
	 * @param location
	 */
	public void setLocation(int location) {
		this.location = location;
	}
	/**
	 * 获得创建时间
	 * @return
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * 设置创建时间
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * 取得更新时间
	 * @return
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}
	/**
	 * 设置更新时间
	 * @param updatedTime
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	/**
	 * 获得状态
	 * @return
	 */
	public int getState() {
		return state;
	}
	/**
	 * 设置状态
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
}
