/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.techsolutionshub.database.VentaDAO;
import com.mycompany.techsolutionshub.models.Computadora;
import com.mycompany.techsolutionshub.models.Factura;
import com.mycompany.techsolutionshub.models.Venta;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class VentaController {

    public void obtenerVentasDelDia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener ventas
        VentaDAO ventaDAO = new VentaDAO();
        ArrayList<Venta> ventasDelDia = ventaDAO.obtenerVentasDelDia();
        ArrayList<Venta> ventasTotales = ventaDAO.obtenerVentas();
        ArrayList<ArrayList<Venta>> ventas = new ArrayList<>();
        ventas.add(ventasDelDia);
        ventas.add(ventasTotales);

        // Mostrar vista
        request.setAttribute("ventasAtributo", ventas);
        request.getRequestDispatcher("/vendedor/ventas.jsp").forward(request, response);
    }

    public void crearVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nitCliente = request.getParameter("cliente");
        String[] computadorasSeleccionadas = request.getParameterValues("computadoras");
        
        VentaDAO ventaDAO = new VentaDAO();
        Factura factura = ventaDAO.crearVenta(nitCliente, computadorasSeleccionadas);
        
        if(factura == null){
            request.setAttribute("mensajeAlerta", "Error al registrar la venta");
            obtenerVentasDelDia(request, response);
        }else{
            request.setAttribute("mensajeAlerta", "Venta registrada");
        }
        
        try ( // GENERAR PDF
                ByteArrayOutputStream pdfStream = generarFacturaPDF(factura)) {
            // Enviar el PDF como descarga
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=factura.pdf");
            response.setContentLength(pdfStream.size());
            OutputStream outStream = response.getOutputStream();
            pdfStream.writeTo(outStream);
            outStream.flush();
        }

    }
    
    private ByteArrayOutputStream generarFacturaPDF(Factura factura) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Estilos
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            // Título
            Paragraph title = new Paragraph("Factura de Venta", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Información del Cliente
            document.add(new Paragraph("NIT Cliente: " + factura.getNitCliente(), textFont));
            document.add(new Paragraph("\n"));

            // Tabla de computadoras vendidas
            document.add(new Paragraph("Computadoras:", textFont));
            PdfPTable table = new PdfPTable(3); // 3 columnas: ID, Molde, Precio
            table.addCell("ID");
            table.addCell("Molde");
            table.addCell("Precio");

            double total = 0.0;

            for (Computadora computadora : factura.getComputadoras()) {
                table.addCell(String.valueOf(computadora.getId()));  // ID
                table.addCell(computadora.getMolde());  // Molde
                table.addCell("$" + computadora.getPrecioVenta());  // Precio
                total += computadora.getPrecioVenta();
            }
            document.add(table);

            // Total
            document.add(new Paragraph("\nTotal: $" + total, textFont));

            document.close();
        } catch (DocumentException e) {
            
        }

        return outputStream;
    }

    
}
