package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.CartDTO;
import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;

@Mapper
public interface OrderMapper {

	public CartDTO orderConfirm(int num);
	public GoodsDTO mypageOrderConfirm(String gCode);
	
	public MemberDTO orderConfirmMember(String userid);
	
	public int orderComplete(OrderDTO dto);
	public int orderCompleteDelete(int num);
	
	public List<OrderDTO> getOrderById(String userid);
	
}
