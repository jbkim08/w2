<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-18
  Time: 오전 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>할일 읽기</title>
</head>
<body>
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="title" value="${dto.title}" readonly>
    </div>
    <div>
        <input type="date" name="dueDate" value="${dto.dueDate}" readonly>
    </div>
    <div>
        <input type="checkbox" name="finished" ${dto.finished ? "checked": ""} readonly >
    </div>
    <div>
        <a href="/todo/modify?tno=${dto.tno}">수정/삭제</a>
        <a href="/todo/list">리스트</a>
    </div>
</body>
</html>
