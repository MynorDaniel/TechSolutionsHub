<%-- 
    Document   : componentes
    Created on : Mar 5, 2025, 8:11:48 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.techsolutionshub.models.Componente"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Componentes</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body class="bg-light">
        <jsp:include page="/includes/navbarEnsamblador.jsp"/>
        
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
        
        <!-- Botón para crear nuevo componente -->
        <div class="container mt-4 text-center">
            <a href="${pageContext.request.contextPath}/ensamblador/crearComponente.jsp" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Crear Componente
            </a>
        </div>
        
        <div class="container mt-4">
            <h2 class="text-center mb-4">Componentes Disponibles</h2>
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    ArrayList<Componente> componentes = (ArrayList<Componente>) request.getAttribute("componentesAtributo");
                    if (componentes != null) {
                        for (Componente componente : componentes) {
                %>
                <div class="col">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title"><%= componente.getNombre() %></h5>
                            <p class="card-text">
                                <strong>Costo:</strong> <%= componente.getCosto() %>
                            </p>
                            <div class="d-flex justify-content-between">
                                <!-- Formulario para eliminar -->
                                <form action="${pageContext.request.contextPath}/ComponenteServlet" method="post">
                                    <input type="hidden" name="_method" value="delete">
                                    <input type="hidden" name="id" value="<%= componente.getId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">
                                        <i class="bi bi-trash"></i> Eliminar
                                    </button>
                                </form>
                                <!-- Editar -->
                                <a href="${pageContext.request.contextPath}/ensamblador/editarComponente.jsp?id=<%= componente.getId() %>" class="btn btn-warning btn-sm">
                                    <i class="bi bi-pencil"></i> Editar
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <%
                        }
                    } else {
                %>
                <p class="text-center">No hay componentes registrados.</p>
                <%
                    }
                %>
            </div>
        </div>
        
        
    </body>
</html>

