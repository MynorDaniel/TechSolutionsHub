<%-- 
    Document   : computadoras
    Created on : Mar 5, 2025, 8:12:04 PM
    Author     : mynordma
--%>

<%@page import="java.util.List"%>
<%@page import="com.mycompany.techsolutionshub.models.Computadora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Computadoras Ensamblador</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
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

        <div class="container mt-4">
            
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2>Computadoras Ensambladas</h2>

                <!-- Botón Ensamblar Computadora -->
                <form action="MoldeServlet" method="get" class="d-inline">
                    <input type="hidden" name="_method" value="create">
                    <button type="submit" class="btn btn-success"><i class="bi bi-plus-lg"></i>Ensamblar Computadora</button>
                </form>  

            </div>
            
            

            <div class="row row-cols-1 row-cols-md-3 g-4">
                <%
                    List<Computadora> computadoras = (List<Computadora>) request.getAttribute("computadorasAtributo");
                    if (computadoras != null && !computadoras.isEmpty()) {
                        for (Computadora comp : computadoras) {
                %>
                <div class="col">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title">ID: <%= comp.getId() %></h5>
                            <p class="card-text"><strong>Cliente:</strong> <%= comp.getCliente() %></p>
                            <p class="card-text"><strong>Ensamblador:</strong> <%= comp.getUsuario() %></p>
                            <p class="card-text"><strong>Molde:</strong> <%= comp.getMolde() %></p>
                            <p class="card-text"><strong>Estado:</strong> <%= comp.getEstado() %></p>
                            <p class="card-text"><strong>Precio de Venta:</strong> $<%= comp.getPrecioVenta() %></p>
                            <p class="card-text"><strong>Fecha de Ensamble:</strong> <%= comp.getFechaEnsamble() %></p>
                            <p class="card-text"><strong>Fecha de Venta:</strong> <%= (comp.getFechaVenta() != null) ? comp.getFechaVenta() : "---" %></p>
                        </div>
                        <div class="card-footer text-center">
                            <!-- Botón Eliminar -->
                            <form action="ComputadoraServlet" method="post" class="d-inline">
                                <input type="hidden" name="_method" value="delete">   
                                <input type="hidden" name="id" value="<%= comp.getId() %>">
                                <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <%
                        }
                    } else {
                %>
                <p class="text-muted">No hay computadoras ensambladas.</p>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>

