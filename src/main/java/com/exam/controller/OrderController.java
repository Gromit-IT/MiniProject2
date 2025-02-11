package com.exam.controller;

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
import com.exam.dto.MemberDTO;
import com.exam.dto.OrderDTO;
import com.exam.service.MemberService;
import com.exam.service.OrderService;

@Controller
//@SessionAttributes("login")
public class OrderController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	OrderService orderService;
	MemberService memberService;

	public OrderController(OrderService orderService, MemberService memberService) {
		this.orderService = orderService;
		this.memberService = memberService;
	}

	@GetMapping("/orderConfirm")
	public String cartAdd(@RequestParam Integer num, Model m) {

		CartDTO cartDTO = orderService.orderConfirm(num);

//		MemberDTO dto = (MemberDTO)m.getAttribute("login");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MemberDTO dto = (MemberDTO) auth.getPrincipal();
		String userid = dto.getUserid();

		MemberDTO memberDTO = orderService.orderConfirmMember(userid);

		m.addAttribute("cDTO", cartDTO);
		m.addAttribute("mDTO", memberDTO);

		return "orderConfirm";
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
