package com.hbd.retrieval.test;

import com.hbd.retrieval.common.util.HttpClientUtils;

import junit.framework.TestCase;

public class TestHttpClient extends TestCase {
	public void testDoGet(){
		String jsonStr = HttpClientUtils.getJsonStr("/api/images");
		System.out.println(jsonStr);
		
		String jsonDiseasesStr = HttpClientUtils.getJsonStr("/api/diseases/" + "556e5812744f9587758b4567");
		System.out.println(jsonDiseasesStr);
	}
}
