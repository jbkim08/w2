<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-18
  Time: 오전 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>투두 리스트</title>
</head>
<body>
<ul>
    <h1>Todo List</h1>
<%-- 컨트롤러에서 dtolist를 보내줌   --%>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <span><a href="/todo/read?tno=${dto.tno}">${dto.tno}</a></span>
            <span>${dto.title}</span>
            <span>${dto.dueDate}</span>
            <span>${dto.finished? "완료" : "안됨"}</span>
        </li>
    </c:forEach>
</ul>
</body>
</html>
