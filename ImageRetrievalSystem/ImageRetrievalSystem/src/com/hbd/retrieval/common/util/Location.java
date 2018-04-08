package com.hbd.retrieval.common.util;
/**
 * 疾病的所有部位，枚举类型
 * @author Edward
 *
 */
public enum Location{
	  HEAD("头部", 1), FACE("脸部", 2), NECK("颈部", 3), UPPERLIMB("上肢", 4), HAND("手部", 5), CHEST("胸部", 6),
	  BELLY("腹部", 7), WAIST("腰部", 8), HIP("臀部", 9), GENITALS("生殖器", 10), LOWERLIMB("下肢", 11), FOOT("足部",12);
	    // 成员变量  
	    private String name;  
	    private int index;  
	    // 构造方法  
	    private Location(String name, int index) {  
	        this.name = name;  
	        this.index = index;  
	    }  
	    // 普通方法  
	    public static String getName(int index) {  
	        for (Location c : Location.values()) {  
	            if (c.getIndex() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getIndex() {  
	        return index;  
	    }  
	    public void setIndex(int index) {  
	        this.index = index;  
	    } 
}
