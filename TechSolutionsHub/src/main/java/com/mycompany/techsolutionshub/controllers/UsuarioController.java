/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.UsuarioDAO;
import com.mycompany.techsolutionshub.models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class UsuarioController {

    public void crearUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Leer parametros
        String nombre = request.getParameter("nombre");
        String clave = request.getParameter("clave");
        String rol = request.getParameter("rol");
        Usuario usuario = new Usuario(nombre, clave, "Activo", rol);
        
        // Peticion a la base de datos
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean usuarioCreado = usuarioDAO.crearUsuario(usuario);
        
        // Vista
        
        if(usuarioCreado){
            request.setAttribute("mensajeAlerta", "Usuario creado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al crear usuario");
        }
        
        request.getRequestDispatcher("/administrador/crearUsuario.jsp").forward(request, response);
    }

    public void verUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar rol
        HttpSession sesion = request.getSession(false);
        String rolUsuario = (String) sesion.getAttribute("rolAtributoSesion");
        
        // Obtener los usuarios
        if(rolUsuario.equals(("Administrador"))){
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            ArrayList<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
            request.setAttribute("usuariosAtributo", usuarios);
            
            // Devolver la vista
            request.getRequestDispatcher("/administrador/verUsuarios.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/usuario/error.jsp").forward(request, response);
        }
        
        
    }

    public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Verificar rol
        HttpSession sesion = request.getSession(false);
        String rolUsuario = (String) sesion.getAttribute("rolAtributoSesion");
        
        // Eliminar usuario
        if(rolUsuario.equals(("Administrador"))){
            String nombre = request.getParameter("nombre");
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean eliminado = usuarioDAO.eliminarUsuario(nombre);
            
            if(!eliminado){
                request.setAttribute("mensajeAlerta", "Error al eliminar el usuario");
            }else{
                request.setAttribute("mensajeAlerta", "Usuario eliminado");
            }
        }
        
        // Mandar a verUsuarios.jsp
        verUsuarios(request, response);
    }

    public void editarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Verificar rol
        HttpSession sesion = request.getSession(false);
        String rolUsuario = (String) sesion.getAttribute("rolAtributoSesion");
        
        // Editar usuario
        if(rolUsuario.equals(("Administrador"))){
            // Recuparar parametros
            String nombre = request.getParameter("nombre");
            String clave = request.getParameter("clave");
            String rol = request.getParameter("rol");
            
            Usuario usuario = new Usuario(nombre, clave, "", rol);
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.editarUsuario(usuario);
        }
        
        // Devolver a verUsuarios.jsp
        verUsuarios(request, response);
    }

}
