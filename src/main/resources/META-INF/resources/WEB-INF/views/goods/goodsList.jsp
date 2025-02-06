<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>

<div class="container">
  <div class="row">
  
  <c:forEach var="dto" items="${goodsList}">
    <div class="col border border-dark m-4 w-80">
      <a href="goodsRetrieve?gCode=${dto.gCode}"> 
		<img src="images/items/${dto.gImage}.gif" width="200">
	  </a>
       <div class="mt-4 fs-6">${dto.gName}</div>
       <div class="mt-2 fs-6">${dto.gContent}</div>
       <div class="mt-2 fs-6">가격 : ${dto.gPrice}</div>
       <div class="mt-2 fs-6">판매 수 : ${dto.purchaseCount}</div>
       <div class="mt-2 fs-6">등록 일 : ${dto.regDate}</div>
       
    </div>
  </c:forEach>  

  </div>
</div>