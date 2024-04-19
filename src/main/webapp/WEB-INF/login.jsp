<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-19
  Time: 오전 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
    <h1>로그인 페이지</h1>
    <form action="/login" method="post">
        <input type="text" name="mid" placeholder="아이디">
        <input type="text" name="mpw" placeholder="비번">
        <button type="submit">로그인</button>
    </form>
</body>
</html>
