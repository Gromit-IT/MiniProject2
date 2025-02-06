package com.exam.dto;

import org.apache.ibatis.type.Alias;

/*
 
   create table goods
   ( gCode varchar(20) PRIMARY KEY, -- 상품코드
     gCategory varchar(20) not null,
     gName varchar(50) not null,
     gContent varchar(4000) not null,
     gPrice INT not null,
     gImage varchar(20) not null );
 
 */

@Alias("GoodsDTO")
public class GoodsDTO {
	
	String gCode;
	String gCategory;
	String gName;
	String gContent;
	int gPrice;
	String gImage;
	
	public GoodsDTO() {}

	public GoodsDTO(String gCode, String gCategory, String gName, String gContent, int gPrice, String gImage) {
		super();
		this.gCode = gCode;
		this.gCategory = gCategory;
		this.gName = gName;
		this.gContent = gContent;
		this.gPrice = gPrice;
		this.gImage = gImage;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getgCategory() {
		return gCategory;
	}

	public void setgCategory(String gCategory) {
		this.gCategory = gCategory;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgContent() {
		return gContent;
	}

	public void setgContent(String gContent) {
		this.gContent = gContent;
	}

	public int getgPrice() {
		return gPrice;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public String getgImage() {
		return gImage;
	}

	public void setgImage(String gImage) {
		this.gImage = gImage;
	}

	@Override
	public String toString() {
		return "GoodsDTO [gCode=" + gCode + ", gCategory=" + gCategory + ", gName=" + gName + ", gContent=" + gContent
				+ ", gPrice=" + gPrice + ", gImage=" + gImage + "]";
	}

}
