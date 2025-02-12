package com.exam.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.dto.CartDTO;
import com.exam.dto.GoodsDTO;
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;
import com.exam.service.CartService;
import com.exam.service.GoodsService;
import com.exam.service.MemberService;
import com.exam.service.OrderService;

@Controller
//@SessionAttributes("login")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	OrderService orderService;
	MemberService memberService;
	GoodsService goodsService;
	CartService cartService;

	public OrderController(OrderService orderService, MemberService memberService, GoodsService goodsService, CartService cartService) {
		this.orderService = orderService;
		this.memberService = memberService;
		this.goodsService = goodsService;
		this.cartService = cartService;
	}

	// 장바구니 => 주문
	@GetMapping("/orderConfirm")
	public String cartAdd(@RequestParam Integer num, Model m) {

		CartDTO cartDTO = orderService.orderConfirm(num);

//		MemberDTO dto = (MemberDTO)m.getAttribute("login");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MemberDTO dto = (MemberDTO) auth.getPrincipal();
		String userid = dto.getUserid();

		MemberDTO mypageDTO = memberService.mypage(userid);
		m.addAttribute("login", mypageDTO);

		MemberDTO memberDTO = orderService.orderConfirmMember(userid);
		m.addAttribute("cDTO", cartDTO);
		m.addAttribute("mDTO", memberDTO);

		return "orderConfirm";
	}
	
	// 상품상세보기 => 주문
	@GetMapping("/orderDirect")
	public String orderDirect(@RequestParam String gCode,
	                          @RequestParam String gSize,
	                          @RequestParam String gColor,
	                          @RequestParam int gAmount,
	                          Model m) {

	    // 1. 로그인 관련 정보 가져오기
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    MemberDTO dto1 = (MemberDTO) auth.getPrincipal();
	    String userid = dto1.getUserid();

	    // 회원 정보 조회
	    MemberDTO mypageDTO = memberService.mypage(userid);
	    m.addAttribute("login", mypageDTO);

	    // 2. 상품 정보를 데이터베이스에서 조회
	    GoodsDTO goodsDTO = orderService.mypageOrderConfirm(gCode);
	    if (goodsDTO == null) {
	        throw new IllegalArgumentException("유효하지 않은 상품 코드입니다: " + gCode);
	    }
	    
	    // 3. 새로운 num 값 생성 (가장 큰 num + 1)
	    int maxNum = orderService.getMaxNum(); // 현재 cart 테이블의 최대 num 값
	    int newNum = maxNum + 1;

	    // 3. CartDTO 생성 및 데이터 설정
	    CartDTO cartDTO = new CartDTO();
	    cartDTO.setNum(newNum);
	    cartDTO.setgCode(gCode);         // 상품 코드
	    cartDTO.setgSize(gSize);         // 선택한 사이즈
	    cartDTO.setgColor(gColor);       // 선택한 색상
	    cartDTO.setgAmount(gAmount);     // 주문 수량
	    cartDTO.setGoodsList(List.of(goodsDTO)); // GoodsDTO를 리스트로 추가

	    // 현재 날짜 설정
	    cartDTO.setgCartDate(LocalDate.now());

	    // 4. 주문 확인창에서 회원정보 가져오기
	    MemberDTO memberDTO = orderService.orderConfirmMember(userid);

	    // 5. 모델에 데이터 추가
	    m.addAttribute("cDTO", cartDTO); // CartDTO 전달
	    m.addAttribute("mDTO", memberDTO); // 사용자 정보 전달

	    return "orderConfirm"; // 주문 확인 페이지로 이동
	}

	
	
	@PostMapping("/orderComplete")
	public String orderComplete(@ModelAttribute CartDTO cartDTO, @ModelAttribute MemberDTO memberDTO, @RequestParam("phone") String phone, @RequestParam("num") int num) {
		OrderDTO order = new OrderDTO();

		//cartDTO와 memberDTO에서 기본 정보 불러오기
		order.setNum(cartDTO.getNum());
		order.setUserid(memberDTO.getUserid());
		order.setgCode(cartDTO.getgCode());
		order.setgSize(cartDTO.getgSize());
		order.setgColor(cartDTO.getgColor());
		order.setgAmount(cartDTO.getgAmount());
		order.setOrderName(memberDTO.getUsername());
		order.setPost(memberDTO.getPost());
		order.setAddr1(memberDTO.getAddr1());
		order.setAddr2(memberDTO.getAddr2());
		order.setPhone(phone);
		order.setOrderDay(java.time.LocalDate.now());
		
		orderService.orderComplete(order);

		int n = orderService.orderCompleteDelete(num);
		
		return "goods/orderSuccess";

	}
	
	@GetMapping("orderInfo")
	public String getOrderById(Model m){
		
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDTO memberDTO = (MemberDTO)auth.getPrincipal();
        
        
		String userid = memberDTO.getUserid();
		
        // 사용자 정보 조회 및 모델에 저장
        MemberDTO mypageDTO = memberService.mypage(userid);
        m.addAttribute("login", mypageDTO);
        
		
		// 데이터
		List<OrderDTO> getOrderById = orderService.getOrderById(userid);
		m.addAttribute("oDTO", getOrderById); // JSP로 전달

		//logger.info("LOGGER:oDTO.num:{}", getOrderById);
		return "goods/orderInfo";
	}
		
	


}
