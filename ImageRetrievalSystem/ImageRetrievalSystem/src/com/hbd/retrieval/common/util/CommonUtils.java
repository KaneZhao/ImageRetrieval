package com.hbd.retrieval.common.util;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.hbd.retrieval.search.domain.ImageInfo;
/**
 * 常用工具类
 * @author Edward
 *
 */
public class CommonUtils {
	
	/**
	 * 获取List<String[]>中各个字符串数组的相同元素的数组
	 * @param resultList	字符串数组
	 * @return	去除相同元素后的字符串数组
	 */
	public static String[] getSameSearchResult(List<String[]> resultList){
		String[] sameElements = resultList.get(0);
		for(int i=1; i<resultList.size(); i++){
			sameElements = findSameElementIn2Arrays(sameElements, resultList.get(i));
		}
		return sameElements;
	}
	
	/**
	 * 找出两个相同数组的相同元素
	 * @param strArray1	字符串数组1
	 * @param strArray2	字符串数组2
	 * @return	相同元素的字符串数组
	 */
	public static String[] findSameElementIn2Arrays(String[] strArray1, String[] strArray2){
		Set<String> sameSet = new HashSet<String>();//存放数组的相同元素
		Set<String> tempSet = new HashSet<String>();//存放数组1中的元素
		
		for(int i=0; i<strArray1.length; i++){
			tempSet.add(strArray1[i]);
		}
		
		for(int i=0; i<strArray2.length; i++){
			//把数组2中的元素添加到tempSet中
		    //如果tempSet中已存在相同的元素，则tempSet.add（array2[j]）返回false
			if(!tempSet.add(strArray2[i])){
				sameSet.add(strArray2[i]);
			}
		}
		
		Object[] objects = sameSet.toArray();
		String[] sameElements = new String[objects.length];
		for(int i=0; i<objects.length; i++){
			sameElements[i] = objects[i].toString();
		}
		return sameElements;
	}
	
	/**
	 * 删除文件
	 * @param file	待删除文件
	 */
	public static void delelteFile(File file){	
		if (file != null) {
            if (file.isFile()) {
                System.out.println(file.getPath());
                file.delete();
            } else if (file.isDirectory()) {
                System.out.println(file.getPath());
                File[] subFiles = file.listFiles();
                int n = subFiles.length;
                for (int i = 0; i < n; i++) {
                	delelteFile(subFiles[i]);
                }
                file.delete();
            }
        }
	}
	
	/**
	 * 文件重命名
	 * @param file文件
	 * @param prefixStr	文件目录前缀
	 * @param imageInfo	用户上传的图片的信息
	 * @return	重命名后的文件名
	 */
	public static synchronized String renameSrcFile(File file, String prefixStr, ImageInfo imageInfo){
		String newFilePath = null;
		String filePath = file.getAbsolutePath();
		String suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
		Format format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String newName = format.format(new Date());
		File newFile = new File(prefixStr + newName +  "_" + imageInfo.getPositionFlag() + "_" + "src" + "." + suffix);
		if(file.renameTo(newFile)){
			newFilePath = newFile.getAbsolutePath();
		}
		return newFilePath;
	}
	
	/**
	 * 获取图片在服务器的相对路径
	 * @param results	字符串数组
	 * @param projectName	项目名称
	 * @return	相对路径
	 */
	public static String[] getRelativePath(String[] results, String projectName){
		String[] relativePaths = new String[results.length];
		for(int i = 0; i < results.length; i++){
			results[i] = results[i].replaceAll("\\\\", "/");
			results[i] = results[i].substring(results[i].indexOf(projectName) + projectName.length());
			relativePaths[i] = results[i];
		}
		return relativePaths;
	}
	
	/**
	 * 为上传图片命名
	 * @param fileName	上传图片的原名
	 * @return	上传图片的新名称
	 */
	public static synchronized String getNewName(String fileName){
		String newName = null;;
		if(!"".equals(fileName) && fileName != null){
			Format format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
			String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
			newName = format.format(new Date());
			newName = newName + "." + prefix;
		}
		return newName;
	}
	
	/**
	 * 根据路径名截取文件名
	 * @param filePath	文件路径名
	 * @return	文件名
	 */
	public static String getFileName(String filePath){
		if(filePath.contains("\\")){
			filePath = filePath.replaceAll("\\\\", "/");
		}
		String srcName = filePath.substring(filePath.lastIndexOf("/") + 1);
		
		return srcName;
	}
	
	/**
	 * 解析HTML字符串，提取其内容
	 * @param str	HTML字符串
	 * @return	HTML中内容
	 */
	public static String removeHtmlTag(String str){
		String desc = null;
		if(!"".equals(str) && str != null){
			if(str.contains("&nbsp;")){
				str = str.replace("&nbsp;", "");
			}
			Document doc = Jsoup.parse(str);
			desc = doc.body().text();
			if(desc.length() > 35){
				desc = desc.substring(0, 35) + "......";
			}else{
				desc = desc + "......";
			}
		}
		return desc;
	}
}
