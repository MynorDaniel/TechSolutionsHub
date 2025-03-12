/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.VentaDAO;
import com.mycompany.techsolutionshub.models.Venta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class VentaController {

    public void obtenerVentas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener ventas
        VentaDAO ventaDAO = new VentaDAO();
        ArrayList<Venta> ventas = ventaDAO.obtenerVentas();

        // Mostrar vista
        request.setAttribute("ventasAtributo", ventas);
        request.getRequestDispatcher("/vendedor/ventas.jsp").forward(request, response);
    }
    
}
