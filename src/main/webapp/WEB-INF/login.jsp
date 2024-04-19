<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-19
  Time: 오전 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h1>로그인 페이지</h1>
    <c:if test="${param.result == 'error'}">
        <h1>로그인 에러(아이디 혹은 비밀번호가 맞지 않습니다!)</h1>
    </c:if>
    <form action="/login" method="post">
        <input type="text" name="mid" placeholder="아이디">
        <input type="text" name="mpw" placeholder="비번">
        <button type="submit">로그인</button>
    </form>
</body>
</html>
