<%-- 
    Document   : editarUsuario
    Created on : Mar 9, 2025, 2:33:37 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-light">
        <jsp:include page="/includes/navbarAdministrador.jsp"/>

        <%
            String nombreUsuario = request.getParameter("nombre");
        %>

        <div class="container d-flex justify-content-center align-items-center" style="min-height: 80vh;">
            <div class="card p-4 shadow-lg" style="width: 25rem;">
                <h2 class="text-center mb-4">Editar Usuario</h2>
                <p class="text-center fw-bold text-secondary"><%= nombreUsuario != null ? nombreUsuario : "No especificado" %></p>

                <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
                    <input type="hidden" name="_method" value="PATCH">
                    <input type="hidden" name="nombre" value="<%= nombreUsuario != null ? nombreUsuario : "" %>">

                    <div class="mb-3">
                        <label for="clave" class="form-label">Clave (Dejar vacío para no cambiar)</label>
                        <input type="password" class="form-control" id="clave" name="clave">
                    </div>
                    <div class="mb-3">
                        <label for="rol" class="form-label">Rol</label>
                        <select class="form-select" id="rol" name="rol">
                            <option value="">No cambiar</option>
                            <option value="Ensamblador">Ensamblador</option>
                            <option value="Vendedor">Vendedor</option>
                            <option value="Administrador">Administrador</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Guardar Cambios</button>
                </form>
            </div>
        </div>

    </body>
</html>

