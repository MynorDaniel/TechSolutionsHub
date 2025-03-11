/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.ClienteDAO;
import com.mycompany.techsolutionshub.models.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ClienteController {

    public void obtenerClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener computadoras
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();

        // Mostrar vista
        request.setAttribute("clientesAtributo", clientes);
        request.getRequestDispatcher("/vendedor/clientes.jsp").forward(request, response);
    }

    public void crearCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        Cliente cliente = new Cliente(nit, nombre, direccion);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean creado = clienteDAO.crearCliente(cliente);
        
        if(creado){
            request.setAttribute("mensajeAlerta", "Cliente registrado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al registrar el cliente");
        }
        
        obtenerClientes(request, response);
    }

    public void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean eliminado = clienteDAO.eliminarCliente(nit);
        
        if(eliminado){
            request.setAttribute("mensajeAlerta", "Cliente eliminado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al eliminar el cliente");
        }
        
        obtenerClientes(request, response);
    }

    public void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        Cliente cliente = new Cliente(nit, nombre, direccion);
        
        ClienteDAO clienteDAO = new ClienteDAO();
        boolean editado = clienteDAO.editarCliente(cliente);
        
        if(editado){
            request.setAttribute("mensajeAlerta", "Cliente editado");
        }else{
            request.setAttribute("mensajeAlerta", "Error al editar el cliente");
        }
        
        obtenerClientes(request, response);
    }
    
}
