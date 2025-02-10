package com.exam.service;

import java.util.List;

import com.exam.dto.CartDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;



public interface OrderService {

	public CartDTO orderConfirm(int num);
	
	public MemberDTO orderConfirmMember(String userid);
	
	public int orderComplete(OrderDTO dto);
	public int orderCompleteDelete(int num);
	public OrderDTO getOrderById(int num);
}
