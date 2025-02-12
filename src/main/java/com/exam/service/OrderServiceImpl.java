package com.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.dto.CartDTO;
import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;
import com.exam.mapper.OrderMapper;


@Service
public class OrderServiceImpl implements OrderService {

	OrderMapper mapper;
	
	public OrderServiceImpl(OrderMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public CartDTO orderConfirm(int num) {
		return mapper.orderConfirm(num);
	}

	@Override
	public MemberDTO orderConfirmMember(String userid) {
		return mapper.orderConfirmMember(userid);
	}

	@Override
	@Transactional
	public int orderComplete(OrderDTO dto) {
		return mapper.orderComplete(dto);
	}

	@Override
	public int orderCompleteDelete(int num) {
		return mapper.orderCompleteDelete(num);
	}

	@Override
	public List<OrderDTO> getOrderById(String userid) {
		return mapper.getOrderById(userid);
	}

	@Override
	public GoodsDTO mypageOrderConfirm(String gCode) {
		return mapper.mypageOrderConfirm(gCode);
	}

	@Override
	public int getMaxNum() {
	    return mapper.getMaxNum();
	}


	



	
}
