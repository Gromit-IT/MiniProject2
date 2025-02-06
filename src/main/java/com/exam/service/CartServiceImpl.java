package com.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.dto.CartDTO;
import com.exam.mapper.CartMapper;


@Service
public class CartServiceImpl implements CartService {

	CartMapper mapper;
	
	public CartServiceImpl(CartMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public int cartAdd(CartDTO dto) {
		return mapper.cartAdd(dto);
	}

	@Override
	public List<CartDTO> cartList(String userid) {
		return mapper.cartList(userid);
	}

	@Override
	@Transactional
	public int cartDelete(int num) {
		return mapper.cartDelete(num);
	}

	@Override
	public int cartDeleteAll(List<String> list) {
		return mapper.cartDeleteAll(list);
	}

	
}
