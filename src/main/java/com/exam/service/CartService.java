package com.exam.service;

import java.util.List;

import com.exam.dto.CartDTO;



public interface CartService {

	public int cartAdd(CartDTO dto);
	public int getMaxNumCart();
	public List<CartDTO> cartList(String userid);
	public int cartDelete(int num);
	public int cartDeleteAll(List<String> list);
	
}
