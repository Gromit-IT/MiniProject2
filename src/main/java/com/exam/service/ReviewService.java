package com.exam.service;

import java.util.List;
import com.exam.dto.ReviewDTO;

public interface ReviewService {

    int addReview(ReviewDTO review);  // 리뷰 추가
    List<ReviewDTO> getReviewsByGCode(String gCode);  // 특정 상품의 리뷰 조회
    double getAverageRating(String gCode);  // 평균 별점 조회
    boolean deleteReview(int reviewId, String userid);
}
