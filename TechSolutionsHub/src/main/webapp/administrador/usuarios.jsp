<%-- 
    Document   : usuarios
    Created on : Mar 5, 2025, 7:32:09 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarAdministrador.jsp"/>
        
        <div class="container mt-4">

            <!-- Menú -->
            <div class="list-group">
                <!-- Formulario para hacer un GET al servlet UsuarioServlet -->
                <form action="${pageContext.request.contextPath}/UsuarioServlet" method="get" class="d-inline">
                    <button type="submit" class="list-group-item list-group-item-action">Ver Usuarios</button>
                </form>
                
                <!-- Enlace para ir a crearUsuario.jsp -->
                <a href="crearUsuario.jsp" class="list-group-item list-group-item-action">Crear Usuario</a>
            </div>
        </div>
        
    </body>
</html>
