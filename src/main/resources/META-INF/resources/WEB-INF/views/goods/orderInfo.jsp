<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

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
