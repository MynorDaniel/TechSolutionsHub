<%-- 
    Document   : moldes
    Created on : Mar 5, 2025, 8:11:57 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.mycompany.techsolutionshub.models.Molde" %>
<%@page import="com.mycompany.techsolutionshub.models.Componente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Moldes Ensamblador</title>
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
                <h2 class="mb-0">Lista de Moldes</h2>
                <!-- Botón Crear Molde con Formulario -->
                <form action="${pageContext.request.contextPath}/ComponenteServlet" method="get" class="d-inline">
                    <input type="hidden" name="_method" value="crearMolde">
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-plus-lg"></i> Crear Molde
                    </button>
                </form>

            </div>
            
            <div class="row">
                <%
                    List<Molde> moldes = (List<Molde>) request.getAttribute("moldesAtributo");
                    if (moldes != null && !moldes.isEmpty()) {
                        for (Molde molde : moldes) {
                        double costoTotal = 0;
                %>
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title"><%= molde.getNombre() %></h5>

                                <table class="table table-sm">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Componentes</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (Componente componente : molde.getComponentes()) {
                                            costoTotal += componente.getCosto();
                                        %>
                                        <tr>
                                            <td><%= componente.getNombre() %> - $<%= componente.getCosto() %></td>
                                        </tr>
                                        <%
                                            }
                                           
                                        %>
                                    </tbody>
                                    
                                </table>
                                
                               
                                <p class="card-text fw-bold text-primary">Costo Ensamblaje: $<%= costoTotal %></p>
                                           
                                    
                                <div class="d-flex justify-content-between">
                                    <!-- Botón Eliminar -->
                                    <form action="MoldeServlet" method="post">
                                        <input type="hidden" name="nombre" value="<%= molde.getNombre() %>">
                                        <input type="hidden" name="_method" value="delete">
                                        <button type="submit" class="btn btn-danger btn-sm">
                                            <i class="bi bi-trash"></i> Eliminar
                                        </button>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                <%
                        }
                    } else {
                %>
                    <div class="col-12">
                        <p class="text-center text-muted">No hay moldes disponibles.</p>
                    </div>
                <%
                    }
                %>
            </div>
        </div>
    </body>
</html>


