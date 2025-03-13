/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.models;

/**
 *
 * @author mynordma
 */
public class ReporteVenta {
    String compras;
    String devoluciones;
    String facturas;

    public ReporteVenta(String compras, String devoluciones, String facturas) {
        this.compras = compras;
        this.devoluciones = devoluciones;
        this.facturas = facturas;
    }

    public String getCompras() {
        return compras;
    }

    public void setCompras(String compras) {
        this.compras = compras;
    }

    public String getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(String devoluciones) {
        this.devoluciones = devoluciones;
    }

    public String getFacturas() {
        return facturas;
    }

    public void setFacturas(String facturas) {
        this.facturas = facturas;
    }
    
    
}
