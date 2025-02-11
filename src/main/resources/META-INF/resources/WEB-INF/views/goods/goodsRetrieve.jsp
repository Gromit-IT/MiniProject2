<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="webjars/jquery/3.7.1/jquery.min.js"></script>
<script>
  $(document).ready(function(){
	  $("#up").on("click", function(){
		  var gAmount = $("#gAmount").val();
		  $("#gAmount").val(Number.parseInt(gAmount) + 1);
	  });

	  $("#down").on("click", function(){
		  var gAmount = Number.parseInt($("#gAmount").val());
		  if(gAmount > 1){
		    $("#gAmount").val(gAmount - 1);
		  }
	  });

	  $("#cartBtn").on("click", function(event){
	    	event.preventDefault();
	    	var f = $("form")[0];
	    	f.action="cartAdd";
	    	f.method="GET";
	    	f.submit();
	  });
  });
</script>

<div class="container">
	<div>${errorMessage}</div>
	<form class="row g-3 m-4">
		<input type="hidden" name="gCode" value="${goodsRetrieve.gCode}">

		<div class="row">
			<!-- ✅ 상품 정보 카드 -->
			<div class="card" style="width: 25rem;">
				<img src="images/items/${goodsRetrieve.gImage}.gif" class="card-img-top" alt="상품 이미지">
				<div class="card-body">
					<h6 class="card-title"><span class="fw-bold">제품코드:</span> ${goodsRetrieve.gCode}</h6>
					<h6 class="card-text"><span class="fw-bold ">상품명:</span> ${goodsRetrieve.gName}</h6>
					<h6 class="card-text"><span class="fw-bold ">가격:</span> ${goodsRetrieve.gPrice}</h6>
					<h6 class="card-text">
						<span class="fw-bold ">상품옵션:</span> 
						<select name="gSize" id="gSize">
							<option selected value="사이즈선택">사이즈선택</option>
							<option value="L">L</option>
							<option value="M">M</option>
							<option value="S">S</option>
						</select> 
						<select name="gColor" id="gColor">
							<option selected value="색상선택">색상선택</option>
							<option value="navy">navy</option>
							<option value="black">black</option>
							<option value="ivory">ivory</option>
							<option value="white">white</option>
							<option value="gray">gray</option>
						</select>
					</h6>
					<h6 class="card-text">
						<span class="fw-bold ">주문수량:</span> 
						<input type="text" name="gAmount" value="1" id="gAmount">
						<img src="images/up.PNG" id="up"> <img src="images/down.PNG" id="down">
					</h6>
					<button class="btn btn-primary mt-3">구매</button>
					<button class="btn btn-primary mt-3" id="cartBtn">장바구니</button>
				</div>
			</div>

			<!-- ✅ 상품 상세 설명 & 리뷰 작성 폼 -->
			<div style="width: 50%; border-left: 1px solid #ddd; padding-left: 20px;">
				<h3>상품 상세 설명</h3>
				<p>${goodsRetrieve.gContent}</p>

				<!-- ✅ 상품 상세 설명 바로 아래 리뷰 작성 폼 추가 -->
				
			</div>

			<!-- ✅ 리뷰 목록 (작성 폼 아래) -->
			<div style="width: 100%; margin-top: 20px;">
				<h3>리뷰 목록</h3>
				<jsp:include page="../review/reviewList.jsp">
	    			<jsp:param name="gCode" value="${goodsRetrieve.gCode}" />
				</jsp:include>
			</div>
			
		</div>
	</form>
</div>
