<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Работа мечты</title>
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
            </li>
        </ul>
        <div class="card" style="width: 100%">
            <div class="card-header">
                Пользователи
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Имя пользователя</th>
                        <th scope="col">Почта</th>
                        <th scope="col">Пароль</th>
                    </tr>
                    <tr>
                        <c:forEach items="${usersapp}" var="users">
                        <td>
                            <a href='<c:url value="/users/edit.do?id=${users.id}"/>'>
                                <i class="fa fa-edit mr-3"></i>
                            </a>
                            <c:out value="${users.name}"/>
                            </a>
                            <br>
                            <a href='<c:url value="/users/delete.do?id=${users.id}"/>'>
                                Удалить пользователя
                            </a>
                        </td>
                        <td>
                            <c:out value="${users.email}"/>
                        </td>
                        <td>
                            <c:out value="${users.password}"/>
                        </td>
                    </tr>
                    </c:forEach>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
                <a href='<c:url value="/index.do"/>'>
                    На главную страницу
                </a>
                <br>
                <a href='<c:url value="/users/edit.do"/>'>
                    Создать пользователя
                </a>
            </div>
        </div>
    </div>
</div>
</body>
</html>