/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.MoldeDAO;
import com.mycompany.techsolutionshub.models.Molde;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class MoldeController {

    public void obtenerMoldes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("_method");
        
        // Obtener componentes
        MoldeDAO moldeDAO = new MoldeDAO();
        ArrayList<Molde> moldes = moldeDAO.obtenerMoldes();

        // Mostrar vista
        request.setAttribute("moldesAtributo", moldes);
        
        if(action == null || action.equals("")){
           request.getRequestDispatcher("/ensamblador/moldes.jsp").forward(request, response);
        }else if(action.equals("crearComputadora")){
            request.getRequestDispatcher("/ensamblador/crearComputadora.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/ensamblador/moldes.jsp").forward(request, response);
        }
    }

    public void crearMolde(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreMolde = request.getParameter("nombreMolde");
        String[] componentesSeleccionados = request.getParameterValues("componentesSeleccionados");
        
        MoldeDAO moldeDAO = new MoldeDAO();
        boolean creado = moldeDAO.crearMolde(nombreMolde, componentesSeleccionados);

        if(creado){
            request.setAttribute("mensajeAlerta", "Molde creado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al crear el molde");
        }
        
        obtenerMoldes(request, response);
    }

    public void editarMolde(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("No implementado.");
    }

    public void eliminarMolde(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreMolde = request.getParameter("nombre");
        
        MoldeDAO moldeDAO = new MoldeDAO();
        boolean eliminado = moldeDAO.eliminarMolde(nombreMolde);
        
        if(eliminado){
            request.setAttribute("mensajeAlerta", "Molde eliminado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al eliminar el molde");
        }
        
        obtenerMoldes(request, response);
    }
    
}
