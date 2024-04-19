<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-18
  Time: 오후 2:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>수정/삭제</title>
</head>
<body>
<form action="/todo/modify" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" >
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}" >
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked":""} >
    </div>
    <div>
        <button type="submit">수정하기</button>
    </div>
</form>
<%--삭제하기 폼--%>
<form action="/todo/remove" method="post">
    <input type="hidden" name="tno" value="${dto.tno}" readonly>
    <div>
        <button type="submit">삭제</button>
    </div>
</form>
</body>
</html>
