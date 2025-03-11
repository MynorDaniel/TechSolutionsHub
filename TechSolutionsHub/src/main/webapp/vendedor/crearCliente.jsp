<%-- 
    Document   : crearCliente
    Created on : Mar 10, 2025, 9:37:31 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Cliente</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarVendedor.jsp"/>
        <div class="container mt-4">
            <h2 class="mb-4">Registrar Cliente</h2>
            <form action="${pageContext.request.contextPath}/ClienteServlet" method="post">
                <input type="hidden" name="_method" value="create">
                
                <div class="mb-3">
                    <label for="nit" class="form-label">NIT</label>
                    <input type="text" class="form-control" id="nit" name="nit" required>
                </div>

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion" required>
                </div>

                <button type="submit" class="btn btn-primary">Registrar Cliente</button>
            </form>
        </div>
    </body>
</html>

