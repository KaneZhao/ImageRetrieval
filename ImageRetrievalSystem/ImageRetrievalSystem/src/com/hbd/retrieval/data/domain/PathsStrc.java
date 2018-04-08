package com.hbd.retrieval.data.domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * json中image_files文档中解析得到src原图、crop裁剪、seg分割图片的信息
 * @author sunyinhui
 *
 */
public class PathsStrc {
	//原图对象
	private ImageSrc imageSrc;
	//裁剪对象
	private ImageCrop imageCrop;
	//分割对象
	private ImageSeg imageSeg;

	JSONObject srcJsonObj = new JSONObject();
	JSONObject cropJsonObj = new JSONObject();
	JSONObject segJsonObj = new JSONObject();
	/**
	 * 从得到的imageJsonArray数组中解析数据，通过构造函数构造PathsStrc对象。包含原图、裁剪和分割的图片信息。
	 * @param imageJsonArray  :从json文件中的image_files文档中解析得到的JsonArray
	 * @throws JSONException
	 */
	public PathsStrc(JSONArray imageJsonArray) throws JSONException {
		for (int indexNum = 0; indexNum < imageJsonArray.length(); indexNum++) {
			JSONObject filesJsonObj = imageJsonArray.getJSONObject(indexNum);
			String type = filesJsonObj.getString("type");

			if (type.equals("src")) {
				srcJsonObj = filesJsonObj;
			}
			if (filesJsonObj.getString("type").equals("crop")) {
				cropJsonObj = filesJsonObj;
			}

			if (filesJsonObj.getString("type").equals("seg")) {
				segJsonObj = filesJsonObj;
			}
		}
		//当对应的src、crop、seg字段不存在时，toString()与"{}"相等。
		//当不等{}时，再把相应的json解析为相应的对象。(调用相应的构造函数)
		if (!srcJsonObj.toString().equals("{}")) {
			this.imageSrc = new ImageSrc(srcJsonObj);
		}
		if (!cropJsonObj.toString().equals("{}")) {
			this.imageCrop = new ImageCrop(cropJsonObj);
		}
		if (!segJsonObj.toString().equals("{}")) {
			this.imageSeg = new ImageSeg(segJsonObj);
		}
	}
	/**
	 * 获得表示原图ImageSrc对象
	 * @return
	 */
	//若为空，赋为null
	public ImageSrc getImageSrc() {
		if (!srcJsonObj.toString().equals("{}")) {
			return imageSrc;
		}
		return null;
	}
	/**
	 * 设置原图对象
	 * @param imageSrc
	 */
	public void setImageSrc(ImageSrc imageSrc) {
		this.imageSrc = imageSrc;
	}
	/**
	 * 获得表示裁剪图片ImageCrop对象
	 * @return
	 */
	public ImageCrop getImageCrop() {
		if (!cropJsonObj.toString().equals("{}")) {
			return imageCrop;
		}
		return null;
	}
	/**
	 * 设置裁剪对象
	 * @param imageCrop
	 */
	public void setImageCrop(ImageCrop imageCrop) {
		this.imageCrop = imageCrop;
	}
	/**
	 * 获得表示分割图片ImageSeg对象
	 * @return
	 */
	public ImageSeg getImageSeg() {
		if (!segJsonObj.toString().equals("{}")) {
			return imageSeg;
		}
		return null;
	}
	/**
	 *设置分割对象
	 * @param imageSeg
	 */
	public void setImageSeg(ImageSeg imageSeg) {
		this.imageSeg = imageSeg;
	}
}
