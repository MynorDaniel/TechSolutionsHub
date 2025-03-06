<%-- Verificar el rol y hacer el include correspondiente --%>
<%
    HttpSession sesion = request.getSession();
    String rolUsuario = (String) sesion.getAttribute("rolAtributoSesion");

    if ("Administrador".equals(rolUsuario)) {
    %>
        <jsp:include page="/includes/navbarAdministrador.jsp"/>
    <%
    } else if ("Vendedor".equals(rolUsuario)) {
    %>
        <jsp:include page="/includes/navbarVendedor.jsp"/>
    <%    
    } else if ("Ensamblador".equals(rolUsuario)) {
    %>
        <jsp:include page="/includes/navbarEnsamblador.jsp"/>
    <% 
    }
%>