package com.exam.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.dto.GoodsDTO;
import com.exam.service.GoodsService;

@Controller
public class GoodsSelectListController {

	GoodsService goodsService;
	
	public GoodsSelectListController(GoodsService goodsService) {
		
		this.goodsService = goodsService;
	}
	
	@GetMapping("/shop/main")
	public String goodsSelectList(
	        @RequestParam(required = false) String gCategory,
	        @RequestParam(required = false, defaultValue = "default") String sort,
	        Model model) {

	    // gCategory가 비었거나 null이면 null로 설정하여 URL에서 제거되도록 함.
	    if (gCategory == null || gCategory.trim().isEmpty()) {
	        gCategory = null;
	    }

	    Map<String, Object> params = new HashMap<>();
	    params.put("gCategory", gCategory);
	    params.put("sort", sort);

	    List<GoodsDTO> goodsList = goodsService.goodsSelectList(params);
	    model.addAttribute("goodsList", goodsList);
	    model.addAttribute("selectedSort", sort);
	    model.addAttribute("gCategory", gCategory);

	    if (gCategory != null) {
	        return "redirect:/shop/main?gCategory=" + gCategory + "&sort=" + sort;
	    } else {
	        return "redirect:/shop/main?sort=" + sort;
	    }
	}
}
