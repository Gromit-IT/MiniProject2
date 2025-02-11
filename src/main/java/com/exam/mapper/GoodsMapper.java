package com.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.exam.dto.GoodsDTO;

@Mapper
public interface GoodsMapper {

	public List<GoodsDTO> goodsList(String gCategory);
	public GoodsDTO goodsRetrieve(String gCode);
	public List<GoodsDTO> goodsPurchaseList(String gCategory);
	public List<GoodsDTO> goodsRegDateList(String gCategory);
}
