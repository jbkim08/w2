<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2024-04-18
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/todo/register" method="post">
    <div>
        <input type="text" name="title" placeholder="할일입력">
    </div>
    <div>
        <input type="date" name="dueDate" >
    </div>
    <div>
        <button type="reset">리셋</button>
        <button type="submit">등록</button>
    </div>
</form>
</body>
</html>
