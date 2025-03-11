<%-- 
    Document   : crearComponente
    Created on : Mar 9, 2025, 6:33:33 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Componente</title>
        <jsp:include page="/includes/resources.jsp"/> <!-- Asegúrate de que este JSP incluya Bootstrap -->
    </head>
    <body class="bg-light">
        <jsp:include page="/includes/navbarEnsamblador.jsp"/>
        
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-sm">
                        <div class="card-header bg-primary text-white text-center">
                            <h4>Crear Nuevo Componente</h4>
                        </div>
                        <div class="card-body">
                            <form action="<%= request.getContextPath() %>/ComponenteServlet" method="post">
                                <input type="hidden" name="_method" value="create">
                                
                                <div class="mb-3">
                                    <label for="nombre" class="form-label">Nombre del Componente</label>
                                    <input type="text" id="nombre" name="nombre" class="form-control" required>
                                </div>
                                
                                <div class="mb-3">
                                    <label for="costo" class="form-label">Costo</label>
                                    <input type="number" id="costo" name="costo" class="form-control" step="0.01" required>
                                </div>
                                
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-success">Crear Componente</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

