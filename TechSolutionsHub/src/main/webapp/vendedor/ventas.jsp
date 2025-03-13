<%-- 
    Document   : ventas
    Created on : Mar 5, 2025, 8:08:59 PM
    Author     : mynordma
--%>

<%@page import="com.mycompany.techsolutionshub.models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas - Vendedor</title>
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
            <h2>Ventas del Día</h2>

            <!-- Botón Nueva Venta -->
            <form action="VentaServlet" method="get" class="mb-3">
                <input type="hidden" name="_method" value="crearVenta">
                <button type="submit" class="btn btn-primary">Nueva Venta</button>
            </form>

            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Cliente (NIT)</th>
                        <th>Computadora ID</th>
                        <th>Precio Venta</th>
                        <th>Fecha Venta</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<ArrayList<Venta>> ventas = (ArrayList<ArrayList<Venta>>) request.getAttribute("ventasAtributo");
                        if (ventas != null && !ventas.isEmpty() && !ventas.get(0).isEmpty()) {
                            for (Venta venta : ventas.get(0)) { // Ventas del día
                                Cliente cliente = venta.getCliente();
                                for (Computadora pc : venta.getProductos()) {
                    %>
                    <tr>
                        <td><%= cliente.getNombre() %> (<%= cliente.getNit() %>)</td>
                        <td><%= pc.getId() %></td>
                        <td>Q<%= pc.getPrecioVenta() %></td>
                        <td><%= pc.getFechaVenta() %></td>
                    </tr>
                    <% 
                                }
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">No hay ventas registradas para hoy.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <!-- Sección de Ventas Totales -->
        <div class="container mt-4">
            <h2>Ventas Totales</h2>
            <table class="table table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Cliente (NIT)</th>
                        <th>Computadora ID</th>
                        <th>Precio Venta</th>
                        <th>Fecha Venta</th>
                        <th>Devolución</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (ventas != null && ventas.size() > 1 && !ventas.get(1).isEmpty()) {
                            for (Venta venta : ventas.get(1)) { // Ventas totales
                                Cliente cliente = venta.getCliente();
                                for (Computadora pc : venta.getProductos()) {
                    %>
                    <tr>
                        <td><%= cliente.getNombre() %> (<%= cliente.getNit() %>)</td>
                        <td><%= pc.getId() %></td>
                        <td>Q<%= pc.getPrecioVenta() %></td>
                        <td><%= pc.getFechaVenta() %></td>
                        <td>
                            <!-- Formulario de devolución -->
                            <form action="ComputadoraServlet" method="post">
                                <input type="hidden" name="_method" value="devolver">
                                <input type="hidden" name="nit" value="<%= cliente.getNit() %>">
                                <input type="hidden" name="computadoraId" value="<%= pc.getId() %>">
                                <button type="submit" class="btn btn-warning btn-sm">Devolver</button>
                            </form>
                        </td>
                    </tr>
                    <% 
                                }
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">No hay ventas registradas.</td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>


    </body>
</html>

