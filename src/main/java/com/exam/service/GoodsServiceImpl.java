package com.exam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.dto.GoodsDTO;
import com.exam.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService{

	GoodsMapper mapper;
	
	

	public GoodsServiceImpl(GoodsMapper mapper) {
		this.mapper = mapper;
	}


	@Override
	public List<GoodsDTO> goodsList(String gCategory) {
		return mapper.goodsList(gCategory);
	}


	@Override
	public GoodsDTO goodsRetrieve(String gCode) {
		return mapper.goodsRetrieve(gCode);
	}


	@Override
	public List<GoodsDTO> goodsPurchaseList(String gCategory) {
		return mapper.goodsPurchaseList(gCategory);
	}


	@Override
	public List<GoodsDTO> goodsRegDateList(String gCategory) {
		return mapper.goodsRegDateList(gCategory);
	}


	@Override
	public void goodsAdd(GoodsDTO dto) {
        mapper.goodsAdd(dto);
		
	}
	
	

}
