<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${empty reviews}">
        <p>아직 작성된 리뷰가 없습니다.</p>
    </c:when>
    <c:otherwise>
        <table border="1" style="width: 100%; border-collapse: collapse; text-align: center;">
            <tr style="background-color: #f2f2f2;">
                <th style="padding: 10px;">작성자</th>
                <th style="padding: 10px;">별점</th>
                <th style="padding: 10px;">리뷰 내용</th>
                <th style="padding: 10px;">작성일</th>
                <th style="padding: 10px;">삭제</th> <!-- 삭제 버튼 추가 -->
            </tr>
            <c:forEach var="review" items="${reviews}">
                <tr>
                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">${review.userid}</td>
                    
                    <!-- ⭐ 별점을 별 모양으로 변환 -->
                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">
                        <c:choose>
                            <c:when test="${review.rating == 5}">★★★★★</c:when>
                            <c:when test="${review.rating == 4}">★★★★☆</c:when>
                            <c:when test="${review.rating == 3}">★★★☆☆</c:when>
                            <c:when test="${review.rating == 2}">★★☆☆☆</c:when>
                            <c:when test="${review.rating == 1}">★☆☆☆☆</c:when>
                            <c:otherwise>없음</c:otherwise>
                        </c:choose>
                    </td>

                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">${review.reviewText}</td>
                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">${review.createdAt}</td>
                    
                    <!-- 삭제 버튼 -->
                    <td style="padding: 10px; border-bottom: 1px solid #ddd;">
                        <form action="/shop/reviews/delete" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');">
                            <input type="hidden" name="reviewId" value="${review.id}">
                        
                            <button type="submit" style="padding: 5px 10px; background-color: red; color: white; border: none; cursor: pointer;">
                                삭제
                            </button>
                           <input type="hidden" name="gCode" value="${review.gCode}">
                            
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
