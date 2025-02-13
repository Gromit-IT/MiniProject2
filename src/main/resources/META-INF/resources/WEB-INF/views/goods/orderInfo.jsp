<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- Bootstrap CSS 추가 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<script src="webjars/jquery/3.7.1/jquery.min.js"></script>

<!-- Bootstrap JS 및 Popper.js 추가 -->
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"></script>

<form name="myForm" action="/shop/orderInfo" method="get">
	<div class="container">
		<div class="row mb-3">
			<label class="col-sm-2 col-form-label fs-4">주문 내역</label>
		</div>
		<hr>
		<div>
			<table class="table">

				<thead>
					<tr>
						<th>번호</th>
						<th>상품이미지</th>
						<th>상품정보</th>
						<th>상품가격</th>
						<th>상품수량</th>
						<th>주문날짜</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="order" items="${oDTO}">
						<tr>
							<td>${order.num}</td>
							<td><img src="images/items/${order.gImage}.gif" width="50"
								height="50"></td>
							<td>${order.gCode}&nbsp;/&nbsp;${order.gSize}&nbsp;/&nbsp;${order.gColor}</td>
							<td>${order.gPrice}</td>
							<td>${order.gAmount}</td>
							<td>${order.orderDay}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>

</form>
<div class="d-flex justify-content-center align-items-center">
    <!-- Cancel Button -->
    <button type="button" class="btn btn-secondary" onclick="window.location.href='main';">Back</button>
</div>

