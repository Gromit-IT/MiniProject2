package com.exam.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.exam.dto.GoodsDTO;
import com.exam.dto.ReviewDTO;
import com.exam.service.GoodsService;
import com.exam.service.ReviewService;


@Controller
@SessionAttributes("gCode")
public class GoodsController {
	
	ReviewService reviewService;
	GoodsService goodsService;
	
	public GoodsController(GoodsService goodsService, ReviewService reviewService) {
        this.goodsService = goodsService;
        this.reviewService = reviewService;
    }


	@GetMapping("/goodsRetrieve")
	public ModelAndView goodsRetrieve(@RequestParam(required = false) 
	                                 String gCode,
	                                 Model m) {
		//GlobalExceptionHandler 에서 요청하는 경우
		if(gCode == null) {
			gCode = (String)m.getAttribute("gCode");
		}
		
		// 상품목록에서 자세히보기로 요청한 경우에 gCode를 세션에 저장.
		// 이유는 GlobalExceptionHandler 요청할 때 gCode 얻기 위함.
		m.addAttribute("gCode", gCode);
		
		
		
		GoodsDTO dto = goodsService.goodsRetrieve(gCode);
		List<ReviewDTO> reviews = reviewService.getReviewsByGCode(gCode);

		ModelAndView mav = new ModelAndView();
		//모델 저장
		mav.addObject("goodsRetrieve", dto);
		mav.addObject("reviews", reviews);
		
		//뷰 저정
		mav.setViewName("goodsRetrieve");
		
		return mav;
	}
	
}




