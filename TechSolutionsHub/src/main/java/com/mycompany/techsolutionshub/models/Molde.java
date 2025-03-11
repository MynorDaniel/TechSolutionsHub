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
public class Molde {
    private String nombre;
    private ArrayList<Componente> componentes;

    public Molde(String nombre, ArrayList<Componente> componentes) {
        this.nombre = nombre;
        this.componentes = componentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Componente> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<Componente> componentes) {
        this.componentes = componentes;
    }
    
    
}
