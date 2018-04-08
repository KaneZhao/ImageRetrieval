package com.hbd.retrieval.common.domain;
/**
 * 特征配置信息
 * @author Edward
 *
 */
public class DocBuilderConfig {
	//特征名
	private String builderName;
	//特征权重
	private float weight;
	//获取特征名
	public String getBuilderName() {
		return builderName;
	}
	//设置特证名
	public void setBuilderName(String builderName) {
		this.builderName = builderName;
	}
	//获取权重
	public float getWeight() {
		return weight;
	}
	//设置权重
	public void setWeight(float weight) {
		this.weight = weight;
	}

}
