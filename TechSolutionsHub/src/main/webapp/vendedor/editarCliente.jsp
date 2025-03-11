<%-- 
    Document   : editarCliente
    Created on : Mar 10, 2025, 9:39:35 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cliente</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarVendedor.jsp"/>

        <div class="container mt-4">
            <h2>Editar Cliente</h2>

            <%
                String nitCliente = request.getParameter("nit");
            %>

            <form action="${pageContext.request.contextPath}/ClienteServlet" method="post">
                <input type="hidden" name="_method" value="edit">
                <input type="hidden" name="nit" value="<%= nitCliente %>">

                <div class="mb-3">
                    <label for="nit" class="form-label">NIT</label>
                    <input type="text" class="form-control" id="nit" name="nit" value="<%= nitCliente %>" disabled>
                </div>

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre">
                </div>

                <div class="mb-3">
                    <label for="direccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="direccion" name="direccion">
                </div>

                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </form>
        </div>
    </body>
</html>

