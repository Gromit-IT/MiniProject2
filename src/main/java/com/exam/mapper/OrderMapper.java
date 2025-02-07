package com.exam.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.CartDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;

@Mapper
public interface OrderMapper {

	public CartDTO orderConfirm(int num);
	
	public MemberDTO orderConfirmMember(String userid);
	
	public int orderComplete(OrderDTO dto);
	
}
