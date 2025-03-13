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
public class Factura {
    private int id;
    private String nitCliente;
    private ArrayList<Computadora> computadoras;

    public Factura(int id, String nitCliente, ArrayList<Computadora> computadoras) {
        this.id = id;
        this.nitCliente = nitCliente;
        this.computadoras = computadoras;
    }

    public Factura(String nitCliente, ArrayList<Computadora> computadoras) {
        this.nitCliente = nitCliente;
        this.computadoras = computadoras;
    }

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public ArrayList<Computadora> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(ArrayList<Computadora> computadoras) {
        this.computadoras = computadoras;
    }

    
    
    
}
