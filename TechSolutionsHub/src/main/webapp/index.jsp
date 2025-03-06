<%-- 
    Document   : index
    Created on : Feb 21, 2025, 1:16:15 AM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bienvenido - La Computadora Feliz</title>
        <jsp:include page="includes/resources.jsp"/>
    </head>
    <body class="bg-light">
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h1 class="display-4 fw-bold text-primary">¡Bienvenido a La Computadora Feliz!</h1>
                    <p class="lead text-secondary mt-3">
                        Este sistema le permite gestionar fácilmente la producción y venta de computadoras personalizadas, desde el ingreso de componentes hasta la entrega final.
                    </p>
                    <a href="usuario/login.jsp" class="btn btn-primary mt-4">Ingresar al Sistema</a>
                </div>
            </div>
        </div>
    </body>
</html>

