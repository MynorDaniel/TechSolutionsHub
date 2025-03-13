<%-- 
    Document   : crearVenta
    Created on : Mar 12, 2025, 11:26:43 AM
    Author     : mynordma
--%>

<%@page import="com.mycompany.techsolutionshub.models.Computadora"%>
<%@page import="com.mycompany.techsolutionshub.models.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Venta</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarVendedor.jsp"/>
        
        <div class="container mt-4">
            <h2 class="text-center">Crear Venta</h2>

            <form action="VentaServlet" method="post" class="mt-4">
                <!-- Campo oculto para identificar la acción -->
                <input type="hidden" name="_method" value="crearVenta">

                <!-- Selección de cliente -->
                <div class="mb-3">
                    <label for="cliente" class="form-label">Seleccione un cliente:</label>
                    <select name="cliente" id="cliente" class="form-select" required>
                        <option value="">-- Seleccione --</option>
                        <%
                            ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("clientesAtributo");
                            if (clientes != null) {
                                for (Cliente cliente : clientes) {
                        %>
                                    <option value="<%= cliente.getNit() %>">
                                        <%= cliente.getNombre() %> (<%= cliente.getNit() %>)
                                    </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- Selección de computadoras -->
                <div class="mb-3">
                    <label class="form-label">Seleccione una o más computadoras:</label>
                    <div class="form-check">
                        <%
                            ArrayList<Computadora> computadoras = (ArrayList<Computadora>) request.getAttribute("computadorasAtributo");
                            if (computadoras != null) {
                                for (Computadora computadora : computadoras) {
                                    // Filtrar computadoras con estado "En venta"
                                    if ("En venta".equals(computadora.getEstado())) {
                        %>
                                        <div class="form-check">
                                            <input type="checkbox" name="computadoras" value="<%= computadora.getId() %>" class="form-check-input">
                                            <label class="form-check-label">
                                                <%= computadora.getMolde() %> - $<%= computadora.getPrecioVenta() %>
                                            </label>
                                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Generar factura</button>
            </form>
        </div>

    </body>
</html>

