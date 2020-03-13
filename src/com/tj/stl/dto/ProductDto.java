package com.tj.stl.dto;

public class ProductDto {
	private int productCode;
	private String productName;
	private int pbrandCode;
	private String pbrandName;
	private String ptypeName;
	private int ptypeCode;
	private int productPrice;
	private int productRemain;
	private String productContent;
	private String productImg;
	public ProductDto(int productCode, String productName, String pbrandName, String ptypeName, int ptypeCode,
			int productPrice, int productRemain, String productContent, String productImg, int pbrandCode) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.pbrandCode = pbrandCode;
		this.pbrandName = pbrandName;
		this.ptypeName = ptypeName;
		this.ptypeCode = ptypeCode;
		this.productPrice = productPrice;
		this.productRemain = productRemain;
		this.productContent = productContent;
		this.productImg = productImg;
	}
	
	public int getPbrandCode() {
		return pbrandCode;
	}

	public void setPbrandCode(int pbrandCode) {
		this.pbrandCode = pbrandCode;
	}

	public int getProductCode() {
		return productCode;
	}
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPbrandName() {
		return pbrandName;
	}
	public void setPbrandName(String pbrandName) {
		this.pbrandName = pbrandName;
	}
	public String getPtypeName() {
		return ptypeName;
	}
	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}
	public int getPtypeCode() {
		return ptypeCode;
	}
	public void setPtypeCode(int ptypeCode) {
		this.ptypeCode = ptypeCode;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductRemain() {
		return productRemain;
	}
	public void setProductRemain(int productRemain) {
		this.productRemain = productRemain;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	@Override
	public String toString() {
		return "ProductDto [productCode=" + productCode + ", productName=" + productName + ", pbrandName=" + pbrandName
				+ ", ptypeName=" + ptypeName + ", ptypeCode=" + ptypeCode + ", productPrice=" + productPrice
				+ ", productRemain=" + productRemain + ", productContent=" + productContent + ", productImg="
				+ productImg + "]";
	}
		
}
