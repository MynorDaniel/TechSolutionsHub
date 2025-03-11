/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.techsolutionshub.resources;

import com.mycompany.techsolutionshub.controllers.MoldeController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mynordma
 */
public class MoldeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MoldeController molde = new MoldeController();
        molde.obtenerMoldes(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method");
        
        MoldeController molde = new MoldeController();

        if (action == null) {
            request.setAttribute("mensajeAtributo", "Error");
            molde.obtenerMoldes(request, response);
        }else{
            switch (action) {
                case "create":
                    molde.crearMolde(request, response);
                    break;
                case "edit":
                    molde.editarMolde(request, response);
                    break;
                case "delete":
                    molde.eliminarMolde(request, response);
                    break;
                default:
                    request.setAttribute("mensajeAtributo", "Error");
                    molde.obtenerMoldes(request, response);
            }
        }
    }
}
