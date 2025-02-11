package com.exam.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exam.dto.ReviewDTO;
import com.exam.mapper.ReviewMapper;

@Service

public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    
    public int addReview(ReviewDTO review) {
        return reviewMapper.addReview(review);
    }

    @Override
    public List<ReviewDTO> getReviewsByGCode(String gCode) {
        return reviewMapper.getReviewsByGCode(gCode);
    }

    @Override
    public double getAverageRating(String gCode) {
        return reviewMapper.getAverageRating(gCode);
    }
}
