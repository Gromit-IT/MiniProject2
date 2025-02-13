package com.exam.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exam.dto.MemberDTO;
import com.exam.dto.ReviewDTO;
import com.exam.service.ReviewService;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Controller
@Validated
@RequestMapping("/reviews")  
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

   
    @PostMapping("/add")
    public String addReview(@RequestParam String gCode,
                            @Min(1) @Max(5) @RequestParam int rating,
                            @NotBlank @Size(max = 500) @RequestParam String reviewText) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDTO member = (MemberDTO) auth.getPrincipal();

        ReviewDTO review = new ReviewDTO();
        review.setGCode(gCode);
        review.setUserid(member.getUserid());
        review.setRating(rating);
        review.setReviewText(reviewText);

        reviewService.addReview(review);
        return "redirect:/goodsRetrieve?gCode=" + gCode; 
    }

    
    @GetMapping("/list")
    public String getReviews(@RequestParam("gCode") String gCode, Model model) {
        List<ReviewDTO> reviews = reviewService.getReviewsByGCode(gCode);
        System.out.println("받은 상품 코드: " + gCode);
        System.out.println("조회된 리뷰 개수: " + reviews.size());
        System.out.println("하이");
        model.addAttribute("reviews", reviews);
        return "forward:/WEB-INF/views/review/reviewList.jsp";  
    }
    
    @PostMapping("/delete")
    public String deleteReview(@RequestParam int reviewId, 
                               @RequestParam String gCode, 
                               RedirectAttributes redirectAttributes) {
        // ✅ 현재 로그인한 사용자 ID 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MemberDTO member = (MemberDTO) auth.getPrincipal();
        String userid = member.getUserid();  // 🔥 여기서 로그인한 사용자의 ID 가져옴

        // ✅ 삭제 시도
        boolean isDeleted = reviewService.deleteReview(reviewId, userid);

        if (!isDeleted) {
            redirectAttributes.addFlashAttribute("errorMessage", "권한이 없습니다.");
        }

        return "redirect:/goodsRetrieve?gCode=" + gCode;
    }
    

}
