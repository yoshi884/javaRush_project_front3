<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <p>Java-игра. Состоит из заданий-квестов на знание Java. Цель игры: пройти все уровни. Следующий уровень
        разблокируется
        после успешного прохождения одного квеста текущего уровня. Желаю удачи!
    </p>
    <div class="d-flex justify-content-center pt-4">
        <div class="form-group">
            <h1 for="nameInput" >Введите Ваше имя</h1>
            <textarea class="form-control" rows="1" name="userName" id="nameInput" required ></textarea>
            <button type="submit" class="btn btn-primary"  >Submit</button>
        </div>
    </div>
</form>
</body>
</html>