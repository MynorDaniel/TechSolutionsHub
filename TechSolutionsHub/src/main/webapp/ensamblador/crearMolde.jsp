<%-- 
    Document   : crearMolde
    Created on : Mar 10, 2025, 12:27:29 AM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.mycompany.techsolutionshub.models.Componente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Molde</title>
    <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarEnsamblador.jsp"/>
        <div class="container mt-4">
            <h2 class="text-center">Crear Nuevo Molde</h2>

            <form action="${pageContext.request.contextPath}/MoldeServlet" method="post">
                <input type="hidden" name="_method" value="create">

                <!-- Nombre del Molde -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Nombre del Molde:</label>
                    <input type="text" name="nombreMolde" class="form-control" required>
                </div>

                <!-- Lista de Componentes con Checkboxes -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Selecciona los Componentes:</label>
                    <div class="form-check">
                        <%
                            List<Componente> componentes = (List<Componente>) request.getAttribute("componentesAtributo");
                            if (componentes != null && !componentes.isEmpty()) {
                                for (Componente componente : componentes) {
                        %>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="componentesSeleccionados" value="<%= componente.getId() %>">
                                <label class="form-check-label" ><%= componente.getNombre() %></label>
                                <label class="form-check-label"> - $<%= componente.getCosto() %></label>
                            </div>
                        <%
                            }      
                                
                            } else {
                        %>
                            <p class="text-muted">No hay componentes disponibles.</p>
                        <%
                            }
                            
                        %>
                    </div>
                </div>

                <!-- Botón para Crear el Molde -->
                <button type="submit" class="btn btn-success">
                    <i class="bi bi-plus-lg"></i> Crear Molde
                </button>
            </form>
        </div>

    </body>
</html>
