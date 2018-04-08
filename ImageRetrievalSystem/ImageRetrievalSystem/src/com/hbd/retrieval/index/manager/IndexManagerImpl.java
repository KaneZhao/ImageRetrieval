package com.hbd.retrieval.index.manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.semanticmetadata.lire.imageanalysis.LireFeature;
import net.semanticmetadata.lire.impl.ChainedDocumentBuilder;
import net.semanticmetadata.lire.impl.GenericDocumentBuilder;
import net.semanticmetadata.lire.utils.LuceneUtils;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import com.hbd.retrieval.common.domain.DocBuilderConfig;
import com.hbd.retrieval.common.util.CommonUtils;
import com.hbd.retrieval.common.util.ConfigManager;
/**
 * 建立索引，以分割的图片建立索引，检索返回的是对应的裁剪图片，所以以json数组中的元素rootId,建立索引。
 * 检索时，再根据rootId获得对应的裁剪图片的url，在前端加载显示。
 * 
 * @author sunyinhui
 *
 */
public class IndexManagerImpl implements IndexManager {
	
	@SuppressWarnings("unchecked")
	public void createIndex(Map<String, String> allSegImgMap, String indexPath) {
			
		// Use multiple DocumentBuilder instances:
        ChainedDocumentBuilder builder = new ChainedDocumentBuilder();
		List<DocBuilderConfig> configList = ConfigManager.getConfigList();
		for(Iterator<DocBuilderConfig> iterator = configList.iterator(); iterator.hasNext();){
			//通过反射机制
			try {
				DocBuilderConfig builderConfig = iterator.next();
				String builderName = builderConfig.getBuilderName();
				Object feature = Class.forName(builderName).newInstance();
				builder.addBuilder(new GenericDocumentBuilder((Class<? extends LireFeature>) feature.getClass()));
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
        // 创建IndexWriter
        @SuppressWarnings("deprecation")
		IndexWriterConfig conf = new IndexWriterConfig(LuceneUtils.LUCENE_VERSION,
                new WhitespaceAnalyzer(LuceneUtils.LUCENE_VERSION));
        IndexWriter iw = null;
        
        File file = new File(indexPath);
      //清空原有存在的索引文件
		CommonUtils.delelteFile(file);
		
		if(!file.exists()&&!file.isDirectory()){
			file.mkdirs();
		}
		
		try {
			iw = new IndexWriter(FSDirectory.open(new File(indexPath)),conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Iterating through images building the low level features
		//遍历rootId_segPath 
		int i = 0;
		for(Map.Entry<String, String> entry: allSegImgMap.entrySet()){
			String rootId = entry.getKey();
			String segPathId = entry.getValue();
			String ip  = ConfigManager.getDataServerConfig().getServerUrl()+":8081/images/";
			String ip_segPathId = ip+segPathId;
			try {
				URL url  = new URL(ip_segPathId);
				//System.out.println(url);
				BufferedImage img = ImageIO.read(url);
				//json数组中的元素id建立索引
				Document doc = builder.createDocument(img, rootId);
				iw.addDocument(doc);
				//System.out.println(doc);
				System.out.println(++i + "-----" + "index");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			iw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Index successed!");
	}
}
