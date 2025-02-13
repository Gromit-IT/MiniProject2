<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet"
	href="webjars/bootstrap/5.3.3/css/bootstrap.min.css">
</head>
<body>
</div>
	 <div class="App">
	<jsp:include page="../common/top.jsp" flush="true" /><br>
 </div>
	<div class="container">
		<h2>상품 등록</h2>
		<form action="/shop/goodsAdd" method="post"
			enctype="multipart/form-data">
			<div class="mb-3">
            <label class="form-label">상품 코드 (gCode)</label>
            <input type="text" class="form-control" name="gCode" required>
        </div>
			<div class="mb-3">
				<label class="form-label">상품명</label> <input type="text"
					class="form-control" name="gName" required>
			</div>
			<div class="mb-3">
				<label class="form-label">카테고리</label> <select class="form-select"
					name="gCategory">
					<option value="outer">아우터</option>
					<option value="top">상의</option>
					<option value="bottom">하의</option>
					<option value="dress">드레스</option>
				</select>
			</div>
			<div class="mb-3">
				<label class="form-label">가격</label> <input type="number"
					class="form-control" name="gPrice" required>
			</div>
			<div class="mb-3">
				<label class="form-label">설명</label>
				<textarea class="form-control" name="gContent" rows="3"></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">상품 이미지</label> <input type="file"
					class="form-control" name="gImage">
			</div>
			<button type="submit" class="btn btn-primary">등록</button>
		</form>

	
</body>
</html>
