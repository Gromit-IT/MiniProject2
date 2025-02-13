package com.exam.service;

import java.util.List;
import java.util.Map;

import com.exam.dto.GoodsDTO;



public interface GoodsService {

	public List<GoodsDTO> goodsList(String gCategory);
	public GoodsDTO goodsRetrieve(String gCode);
	public List<GoodsDTO> goodsPurchaseList(String gCategory);
	public List<GoodsDTO> goodsRegDateList(String gCategory);
	public void goodsAdd(GoodsDTO dto);

}
