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
	public List<GoodsDTO> main(@RequestParam(required = false, defaultValue = "top") String gCategory, Model m) {

		// AuthProvider에서 저장시킨 Authentication 이 필요하다.(********)
		// 아래는 로그인한 사용자 정보 가져오는 코드임.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
	        // auth !=null Null Pointer Exception 방지
			// auth.isAuthenticated()을 통해 로그인 했는지 확인. 익명사용자도 true 칠수 있음 조심.
			// 익명 사용자 true 방지를 위해 !(auth.getPrincipal() instanceof String)을 사용.(익명사용자는 String으로 저장)
			//로그인한 사용자 정보 가져오기
	        MemberDTO dto = (MemberDTO) auth.getPrincipal();
	        String userid = dto.getUserid();

	        // 사용자 정보 조회 및 모델에 저장
	        MemberDTO mypageDTO = memberService.mypage(userid);
	        m.addAttribute("login", mypageDTO);
	    }
		
		//위의 코드는 로그인 후 정보를 가져와서 JSP에 안녕하세요${login.username}를 사용하기 위해 필요함.

		// JSP에서 보여줄 데이터고 모델에 저장해야 된다.
		List<GoodsDTO> list = service.goodsList(gCategory);

		return list;
	}

}
