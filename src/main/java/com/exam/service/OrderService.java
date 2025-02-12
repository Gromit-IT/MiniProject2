package com.exam.service;

import java.util.List;

import com.exam.dto.CartDTO;
import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;



public interface OrderService {

	public CartDTO orderConfirm(int num);
	public GoodsDTO mypageOrderConfirm(String gCode);
	public int getMaxNum();

	
	public MemberDTO orderConfirmMember(String userid);
	
	public int orderComplete(OrderDTO dto);
	public int orderCompleteDelete(int num);
	public List<OrderDTO> getOrderById(String userid);
}
