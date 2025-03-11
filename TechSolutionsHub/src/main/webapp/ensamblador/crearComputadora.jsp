<%-- 
    Document   : crearComputadora
    Created on : Mar 10, 2025, 8:05:00 PM
    Author     : mynordma
--%>

<%@page import="com.mycompany.techsolutionshub.models.Molde"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ensamblar Computadora</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/navbarEnsamblador.jsp"/>
        
        <div class="container mt-4">
            <h2>Ensamblar Computadora</h2>
            
            <form action="ComputadoraServlet" method="post">
                <input type="hidden" name="_method" value="create">
                
                <div class="mb-3">
                    <label for="precioVenta" class="form-label">Precio de Venta</label>
                    <input type="number" class="form-control" id="precioVenta" name="precioVenta" required>
                </div>

                <div class="mb-3">
                    <label for="molde" class="form-label">Selecciona el Molde</label>
                    <select class="form-select" id="molde" name="molde" required>
                        <option value="" disabled selected>Selecciona un Molde</option>
                        <% 
                            // Obteniendo los moldes desde el request
                            List<Molde> moldes = (List<Molde>) request.getAttribute("moldesAtributo");
                            if (moldes != null && !moldes.isEmpty()) {
                                for (Molde molde : moldes) {
                        %>
                                    <option value="<%= molde.getNombre() %>"><%= molde.getNombre() %></option>
                        <%  
                                }
                            }
                        %>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Ensamblar Computadora</button>
            </form>
        </div>

    </body>
</html>


