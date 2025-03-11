/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.techsolutionshub.resources;

import com.mycompany.techsolutionshub.controllers.ComponenteController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mynordma
 */
public class ComponenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ComponenteController componente = new ComponenteController();
        componente.obtenerComponentes(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method");
        
        ComponenteController componente = new ComponenteController();

        if (action == null) {
            request.setAttribute("mensajeAtributo", "Error");
            componente.obtenerComponentes(request, response);
        }else{
            switch (action) {
                case "create":
                    componente.crearComponente(request, response);
                    break;
                case "edit":
                    componente.editarComponente(request, response);
                    break;
                case "delete":
                    componente.eliminarComponente(request, response);
                    break;
                default:
                    request.setAttribute("mensajeAtributo", "Error");
                    componente.obtenerComponentes(request, response);
            }
        }
    }
}
