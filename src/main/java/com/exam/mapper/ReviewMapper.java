package com.exam.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.exam.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {
	
    int addReview(ReviewDTO review);  // 리뷰 추가
    
    List<ReviewDTO> getReviewsByGCode(String gCode);  // 특정 상품 리뷰 조회
    
    double getAverageRating(String gCode);  // 평균 별점 조회
    int deleteReview(int reviewId, String userid);
}
