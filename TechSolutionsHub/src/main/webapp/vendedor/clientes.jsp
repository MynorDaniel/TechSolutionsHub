<%-- 
    Document   : clientes
    Created on : Mar 5, 2025, 8:08:45 PM
    Author     : mynordma
--%>

<%@page import="com.mycompany.techsolutionshub.models.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes Vendedor</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarVendedor.jsp"/>
        
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
            <!-- Botón para Crear Cliente -->
            <a href="${pageContext.request.contextPath}/vendedor/crearCliente.jsp" class="btn btn-success mb-4">Crear Cliente</a>

            <div class="row">
                <% 
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientesAtributo");
                    if (clientes != null && !clientes.isEmpty()) {
                        for (Cliente cliente : clientes) {
                %>
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title"><%= cliente.getNombre() %></h5>
                                    <p class="card-text">
                                        <strong>NIT:</strong> <%= cliente.getNit() %><br>
                                        <strong>Dirección:</strong> <%= cliente.getDireccion() %>
                                    </p>
                                    <a href="${pageContext.request.contextPath}/vendedor/editarCliente.jsp?nit=<%= cliente.getNit() %>" class="btn btn-warning btn-sm">Editar</a>
                                    <form action="${pageContext.request.contextPath}/ClienteServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="_method" value="delete">
                                        <input type="hidden" name="nit" value="<%= cliente.getNit() %>">
                                        <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                <%  
                        }
                    } else {
                %>
                        <p>No hay clientes disponibles.</p>
                <% 
                    }
                %>
            </div>
        </div>
    </body>
</html>
