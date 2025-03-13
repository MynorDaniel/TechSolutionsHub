/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.techsolutionshub.resources;

import com.mycompany.techsolutionshub.controllers.ComputadoraController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mynordma
 */
public class ComputadoraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        ComputadoraController comp = new ComputadoraController();
        comp.obtenerComputadoras(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method");
        
        ComputadoraController comp = new ComputadoraController();

        if (action == null) {
            request.setAttribute("mensajeAtributo", "Error");
            comp.obtenerComputadoras(request, response);
        }else{
            switch (action) {
                case "create":
                    comp.crearComputadora(request, response);
                    break;
                case "delete":
                    comp.eliminarComputadora(request, response);
                    break;
                case "devolver":
                    comp.devolverComputadora(request, response);
                    break;
                default:
                    request.setAttribute("mensajeAtributo", "Error");
                    comp.obtenerComputadoras(request, response);
            }
        }
    }
}
