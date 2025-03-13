/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.techsolutionshub.resources;

import com.mycompany.techsolutionshub.controllers.VentaController;
import com.mycompany.techsolutionshub.database.ClienteDAO;
import com.mycompany.techsolutionshub.database.ComputadoraDAO;
import com.mycompany.techsolutionshub.models.Cliente;
import com.mycompany.techsolutionshub.models.Computadora;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class VentaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method"); // crearVenta
        
        if (action == null || action.equals("")) {
            VentaController venta = new VentaController();
            venta.obtenerVentasDelDia(request, response);
        }else if(action.equals("crearVenta")){
            // Obtener clientes
            ClienteDAO clienteDAO = new ClienteDAO();
            ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();
            
            // Obtener computadoras
            ComputadoraDAO compDAO = new ComputadoraDAO();
            ArrayList<Computadora> computadoras = compDAO.obtenerComputadoras();
            
            // Devolver vista
            request.setAttribute("clientesAtributo", clientes);
            request.setAttribute("computadorasAtributo", computadoras);
            
            request.getRequestDispatcher("/vendedor/crearVenta.jsp").forward(request, response);
            
        }else{
            VentaController venta = new VentaController();
            venta.obtenerVentasDelDia(request, response);
        }
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener la acción que se quiere realizar
        String action = request.getParameter("_method"); // crearVenta
        
        VentaController venta = new VentaController();

        if (action == null) {
            request.setAttribute("mensajeAtributo", "Error");
            venta.obtenerVentasDelDia(request, response);
        }else{
            switch (action) {
                case "crearVenta":
                    venta.crearVenta(request, response);
                    break;
                default:
                    request.setAttribute("mensajeAtributo", "Error");
                    venta.obtenerVentasDelDia(request, response);
            }
        }
    }
    
}
