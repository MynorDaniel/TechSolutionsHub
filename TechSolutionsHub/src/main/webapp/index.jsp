<%-- 
    Document   : index
    Created on : Feb 21, 2025, 1:16:15 AM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Inicio</title>
        <jsp:include page="/includes/resources.jsp"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body class="d-flex justify-content-center align-items-center vh-100 bg-light">
        <div class="text-center p-5 bg-white shadow rounded">
            <h1 class="mb-4">Bienvenido a la Aplicación</h1>
            <p class="mb-4">Por favor, inicia sesión para continuar.</p>
            <a href="usuario/login.jsp" class="btn btn-primary btn-lg">Iniciar Sesión</a>
        </div>
    </body>
</html>

