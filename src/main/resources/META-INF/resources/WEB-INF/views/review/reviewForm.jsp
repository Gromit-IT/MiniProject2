<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String gCode = request.getParameter("gCode");
%>

<form action="/shop/reviews/add" method="post" style="margin-bottom: 20px;">
    <input type="hidden" name="gCode" value="<%= gCode %>">

    <label for="rating"><b>별점 (1~5):</b></label>
    <select name="rating" id="rating" required>
        <option value="5">★★★★★ (5)</option>
        <option value="4">★★★★☆ (4)</option>
        <option value="3">★★★☆☆ (3)</option>
        <option value="2">★★☆☆☆ (2)</option>
        <option value="1">★☆☆☆☆ (1)</option>
    </select>
    <br><br>

    <label for="reviewText"><b>리뷰 내용:</b></label><br>
    <textarea id="reviewText" name="reviewText" rows="4" cols="50" required style="width: 100%;"></textarea>
    <br><br>

    <button type="submit" style="padding: 5px 15px; font-size: 16px;">리뷰 등록</button>
</form>
