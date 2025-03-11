/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.models;

/**
 *
 * @author mynordma
 */
public class Componente {
    
    private int id;
    private double costo;
    private String nombre;

    public Componente(int id, double costo, String nombre) {
        this.id = id;
        this.costo = costo;
        this.nombre = nombre;
    }

    public Componente(double costo, String nombre) {
        this.costo = costo;
        this.nombre = nombre;
    }

    public Componente(String nombre) {
        this.nombre = nombre;
    }

    public Componente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
