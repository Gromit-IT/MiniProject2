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
	                    String gCategory,
	                    @RequestParam(required = false, defaultValue = "default") String sort) {
		
		// JSP에서 보여줄 데이터고 모델에 저장해야 된다.
		List<GoodsDTO> list;

        // 정렬 조건 추가
        if ("popular".equals(sort)) {
            list = service.goodsPurchaseList(gCategory); // 인기순 정렬
            System.out.println("인기순 정렬 실행");
        } else if ("latest".equals(sort)) {
            list = service.goodsRegDateList(gCategory); // 최신순 정렬
            System.out.println("최신순 정렬 실행");
        } else {
            list = service.goodsList(gCategory); // 기본 정렬
            System.out.println("기본 정렬 실행");
        }
		
		return list;
	}
	
}
