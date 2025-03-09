<%-- 
    Document   : crearUsuario
    Created on : Mar 6, 2025, 9:41:10 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Usuario</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-light">
        <jsp:include page="/includes/navbarAdministrador.jsp"/>
        
        <!-- Mensaje de alerta -->
        <div class="container mt-3">
            <%
                String mensajeAlerta = (String) request.getAttribute("mensajeAlerta");
                if (mensajeAlerta != null) {
            %>
                <div class="alert alert-info text-center" role="alert">
                    <%= mensajeAlerta %>
                </div>
            <%
                }
            %>
        </div>

        <div class="container d-flex justify-content-center align-items-center" style="min-height: 75vh;">
            <div class="card p-4 shadow-lg" style="width: 25rem;">
                <h2 class="text-center mb-4">Crear Usuario</h2>
                <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
                    <div class="mb-3">
                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label for="clave" class="form-label">Clave</label>
                        <input type="password" class="form-control" id="clave" name="clave" required>
                    </div>
                    <div class="mb-3">
                        <label for="rol" class="form-label">Rol</label>
                        <select class="form-select" id="rol" name="rol" required>
                            <option value="Ensamblador">Ensamblador</option>
                            <option value="Vendedor">Vendedor</option>
                            <option value="Administrador">Administrador</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Crear Usuario</button>
                </form>
            </div>
        </div>
    </body>
</html>

