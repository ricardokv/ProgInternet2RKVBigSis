<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BibSis</title>
        <!-- Início Bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <script src="js/bootstrap.min.js"></script>
        <!-- Término Bootstrap -->
    </head>
    <body>
        <dl class="dl-horizontal">
            <dd><h1>BibSis - Login</h1></dd><br>
        <%
        String err = (String) request.getParameter("error");
        if ((err != null) && (err.equals("1"))) {
            %>
            <dd><p class="text-error">Matrícula e/ou senha inválidos!!</p></dd>
            <%
        }
        %>
            <form action="./Login"method="POST">
                <dt>Matrícula</dt>
                <dd><input type="text" name="usuario"/></dd>
                <br>
                <dt>Senha</dt>
                <dd><input type="password" name="senha"/></dd>
                <br>
                <dd><input type="submit" value="Enviar"/></dd>
            </form>
        </dl>
    </body>
</html>
