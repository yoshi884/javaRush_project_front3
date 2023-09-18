<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<body>
<h2 class="d-flex justify-content-center">Вопрос : ${currentQuestion.getText()}</h2>

<%@include file="stat.jsp" %>

<form action="${pageContext.request.contextPath}/logic" method="post">


        <c:forEach var="answer" items="${currentQuestion.getAnswers()}">
            <div class="form-check d-flex justify-content-center">
                <input class="form-check-input" type="radio" name="question" id="answers"
                       value="${answer.getNextQuestionId()}" required>
                <label class="form-check-label" for="answers">${answer.getText()}</label>
            </div>
        </c:forEach>
    <div class="d-flex justify-content-center">
        <button type="submit" class="btn btn-primary">Ответить</button>
    </div>

<%--<input class="btn btn-primary" type="submit" value="Ответить">--%>
</form>


</body>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
</html>