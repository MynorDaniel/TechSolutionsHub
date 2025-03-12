/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.models;

import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Venta {
    private ArrayList<Computadora> productos;
    private Cliente cliente;

    public Venta(ArrayList<Computadora> productos, Cliente cliente) {
        this.productos = productos;
        this.cliente = cliente;
    }

    public ArrayList<Computadora> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Computadora> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
