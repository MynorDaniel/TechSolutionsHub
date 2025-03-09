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
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100 bg-light">

        <div class="card p-4 shadow-lg" style="width: 22rem;">
            <h2 class="text-center mb-4">Iniciar Sesión</h2>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <div class="mb-3">
                    <label for="clave" class="form-label">Clave</label>
                    <input type="password" class="form-control" id="clave" name="clave" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Ingresar</button>
            </form>
        </div>

    </body>
</html>
