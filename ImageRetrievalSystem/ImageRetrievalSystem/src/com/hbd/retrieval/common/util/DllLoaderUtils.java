package com.hbd.retrieval.common.util;

/**
 * DLL�ļ����ع�����
 * @author Edward
 *
 */
public class DllLoaderUtils {
	/**
	 * ����DLL�ļ�
	 * @param fileNames	DLL�ļ���
	 */
	public static void loadDllFile(String... fileNames){
		String path = DllLoaderUtils.class.getResource("/").getPath();
		path = path.replaceAll("%20", " ");
		System.out.println(path);
		for(String fileName : fileNames){
			System.load(path + fileName);
			System.out.println(path+fileName);
		}
	}
}
