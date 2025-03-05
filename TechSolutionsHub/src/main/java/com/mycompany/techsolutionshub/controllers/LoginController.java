/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.UsuarioDAO;
import com.mycompany.techsolutionshub.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 *
 * @author mynordma
 */
public class LoginController {

    public void loguearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Leer parametros
        
        String nombre = request.getParameter("nombre");
        String clave = request.getParameter("clave");
        Usuario usuario = new Usuario(nombre, clave);
        
        // Loguear usuario
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean logueado = usuarioDAO.loguearUsuario(usuario);
        
        // Crear sesion
        // Devolver vista
        
        if(logueado){
            request.getRequestDispatcher("/usuario/home.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/usuario/login.jsp").forward(request, response);
        }
    }
    
}
