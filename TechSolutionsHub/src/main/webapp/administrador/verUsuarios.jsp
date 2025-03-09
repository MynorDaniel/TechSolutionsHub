<%-- 
    Document   : verUsuarios
    Created on : Mar 9, 2025, 1:41:52 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.techsolutionshub.models.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Usuarios</title>
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
        
        <div class="container mt-4">
            <h2 class="text-center mb-4">Usuarios Registrados</h2>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuariosAtributo");
                    if (usuarios != null) {
                        for (Usuario usuario : usuarios) {
                %>
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title"><%= usuario.getNombre() %></h5>
                            <p class="card-text">
                                <strong>Rol:</strong> <%= usuario.getRol() %><br>
                                <strong>Estado:</strong> <%= usuario.getEstado() %>
                            </p>
                            <div class="d-flex justify-content-between">
                                <!-- Formulario para eliminar -->
                                <form action="${pageContext.request.contextPath}/UsuarioServlet" method="post">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <input type="hidden" name="nombre" value="<%= usuario.getNombre() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </form>
                                <!-- Editar -->
                                <a href="${pageContext.request.contextPath}/administrador/editarUsuario.jsp?nombre=<%= usuario.getNombre() %>" class="btn btn-warning btn-sm">
                                    <i class="bi bi-pencil"></i>
                                </a>

                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    } else {
                %>
                <p class="text-center">No hay usuarios registrados.</p>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>

