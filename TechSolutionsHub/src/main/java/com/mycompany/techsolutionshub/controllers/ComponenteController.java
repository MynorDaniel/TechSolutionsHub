/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.ComponenteDAO;
import com.mycompany.techsolutionshub.models.Componente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ComponenteController {

    public void obtenerComponentes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("_method");
        
        // Verificar el rol
            // ...pendiente
            
        // Obtener componentes
        ComponenteDAO componenteDAO = new ComponenteDAO();
        ArrayList<Componente> componentes = componenteDAO.obtenerComponentes();

        // Mostrar vista
        request.setAttribute("componentesAtributo", componentes);
        
        if(action == null || action.equals("")){
           request.getRequestDispatcher("/ensamblador/componentes.jsp").forward(request, response); 
        }else if(action.equals("crearMolde")){
            request.getRequestDispatcher("/ensamblador/crearMolde.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/ensamblador/componentes.jsp").forward(request, response);
        }
    }

    public void crearComponente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parametros
        String nombre = request.getParameter("nombre");
        String costoStr = request.getParameter("costo");
        
        Componente componente = new Componente(Double.parseDouble(costoStr), nombre);
        
        // Crear el componente
        ComponenteDAO componenteDAO = new ComponenteDAO();
        boolean creado = componenteDAO.crearComponente(componente);
        
        // Devolver a componentes.jsp
        if(creado){
            request.setAttribute("mensajeAlerta", "Componente creado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al crear el componente");
        }
        
        obtenerComponentes(request, response);
    }

    public void editarComponente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parametros
        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String costoStr = request.getParameter("costo");
        
        if(costoStr == null || costoStr.equals("")){
            costoStr = "-1.00";
        }
        
        // Editar componente
        Componente componente = new Componente(Integer.parseInt(idStr), Double.parseDouble(costoStr), nombre);
        ComponenteDAO componenteDAO = new ComponenteDAO();
        boolean editado = componenteDAO.editarComponente(componente);
        
        // Devolver a componentes.jsp
        if(editado){
            request.setAttribute("mensajeAlerta", "Componente editado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al editar el componente");
        }
        
        obtenerComponentes(request, response);
    }

    public void eliminarComponente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener id
        String idStr = request.getParameter("id");
        
        // Eliminar componente
        ComponenteDAO componenteDAO = new ComponenteDAO();
        boolean eliminado = componenteDAO.eliminarComponente(Integer.parseInt(idStr));
        
        // Devolver a componentes.jsp
        if(eliminado){
            request.setAttribute("mensajeAlerta", "Componente eliminado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al eliminar el componente, verifica que no este siendo usado en un molde");
        }
        
        obtenerComponentes(request, response);
    }
    
}
