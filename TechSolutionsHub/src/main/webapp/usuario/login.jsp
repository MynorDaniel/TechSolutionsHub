<%-- 
    Document   : login
    Created on : Mar 4, 2025, 8:38:27 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h2>Iniciar Sesión</h2>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
            Nombre: <input type="text" name="nombre" required><br>
            Clave: <input type="password" name="clave" required><br>
            <input type="submit" value="Ingresar">
        </form>
    </body>
</html>
