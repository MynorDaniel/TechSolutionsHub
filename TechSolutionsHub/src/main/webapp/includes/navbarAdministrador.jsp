<!-- Navbar Administrador -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-danger mb-3">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/usuario/home.jsp"><i class="bi bi-person-gear"></i> Administrador</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navAdmin">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navAdmin">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/administrador/usuarios.jsp"><i class="bi bi-person-lines-fill"></i> Usuarios</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/administrador/computadoras.jsp"><i class="bi bi-pc"></i> Computadoras</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/administrador/reportes.jsp"><i class="bi bi-bar-chart"></i> Reportes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/administrador/carga-datos.jsp"><i class="bi bi-cloud-upload"></i> Carga de Datos</a></li>
                </ul>
            </div>
        </div>
    </nav>