package com.hbd.retrieval.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.hbd.retrieval.common.util.CommonUtils;

import junit.framework.TestCase;

public class TestCommonUtil extends TestCase {
	public void testGetNewName(){
		String a = CommonUtils.getNewName("java.txt");
		System.out.println(a);
	}
	
	public void testGetFileName(){
		String filePath = "D:\\ImageRetrievalSystem\\imgData\\uploadSegPath\\2015-06-12_15-51-29_1_seg.JPG";
		String name = CommonUtils.getFileName(filePath);
		System.out.println(name);
	}
	
	public void testCutString(){
		String str = "<p><span style=\"font-size:14px\">&nbsp;基底细胞癌(basal cell carcinoma，BCC) 是皮肤癌最常见类型之一，又称基底细胞上皮瘤(basal cell epithelioma) 、基底样细胞瘤、侵蚀性溃疡等，是源于表皮基底细胞或毛囊外根鞘的上皮性低度恶性肿瘤。本病为来自基底细胞的恶性肿瘤，它与日光照晒有密切关系，所以它好发于日光照晒的头、面、颈部或手背等处。现在普遍认为它是来自表皮多潜能细胞的肿瘤，用单克隆抗体证实基底细胞癌来自表皮细胞。</span></p>";
		str = str.replace("&nbsp;", "");
		Document doc = Jsoup.parse(str);
		String desc = doc.body().text();
		//desc = desc.replace("&nbsp;", "");
		System.out.println(desc);
	}
}
