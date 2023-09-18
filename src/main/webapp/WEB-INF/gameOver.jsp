<%--
  Created by IntelliJ IDEA.
  User: vanja
  Date: 9/13/2023
  Time: 3:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>

<div class="d-flex justify-content-center">
<form action="${pageContext.request.contextPath}/logic" method="post">

        <c:choose>
            <c:when test="${currentQuestion.isWon()}">
                <h1>Ты победил !</h1>
            </c:when>

            <c:otherwise>
                <h1>Ты проиграл!</h1>
            </c:otherwise>
        </c:choose>

        <div class="d-flex justify-content-center">
            <button class="btn btn-primary" type="submit">Попробовать заново</button>
        </div>
    </form>
</div>
<body>
</body>
</html>
