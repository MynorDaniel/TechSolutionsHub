<!-- Navbar Ensamblador -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mb-3">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/usuario/home.jsp"><i class="bi bi-tools"></i> Ensamble</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navEnsamblador">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navEnsamblador">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/ComponenteServlet"><i class="bi bi-box-seam"></i> Componentes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/MoldeServlet"><i class="bi bi-cpu"></i> Moldes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/ComputadoraServlet"><i class="bi bi-pc"></i> Computadoras</a></li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/LoginServlet">
                            <i class="bi bi-box-arrow-right"></i> Salir
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
