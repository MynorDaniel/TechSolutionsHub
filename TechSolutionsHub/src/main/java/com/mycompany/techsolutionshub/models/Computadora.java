/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.models;

import java.time.LocalDate;

/**
 *
 * @author mynordma
 */
public class Computadora {
    private int id;
    private String cliente;
    private double precioVenta;
    private String molde;
    private String estado;
    private LocalDate fechaEnsamble;
    private LocalDate fechaVenta;
    private String usuario;

    public Computadora(int id, String cliente, double precioVenta, String molde, String estado, LocalDate fechaEnsamble, LocalDate fechaVenta, String usuario) {
        this.id = id;
        this.cliente = cliente;
        this.precioVenta = precioVenta;
        this.molde = molde;
        this.estado = estado;
        this.fechaEnsamble = fechaEnsamble;
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
    }

    public Computadora(String cliente, double precioVenta, String molde, String estado, LocalDate fechaEnsamble, LocalDate fechaVenta, String usuario) {
        this.cliente = cliente;
        this.precioVenta = precioVenta;
        this.molde = molde;
        this.estado = estado;
        this.fechaEnsamble = fechaEnsamble;
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
    }

    public Computadora(double precioVenta, String molde, String estado, LocalDate fechaEnsamble, String usuario) {
        this.precioVenta = precioVenta;
        this.molde = molde;
        this.estado = estado;
        this.fechaEnsamble = fechaEnsamble;
        this.usuario = usuario;
    }

    public Computadora(int id, String molde, double precioVenta) {
        this.id = id;
        this.precioVenta = precioVenta;
        this.molde = molde;
    }

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getMolde() {
        return molde;
    }

    public void setMolde(String molde) {
        this.molde = molde;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEnsamble() {
        return fechaEnsamble;
    }

    public void setFechaEnsamble(LocalDate fechaEnsamble) {
        this.fechaEnsamble = fechaEnsamble;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
