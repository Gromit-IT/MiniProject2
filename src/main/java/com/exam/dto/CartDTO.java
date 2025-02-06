package com.exam.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.type.Alias;

/*
 
  create table cart
  (  num INT PRIMARY KEY AUTO_INCREMENT,
     userid VARCHAR(20),
     gCode varchar(20) not null,
     gSize CHAR(1) not null,
     gColor VARCHAR(10) not null,
     gAmount INT not null,
     gCartDate DATE DEFAULT (current_date)
  );   
 
 */
@Alias("CartDTO")
public class CartDTO {
	
    int num;
    String userid;
    String gCode;
    String gSize;
    String gColor;
    int gAmount;
    LocalDate gCartDate;
    
    //GoodsDTO 저장
    List<GoodsDTO> goodsList;
    
	public CartDTO() {}

	public CartDTO(int num, String userid, String gCode, String gSize, String gColor, int gAmount, LocalDate gCartDate,
			List<GoodsDTO> goodsList) {
		this.num = num;
		this.userid = userid;
		this.gCode = gCode;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.gCartDate = gCartDate;
		this.goodsList = goodsList;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getgSize() {
		return gSize;
	}

	public void setgSize(String gSize) {
		this.gSize = gSize;
	}

	public String getgColor() {
		return gColor;
	}

	public void setgColor(String gColor) {
		this.gColor = gColor;
	}

	public int getgAmount() {
		return gAmount;
	}

	public void setgAmount(int gAmount) {
		this.gAmount = gAmount;
	}

	public LocalDate getgCartDate() {
		return gCartDate;
	}

	public void setgCartDate(LocalDate gCartDate) {
		this.gCartDate = gCartDate;
	}

	public List<GoodsDTO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsDTO> goodsList) {
		this.goodsList = goodsList;
	}

	@Override
	public String toString() {
		return "CartDTO [num=" + num + ", userid=" + userid + ", gCode=" + gCode + ", gSize=" + gSize + ", gColor="
				+ gColor + ", gAmount=" + gAmount + ", gCartDate=" + gCartDate + ", goodsList=" + goodsList + "]";
	}
   
}
