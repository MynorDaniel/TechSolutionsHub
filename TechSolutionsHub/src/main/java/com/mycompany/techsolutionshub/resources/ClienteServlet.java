/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.techsolutionshub.resources;

import com.mycompany.techsolutionshub.controllers.ClienteController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mynordma
 */
public class ClienteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        ClienteController cliente = new ClienteController();
        cliente.obtenerClientes(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method");
        
        ClienteController cliente = new ClienteController();

        if (action == null) {
            request.setAttribute("mensajeAtributo", "Error");
            cliente.obtenerClientes(request, response);
        }else{
            switch (action) {
                case "create":
                    cliente.crearCliente(request, response);
                    break;
                case "edit":
                    cliente.editarCliente(request, response);
                    break;
                case "delete":
                    cliente.eliminarCliente(request, response);
                    break;
                default:
                    request.setAttribute("mensajeAtributo", "Error");
                    cliente.obtenerClientes(request, response);
            }
        }
    }
}
