package com.exam.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.service.GoodsService;
import com.exam.service.MemberService;

@Controller
public class MainController {

	GoodsService service;
	MemberService memberService;

	public MainController(GoodsService service, MemberService memberService) {
		this.service = service;
		this.memberService = memberService;
	}

	@GetMapping("/main") // main.jsp
	@ModelAttribute("goodsList")
	public List<GoodsDTO> main(@RequestParam(required = false, defaultValue = "top") String gCategory,
			@RequestParam(required = false, defaultValue = "default") String sort, Model model) {
		// AuthProvider에서 저장시킨 Authentication 이 필요하다.(********)
		// 아래는 로그인한 사용자 정보 가져오는 코드임.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
			// auth !=null Null Pointer Exception 방지
			// auth.isAuthenticated()을 통해 로그인 했는지 확인. 익명사용자도 true 칠수 있음 조심.
			// 익명 사용자 true 방지를 위해 !(auth.getPrincipal() instanceof String)을 사용.(익명사용자는
			// String으로 저장)
			// 로그인한 사용자 정보 가져오기
			MemberDTO dto = (MemberDTO) auth.getPrincipal();
			String userid = dto.getUserid();

			// 사용자 정보 조회 및 모델에 저장
			MemberDTO mypageDTO = memberService.mypage(userid);
			model.addAttribute("login", mypageDTO);
		}

		if (gCategory == null || gCategory.trim().isEmpty()) {
			gCategory = "";
		}

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

		model.addAttribute("goodsList", list);
		model.addAttribute("selectedSort", sort);
		model.addAttribute("gCategory", gCategory);

		return list;
	}

}
