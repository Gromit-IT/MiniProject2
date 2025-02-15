package com.exam.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.dto.CartDTO;
import com.exam.dto.MemberDTO;
import com.exam.service.CartService;
import com.exam.service.MemberService;
import com.exam.service.OrderService;

import jakarta.validation.constraints.Size;

@Controller
@Validated
//@SessionAttributes("login")
public class CartController {

	
	CartService cartService;
	MemberService memberService;
	OrderService orderService;
	
	public CartController(CartService cartService, MemberService memberService,	OrderService orderService) {
		this.cartService = cartService;
		this.memberService = memberService;
		this.orderService = orderService;
	}



	@GetMapping("/cartAdd")
	public String cartAdd( @RequestParam String gCode,
				           @RequestParam String gSize,
			               @RequestParam  String gColor,
			               
			               @Size(min = 1, max = 2)
					      // @Size 적용가능한 타입: 문자열, 컬렉션, 배열
			               @RequestParam  String gAmount,
			               
			               Model m
			               ) {
		
		//실패
		// @Validated 로 설정한 유효성체크는 에러발생시 ConstraintViolationException 예외가 발생되고
		// @ControllerAdvice 지정한 GlobalExceptionHandler 생성.
		
		//성공
//		MemberDTO memberDTO = (MemberDTO)m.getAttribute("login");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDTO memberDTO = (MemberDTO)auth.getPrincipal();
		
		String userid = memberDTO.getUserid();
		
	    // 3. 새로운 num 값 생성 (가장 큰 num + 1)
	    int maxNum = orderService.getMaxNum();
	    int newNum = maxNum + 1;

	    System.out.println(newNum);
		
		CartDTO cartDTO = new CartDTO();
	    cartDTO.setNum(newNum);
	    System.out.println(newNum);
		cartDTO.setUserid(userid);
		cartDTO.setgCode(gCode);
		cartDTO.setgSize(gSize);
		cartDTO.setgColor(gColor);
		cartDTO.setgAmount( Integer.parseInt(gAmount));
		
		int n = cartService.cartAdd(cartDTO);
		
		return "goods/cartAddSuccess";
	}

	
	@GetMapping("/cartList")  // cartList.jsp
	@ModelAttribute("cartList")
	public List<CartDTO> cartList(Model m) {
		
//		MemberDTO memberDTO = (MemberDTO)m.getAttribute("login");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDTO memberDTO = (MemberDTO)auth.getPrincipal();
		String userid = memberDTO.getUserid();
		
        // 사용자 정보 조회 및 모델에 저장
        MemberDTO mypageDTO = memberService.mypage(userid);
        m.addAttribute("login", mypageDTO);
		
		// 데이터
		List<CartDTO> cartList = cartService.cartList(userid);
		return cartList;
	}
	
	@GetMapping("/cartDelete") 
	public String cartDelete(@RequestParam Integer num) {
		
		int n = cartService.cartDelete(num);
		
		return "redirect:cartList";
	}
	
	@GetMapping("/cartDeleteAll")
	public String cartDeleteAll(@RequestParam(name = "check", required = false)  List<String> check) {
		
		System.out.println("check: " + check);
		if(check != null) {
		  int n = cartService.cartDeleteAll(check);
		}
		return "redirect:cartList";
	}
	
}




