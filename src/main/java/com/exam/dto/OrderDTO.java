package com.exam.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.type.Alias;


@Alias("OrderDTO")
public class OrderDTO {
	
    int num;
    String userid;
    String gCode;
    String gSize;
    String gColor;
    int gAmount;
    String orderName;
	String post;
	String addr1;
	String addr2;
	String phone;
    LocalDate orderDay;
    
    //GoodsDTO 저장
	int gPrice;
	String gImage;

	public OrderDTO() {
	}

	public OrderDTO(int num, String userid, String gCode, String gSize, String gColor, int gAmount, String orderName,
			String post, String addr1, String addr2, String phone, LocalDate orderDay, int gPrice, String gImage) {
		this.num = num;
		this.userid = userid;
		this.gCode = gCode;
		this.gSize = gSize;
		this.gColor = gColor;
		this.gAmount = gAmount;
		this.orderName = orderName;
		this.post = post;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.phone = phone;
		this.orderDay = orderDay;
		this.gPrice = gPrice;
		this.gImage = gImage;
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

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getOrderDay() {
		return orderDay;
	}

	public void setOrderDay(LocalDate orderDay) {
		this.orderDay = orderDay;
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
		return "OrderDTO [num=" + num + ", userid=" + userid + ", gCode=" + gCode + ", gSize=" + gSize + ", gColor="
				+ gColor + ", gAmount=" + gAmount + ", orderName=" + orderName + ", post=" + post + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", phone=" + phone + ", orderDay=" + orderDay + ", gPrice=" + gPrice
				+ ", gImage=" + gImage + "]";
	}

	
   
    


}
