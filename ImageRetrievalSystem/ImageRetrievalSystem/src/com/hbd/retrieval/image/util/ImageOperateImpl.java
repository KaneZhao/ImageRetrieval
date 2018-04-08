package com.hbd.retrieval.image.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import com.hbd.retrieval.common.util.DllLoaderUtils;
import com.hbd.retrieval.search.domain.ImageInfo;

public class ImageOperateImpl implements ImageOperate {
	
//	static{
//		System.load("/usr/lib/libopencv_java246.so");
//		System.load("/usr/lib/libopencv_nonfree.so.2.4");
//	}

	public String getCropImgPath(String srcImgPath, ImageInfo imageInfo,
			String uploadCropPath) {
		String srcPath = srcImgPath.replaceAll("\\\\", "/");

		String srcName = srcImgPath.substring(srcPath.lastIndexOf("/") + 1);

		DllLoaderUtils.loadDllFile("opencv_java248.dll");
//		System.load("/usr/lib/libopencv_java246.so");
		Mat image = null;
		image = Highgui.imread(srcPath);

		Point leftTop = new Point(imageInfo.getLeftTopX(), imageInfo
				.getLeftTopY());
		Point rightBottom = new Point(imageInfo.getRightBottomX(), imageInfo
				.getRightBottomY());

		Rect cropRect = new Rect(leftTop, rightBottom);
		
		Mat ROI = image.submat(cropRect);

		String cropName = srcName.replaceAll("src", "crop");

		String cropPath = uploadCropPath + cropName;

		Highgui.imwrite(cropPath, ROI);

		return cropPath;
	}
	
//	public String getCropImgPath(String srcImgPath, ImageInfo imageInfo,
//	String uploadCropPath) {
//		String srcPath = srcImgPath.replaceAll("\\\\", "/");
//		
//		String srcName = srcImgPath.substring(srcPath.lastIndexOf("/") + 1);
//		System.out.println(srcPath);
//		String cropName = srcName.replaceAll("src", "crop");
//		System.out.println(uploadCropPath);
//		String cropPath = uploadCropPath + cropName;
//		System.out.println(cropPath);
//		try {
//			cutImage(srcPath,cropPath,imageInfo.getLeftTopX(),imageInfo.getLeftTopY(),imageInfo.getRightBottomX()-imageInfo.getLeftTopX(),
//					imageInfo.getRightBottomY()-imageInfo.getLeftTopY());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return cropPath;
//}
//	 public  void cutImage(String src,String dest,int x,int y,int w,int h) throws IOException{   
//         Iterator iterator = ImageIO.getImageReadersByFormatName("JPG");   
//         ImageReader reader = (ImageReader)iterator.next();   
//         InputStream in=new FileInputStream(src);  
//         ImageInputStream iis = ImageIO.createImageInputStream(in);   
//         reader.setInput(iis, true);   
//         ImageReadParam param = reader.getDefaultReadParam();   
//         Rectangle rect = new Rectangle(x, y, w,h);    
//         param.setSourceRegion(rect);   
//         BufferedImage bi = reader.read(0,param);     
//         ImageIO.write(bi, "jpg", new File(dest));             
//
//  } 
	
	public String getSegImgPath(String cropImgPath, String uploadSegPath) {
		String cropPath = cropImgPath;
		
		DllLoaderUtils.loadDllFile("opencv_java248.dll");
//		System.load("/usr/lib/libopencv_java248.so");
		
		Mat img = null;
		img = Highgui.imread(cropPath);

		Point tl = new Point(img.width() / 6, img.height() / 6);
		Point br = new Point(5 * img.width() / 6, 5 * img.height() / 6);

		Mat result = new Mat();
		Mat bgModel = new Mat();
		Mat fgModel = new Mat();

		Mat source = new Mat(1, 1, CvType.CV_8U, new Scalar(3.0));
		Rect rect = new Rect(tl, br);

		Imgproc.grabCut(img, result, rect, bgModel, fgModel, 1, 0);
		Core.compare(result, source, result, Core.CMP_EQ);
		Mat foreground = new Mat(img.size(), CvType.CV_8UC3,
				new Scalar(0, 0, 0));

		img.copyTo(foreground, result);

		String cropName = cropPath.substring(cropPath.lastIndexOf("\\") + 1);
		String segName = cropName.replace("crop", "seg");
		String segPath = uploadSegPath + segName;

		Highgui.imwrite(segPath, foreground);

		return segPath;
	}
}
