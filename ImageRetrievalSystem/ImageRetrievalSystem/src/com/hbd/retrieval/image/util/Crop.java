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

public class Crop {
	
	 public static void cutImage(String src,String dest,int x,int y,int w,int h) throws IOException{   
         Iterator iterator = ImageIO.getImageReadersByFormatName("jpg");   
         ImageReader reader = (ImageReader)iterator.next();   
         InputStream in=new FileInputStream(src);  
         ImageInputStream iis = ImageIO.createImageInputStream(in);   
         reader.setInput(iis, true);   
         ImageReadParam param = reader.getDefaultReadParam();   
         Rectangle rect = new Rectangle(x, y, w,h);    
         param.setSourceRegion(rect);   
         BufferedImage bi = reader.read(0,param);     
         ImageIO.write(bi, "jpg", new File(dest));             

  }  
	 
	 public static void main(String[] args) {
		String src = ".//Image001.jpg";
		String dest = ".//image.jpg";
		
		try {
			cutImage(src,dest,0,0,100,100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 
		 
		 
	}
	 
}
