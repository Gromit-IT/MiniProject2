package com.exam.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.ReviewDTO;
import com.exam.service.GoodsService;
import com.exam.service.MemberService;
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
	// 상품 등록 페이지 보여주기
    @GetMapping("/goodsAddForm")
    public String goodsAddForm() {
        return "goods/goodsAddForm"; // JSP 페이지 반환
    }

    // 상품 등록 처리
    @PostMapping("/goodsAdd")
    public String goodsAdd(
            @RequestParam("gCode") String gCode,
            @RequestParam("gName") String gName,
            @RequestParam("gCategory") String gCategory,
            @RequestParam("gPrice") int gPrice,
            @RequestParam("gContent") String gContent,
            @RequestParam("gImage") MultipartFile gImage
    ) {
        GoodsDTO dto = new GoodsDTO();
        dto.setgCode(gCode); 
        dto.setgName(gName);
        dto.setgCategory(gCategory);
        dto.setgPrice(gPrice);
        dto.setgContent(gContent);

        // 원본 파일명만 저장 (파일 저장 X)
        String fileName = gImage.getOriginalFilename();
        dto.setgImage(fileName);

        // DB에 상품 추가
        goodsService.goodsAdd(dto);

        return "redirect:/main"; // 상품 등록 후 메인 페이지로 이동
    }
	
}




