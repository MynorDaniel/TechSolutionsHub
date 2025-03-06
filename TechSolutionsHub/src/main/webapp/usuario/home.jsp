<%-- 
    Document   : home
    Created on : Mar 4, 2025, 9:17:48 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession sesion = request.getSession();
    String nombreUsuario = (String) sesion.getAttribute("nombreAtributoSesion");
    String rolUsuario = (String) sesion.getAttribute("rolAtributoSesion");
%>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bienvenido - La Computadora Feliz</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-light">
        
        <jsp:include page="/includes/navbar.jsp"/>
        
        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-md-8 text-center">
                    <h1 class="display-4 fw-bold text-primary">¡Bienvenido a La Computadora Feliz!</h1>
                    <p class="lead text-secondary mt-3">
                        Hola, <strong><%= nombreUsuario %></strong>. Tu rol es: <strong><%= rolUsuario %></strong>.
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>