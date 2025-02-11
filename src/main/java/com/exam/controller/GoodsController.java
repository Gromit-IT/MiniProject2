package com.exam.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	MemberService memberService;
	
	public GoodsController(GoodsService goodsService, ReviewService reviewService,MemberService memberService) {
        this.goodsService = goodsService;
        this.reviewService = reviewService;
		this.memberService = memberService;
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
	        // auth !=null Null Pointer Exception 방지
			// auth.isAuthenticated()을 통해 로그인 했는지 확인. 익명사용자도 true 칠수 있음 조심.
			// 익명 사용자 true 방지를 위해 !(auth.getPrincipal() instanceof String)을 사용.(익명사용자는 String으로 저장)
			//로그인한 사용자 정보 가져오기
	        MemberDTO dto1 = (MemberDTO) auth.getPrincipal();
	        String userid = dto1.getUserid();

	        // 사용자 정보 조회 및 모델에 저장
	        MemberDTO mypageDTO = memberService.mypage(userid);
	        m.addAttribute("login", mypageDTO);
	    }
		
		return mav;
	}
	
}




