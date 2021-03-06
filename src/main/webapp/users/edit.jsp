<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.User" %>
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

    <script>
        function validate() {
            var name = checkUserInput($('#name'));
            var email = checkUserInput($('#email'));
            var password = checkUserInput($('#password'));
            if (name !== "" && email !== "" && password !== "") {
                return true;
            }
            return false;
        }
        function checkUserInput(input) {
            var currentInput = $(input).val();
            if (currentInput === '' || currentInput === undefined) {
                alert("Поле: "+ $(input).attr('title') + " не заполнено")
                return "";
            }
            return currentInput;
        }
    </script>

    <title>Работа мечты</title>
</head>
<body>
<%
    String id = request.getParameter("id");
    User user = new User(0, "", "" ,"");
    if (id != null) {
        user = (User) request.getAttribute("UserById");
    }
%>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp"> <c:out value="${user.name}"/> | Выйти</a>
            </li>
        </ul>
        <div class="card" style="width: 100%">
            <div class="card-header">
                <% if (id == null) { %>
                Новый Пользователь.
                <% } else { %>
                Редактирование пользователя.
                <% } %>
            </div>
            <div class="card-body">
                <form action="<%=request.getContextPath()%>/users/edit.do?id=<%=user.getId()%>" method="post">
                    <div class="form-group">
                        <label>Имя</label>
                        <input type="text" id="name" title="Имя" class="form-control" name="name" value="<%=user.getName()%>">
                        <label>E-mail</label>
                        <input type="text" id="email" title="E-mail" class="form-control" name="email" value="<%=user.getEmail()%>">
                        <label>Password</label>
                        <input type="text" id="password" title="Password" class="form-control" name="password" value="<%=user.getPassword()%>">
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="return validate()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>