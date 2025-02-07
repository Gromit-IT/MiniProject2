package com.exam.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.exam.dto.GoodsDTO;
import com.exam.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService{

	GoodsMapper mapper;
	
	@Override
	public List<GoodsDTO> goodsSelectList(Map<String, Object> params) {
		return mapper.goodsSelectList(params);
	}


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

}
