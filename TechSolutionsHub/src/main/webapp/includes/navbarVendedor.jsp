<!-- Navbar Vendedor -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-success mb-3">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/usuario/home.jsp"><i class="bi bi-cart"></i> Ventas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navVendedor">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navVendedor">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/ClienteServlet"><i class="bi bi-people"></i> Clientes</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/VentaServlet"><i class="bi bi-cash"></i> Ventas</a></li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/ReporteServlet?&_method=ventas">
                            <i class="bi bi-file-earmark-bar-graph"></i> Reportes
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/LoginServlet">
                            <i class="bi bi-box-arrow-right"></i> Salir
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
