package com.hbd.retrieval.data.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * 获得有关疾病的信息
 * @author sunyinhui
 *
 */
public class Disease {
	private String id;
	
	private Date createdTime;
	
	private Date updatedTime;
	
	//疾病名称
	private String name;
	
	//疾病简介
	private String desc;
	
	//病因
	private String etiology;
	
	//症状
	private String symptom;
	
	//预防
	private String prevent;
	
	//鉴别
	private String identify;
	
	//并发症
	private String complication;
	
	//治疗方法
	private String therapies;
	
	
	/**
	 * 从获得的diseaseJsonObj解析得到的数据，通过构造函数构造Disease对象。疾病的信息
	 * @param diseaseJsonObj  数据库json文件中的disease文档。
	 * @throws JSONException
	 * @throws ParseException
	 */
	public Disease(JSONObject diseaseJsonObj) throws JSONException, ParseException{
		if(!diseaseJsonObj.isNull("id")){
			this.id = diseaseJsonObj.getString("id");
		}
		
		
		if(!diseaseJsonObj.isNull("created_at")){
			this.createdTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(diseaseJsonObj.getString("created_at").replace("Z", "+0800"));
		}
		if(!diseaseJsonObj.isNull("updated_at")){
			this.updatedTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(diseaseJsonObj.getString("updated_at").replace("Z", "+0800"));
		}
		
		//需要判空
		if(!diseaseJsonObj.isNull("name")){
			this.name = diseaseJsonObj.getString("name");
		}
		
		if(!diseaseJsonObj.isNull("desc")){
			this.desc = diseaseJsonObj.getString("desc");
		}
		
		
		if(!diseaseJsonObj.isNull("etiology")){
			this.etiology = diseaseJsonObj.getString("etiology");
		}
		
		if(!diseaseJsonObj.isNull("symptom")){
			this.symptom = diseaseJsonObj.getString("symptom");
		}
		
		if(!diseaseJsonObj.isNull("prevent")){
			this.prevent = diseaseJsonObj.getString("prevent");
		}
		
		if(!diseaseJsonObj.isNull("identify")){
			this.identify = diseaseJsonObj.getString("identify");
		}
		
		
		if(!diseaseJsonObj.isNull("complication")){
			this.complication = diseaseJsonObj.getString("complication");
		}
		
		
		if(!diseaseJsonObj.isNull("therapies")){
			this.therapies = diseaseJsonObj.getString("therapies");
		}
	
	}

    /**
     * 得到疾病id
     * @return id 
     */
	public String getId() {
		return id;
	}

	/**
	 * 设置疾病id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

    /**
     * 获得创建的日期
     * @return  createdTime
     */
	public Date getCreatedTime() {
		return createdTime;
	}


	/**
	 * 设置创建的日期
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	/**
	 * 获得更新的时间
	 * @return updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}


	/**
	 * 设置更新的日期
	 * @param updatedTime
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	/**
	 * 获得疾病名称
	 * @return  name 疾病名称
	 */
	public String getName() {
		return name;
	}


	/**
	 * 设置疾病名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * 设置疾病简介
	 * @return desc 疾病简介
	 */
	public String getDesc() {
		return desc;
	}


	/**
	 * 设置疾病简介
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}


	/**
	 * 获得疾病病因
	 * @return  etiology
	 */
	public String getEtiology() {
		return etiology;
	}


	/**
	 * 设置疾病病因
	 * @param etiology
	 */
	public void setEtiology(String etiology) {
		this.etiology = etiology;
	}


	/**
	 * 获得疾病症状 
	 * @return  symptom  症状
	 */
	public String getSymptom() {
		return symptom;
	}


	/**
	 * 设置疾病症状
	 * @param symptom
	 */
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}


	/**
	 * 获得预防疾病建议
	 * @return
	 */
	public String getPrevent() {
		return prevent;
	}


	/**
	 * 设置预防疾病建议
	 * @param prevent
	 */
	public void setPrevent(String prevent) {
		this.prevent = prevent;
	}


	/**
	 * 获得从易混疾病中鉴别出疾病的明显特征
	 * @return
	 */
	public String getIdentify() {
		return identify;
	}


	/**
	 * 设置疾病的鉴别特征
	 * @param identify
	 */
	public void setIdentify(String identify) {
		this.identify = identify;
	}


	/**
	 * 获得疾病的并发症特征
	 * @return
	 */
	public String getComplication() {
		return complication;
	}


	/**
	 * 设置疾病的并发特征
	 * @param complication
	 */
	public void setComplication(String complication) {
		this.complication = complication;
	}


	/**
	 * 获得疾病的治疗方法
	 * @return
	 */
	public String getTherapies() {
		return therapies;
	}


	/**
	 * 设置疾病的治疗方法
	 * @param therapies
	 */
	public void setTherapies(String therapies) {
		this.therapies = therapies;
	}
	

	
}
