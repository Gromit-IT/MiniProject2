package com.exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.exam.dto.GoodsDTO;
import com.exam.service.GoodsService;

@Controller
public class MainController {

	GoodsService service;
	
	public MainController(GoodsService service) {
		this.service = service;
	}


	@GetMapping("/main") //main.jsp
	@ModelAttribute("goodsList")
	public List<GoodsDTO> main(@RequestParam(required = false, defaultValue = "top")
	                    String gCategory) {
		
		// JSP에서 보여줄 데이터고 모델에 저장해야 된다.
		List<GoodsDTO> list = service.goodsList(gCategory);
		
		return list;
	}
	
}
