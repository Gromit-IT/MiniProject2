package com.exam.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.dto.MemberDTO;
import com.exam.service.MemberService;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Controller
@Validated
//@SessionAttributes("login")
public class MemberController {

	MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 회원가입 화면보기
	@GetMapping("/signup")
	public String signupForm() {
		return "memberForm";
	}

	// id 중복체크
	@GetMapping("/idCheck")
	@ResponseBody // 값 자체를 응답해줌=> 없으면 mesg.jsp가 나옴
	public String idCheck(@RequestParam String userid) {

		String mesg = "아이디 사용 가능";

		MemberDTO dto = memberService.idCheck(userid);

		if (dto != null) {
			mesg = "아이디 중복";
		}

		return mesg;
	}

	// 회원가입
	@PostMapping("/signup")
	public String signup(@RequestParam String userid, @Size(min = 2, message = "최소 2글자") @RequestParam String passwd,

			@NotBlank(message = "username 필수") @RequestParam String username,

			@RequestParam String post, @RequestParam String addr1, @RequestParam String addr2,
			@RequestParam String phone1, @RequestParam String phone2, @RequestParam String phone3,
			@RequestParam String email1, @RequestParam String email2) {

		// 실패하면 ConstraintViolationException 예외발생
		// 따라서 이 예외를 처리하는 @ControllerAdvice 빈을 만들자.

		// 비번 암호화(*********)
		String encodePW = new BCryptPasswordEncoder().encode(passwd);

		// 성공한 경우
		MemberDTO dto = new MemberDTO(userid, encodePW, username, post, addr1, addr2, phone1, phone2, phone3, email1,
				email2);

		int n = memberService.memberAdd(dto);

		return "redirect:main"; // 서블릿의 sendRedirect가 이렇게 바뀜
	}

	// mypage security 적용전
//	@GetMapping("/mypage")
//	public String mypage(Model m) {
//		
//		MemberDTO dto =
//				(MemberDTO)m.getAttribute("login");
//		String userid = dto.getUserid();
//		
//		MemberDTO mypageDTO = memberService.mypage(userid);
//		m.addAttribute("login", mypageDTO);
//		
//		return "mypage";
//	}

	// mypage security 적용후
	@GetMapping("/mypage")
	public String mypage(Model m) {

		// AuthProvider에서 저장시킨 Authentication 이 필요하다.(********)
		// 아래는 로그인한 사용자 정보 가져오는 코드임.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MemberDTO dto = (MemberDTO) auth.getPrincipal();
		String userid = dto.getUserid();
		
		
		MemberDTO mypageDTO = memberService.mypage(userid);
		m.addAttribute("login", mypageDTO);

		return "mypage";
	}
	
	//회원탈퇴
	@PostMapping("/withdrawal")
	public String withdrawal() {
	    // 로그인 정보
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    MemberDTO dto = (MemberDTO) auth.getPrincipal(); // 로그인한 사용자 정보 가져오기
	    String userid = dto.getUserid(); // 로그인한 사용자의 ID

	    // 회원 탈퇴 처리
	    int result = memberService.withdrawal(userid);

	    if (result > 0) {
	        // 탈퇴 성공 시, 로그아웃 처리
	        SecurityContextHolder.clearContext();
	        return "redirect:/logout"; // 탈퇴 후 SecurityFilterChainConfig의 logout으로 가서 쿠키 제거 후 main으로 이동.
	    } else {
	        return "mypage"; // 실패 시 마이페이지로 이동
	    }
	}

}
