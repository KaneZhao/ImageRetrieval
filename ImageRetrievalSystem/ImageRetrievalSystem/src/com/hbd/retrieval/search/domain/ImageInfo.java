package com.hbd.retrieval.search.domain;
/**
 * 上传图片的信息
 * @author Edward
 *
 */
public class ImageInfo {
	//疾病部位
	private int positionFlag;
	//病变区域左上角坐标X
	private int leftTopX;
	//病变区域左上角坐标Y
	private int leftTopY;
	//病变区域右下边坐标X
	private int rightBottomX;
	//病变区域右下角坐标Y
	private int rightBottomY;
	
	public int getPositionFlag() {
		return positionFlag;
	}
	public void setPositionFlag(int positionFlag) {
		this.positionFlag = positionFlag;
	}
	public int getLeftTopX() {
		return leftTopX;
	}
	public void setLeftTopX(int leftTopX) {
		this.leftTopX = leftTopX;
	}
	public int getLeftTopY() {
		return leftTopY;
	}
	public void setLeftTopY(int leftTopY) {
		this.leftTopY = leftTopY;
	}
	public int getRightBottomX() {
		return rightBottomX;
	}
	public void setRightBottomX(int rightBottomX) {
		this.rightBottomX = rightBottomX;
	}
	public int getRightBottomY() {
		return rightBottomY;
	}
	public void setRightBottomY(int rightBottomY) {
		this.rightBottomY = rightBottomY;
	}
	
}
