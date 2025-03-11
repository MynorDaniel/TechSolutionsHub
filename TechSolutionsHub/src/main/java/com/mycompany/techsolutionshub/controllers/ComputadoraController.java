/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.ComputadoraDAO;
import com.mycompany.techsolutionshub.models.Computadora;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ComputadoraController {

    public void obtenerComputadoras(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener computadoras
        ComputadoraDAO compDAO = new ComputadoraDAO();
        ArrayList<Computadora> computadoras = compDAO.obtenerComputadoras();

        // Mostrar vista
        request.setAttribute("computadorasAtributo", computadoras);
        request.getRequestDispatcher("/ensamblador/computadoras.jsp").forward(request, response);
    }

    public void crearComputadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        String usuario = (String) sesion.getAttribute("nombreAtributoSesion");
        String molde = request.getParameter("molde");
        String estado = "En venta";
        String precioVenta = request.getParameter("precioVenta");
        LocalDate fechaEnsamble = LocalDate.now();
        
        Computadora computadora = new Computadora(Double.parseDouble(precioVenta), molde, estado, fechaEnsamble, usuario);
        
        
        ComputadoraDAO compDAO = new ComputadoraDAO();
        boolean creada = compDAO.crearComputadora(computadora);
        
        if(creada){
            request.setAttribute("mensajeAlerta", "Computadora registrada");
        }else{
            request.setAttribute("mensajeAlerta", "Error al registrar la computadora");
        }
        
        obtenerComputadoras(request, response);
    }

    public void eliminarComputadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        ComputadoraDAO compDAO = new ComputadoraDAO();
        boolean eliminada = compDAO.eliminarComputadora(id);
        
        if(eliminada){
            request.setAttribute("mensajeAlerta", "Computadora eliminada");
        }else{
            request.setAttribute("mensajeAlerta", "Error al eliminar la computadora");
        }
        
        obtenerComputadoras(request, response);
    }
    
}
