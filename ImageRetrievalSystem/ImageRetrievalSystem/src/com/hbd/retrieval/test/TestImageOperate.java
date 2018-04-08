package com.hbd.retrieval.test;

import com.hbd.retrieval.image.util.ImageOperate;
import com.hbd.retrieval.image.util.ImageOperateImpl;
import com.hbd.retrieval.search.domain.ImageInfo;

import junit.framework.TestCase;

public class TestImageOperate extends TestCase {
	public void testCrop(){
		ImageOperate imageOperate = new ImageOperateImpl();
		String srcPath = "D:/ImageRetrievalSystem/imgData/uploadSrcPath/2015-06-10_11-40-02_7_src.jpg";
		String uploadCropPath = "D:/ImageRetrievalSystem/imgData/uploadCropPath/";
		ImageInfo imageInfo = new ImageInfo();
		imageOperate.getCropImgPath(srcPath, imageInfo, uploadCropPath);
	}
}
