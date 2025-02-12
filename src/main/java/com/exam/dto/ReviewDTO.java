package com.exam.dto;

import java.sql.Timestamp;

public class ReviewDTO {
    private int id;
    private String gCode;
    public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}
	private String userid;
    private int rating;
    private String reviewText;
    private Timestamp createdAt;

    // 기본 생성자
    public ReviewDTO() {}

    // 모든 필드 포함한 생성자
    public ReviewDTO(int id, String gCode, String userid, int rating, String reviewText, Timestamp createdAt) {
        this.id = id;
        this.gCode = gCode;
        this.userid = userid;
        this.rating = rating;
        this.reviewText = reviewText;
        this.createdAt = createdAt;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getGCode() { return gCode; }
    public void setGCode(String gCode) { this.gCode = gCode; }

    public String getUserid() { return userid; }
    public void setUserid(String userid) { this.userid = userid; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getReviewText() { return reviewText; }
    public void setReviewText(String reviewText) { this.reviewText = reviewText; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
