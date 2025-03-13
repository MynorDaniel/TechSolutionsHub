/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.models;

/**
 *
 * @author mynordma
 */
public class ReporteAdmin {
    private String ventas;
    private String devoluciones;
    private String ganancias;
    private String vendedorMasVentas;
    private String vendedorMasGanancias;
    private String computadoraMasVendida;
    private String computadoraMenosVendida;

    public ReporteAdmin(String ventas, String devoluciones, String ganancias, String vendedorMasVentas, String vendedorMasGanancias, String computadoraMasVendida, String computadoraMenosVendida) {
        this.ventas = ventas;
        this.devoluciones = devoluciones;
        this.ganancias = ganancias;
        this.vendedorMasVentas = vendedorMasVentas;
        this.vendedorMasGanancias = vendedorMasGanancias;
        this.computadoraMasVendida = computadoraMasVendida;
        this.computadoraMenosVendida = computadoraMenosVendida;
    }
    
    

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    public String getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(String devoluciones) {
        this.devoluciones = devoluciones;
    }

    public String getGanancias() {
        return ganancias;
    }

    public void setGanancias(String ganancias) {
        this.ganancias = ganancias;
    }

    public String getVendedorMasVentas() {
        return vendedorMasVentas;
    }

    public void setVendedorMasVentas(String vendedorMasVentas) {
        this.vendedorMasVentas = vendedorMasVentas;
    }

    public String getVendedorMasGanancias() {
        return vendedorMasGanancias;
    }

    public void setVendedorMasGanancias(String vendedorMasGanancias) {
        this.vendedorMasGanancias = vendedorMasGanancias;
    }

    public String getComputadoraMasVendida() {
        return computadoraMasVendida;
    }

    public void setComputadoraMasVendida(String computadoraMasVendida) {
        this.computadoraMasVendida = computadoraMasVendida;
    }

    public String getComputadoraMenosVendida() {
        return computadoraMenosVendida;
    }

    public void setComputadoraMenosVendida(String computadoraMenosVendida) {
        this.computadoraMenosVendida = computadoraMenosVendida;
    }
    
    
}
