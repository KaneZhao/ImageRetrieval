package com.hbd.retrieval.test;

import com.hbd.retrieval.common.util.Location;
import junit.framework.TestCase;

public class TestLocationEnum extends TestCase {
	public void testLocaiton(){
		String color = Location.getName(10);
		String name = Location.CHEST.name();
		System.out.println(color);
		System.out.println(name);
	}
}