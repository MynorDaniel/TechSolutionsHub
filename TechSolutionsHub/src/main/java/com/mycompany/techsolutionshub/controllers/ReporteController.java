/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.mycompany.techsolutionshub.database.ReporteDAO;
import com.mycompany.techsolutionshub.models.ReporteAdmin;
import com.mycompany.techsolutionshub.models.ReporteVenta;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author mynordma
 */
public class ReporteController {

    public void obtenerReporteVentas(HttpServletRequest request, HttpServletResponse response) {
        // Obtener reporte
        ReporteDAO reporteDAO = new ReporteDAO();
        ReporteVenta reporte = reporteDAO.obtenerReportesVentas();

        // Definir el nombre del archivo
        String fileName = "reporte_ventas.csv";

        // Configurar la respuesta HTTP para la descarga
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try (PrintWriter writer = response.getWriter()) {
            // Escribir encabezados del CSV
            writer.println("Compras,Devoluciones,Facturas");

            // Escribir los datos en el CSV
            writer.printf("\"%s\",\"%s\",\"%s\"%n",
                          reporte.getCompras(),
                          reporte.getDevoluciones(),
                          reporte.getFacturas());

            writer.flush();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }


    public void obtenerReporteAdmin(HttpServletRequest request, HttpServletResponse response) {
        // Obtener reporte
        ReporteDAO reporteDAO = new ReporteDAO();
        ReporteAdmin reporte = reporteDAO.obtenerReportesAdmin();

        // Definir el nombre del archivo
        String fileName = "reporte_admin.csv";

        // Configurar la respuesta HTTP para la descarga
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try (PrintWriter writer = response.getWriter()) {
            // Escribir encabezados del CSV
            writer.println("Ventas,Devoluciones,Ganancias,VendedorMasVentas,VendedorMasGanancias,ComputadoraMasVendida,ComputadoraMenosVendida");

            // Escribir los datos en el CSV
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                          reporte.getVentas(),
                          reporte.getDevoluciones(),
                          reporte.getGanancias(),
                          reporte.getVendedorMasVentas(),
                          reporte.getVendedorMasGanancias(),
                          reporte.getComputadoraMasVendida(),
                          reporte.getComputadoraMenosVendida());

            writer.flush();
        } catch (IOException e) {
            System.out.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }

    
}
