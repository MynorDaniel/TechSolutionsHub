/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.ReporteAdmin;
import com.mycompany.techsolutionshub.models.ReporteVenta;
import java.sql.*;

/**
 *
 * @author mynordma
 */
public class ReporteDAO {

    public ReporteVenta obtenerReportesVentas() {
        String sqlCompras = "SELECT GROUP_CONCAT(molde SEPARATOR ', ') FROM computadora WHERE estado = 'Vendida'";
        String sqlDevoluciones = "SELECT GROUP_CONCAT(molde SEPARATOR ', ') FROM computadora WHERE estado = 'Devuelta'";
        String sqlFacturas = "SELECT GROUP_CONCAT(cliente SEPARATOR ', ') FROM factura";

        String compras = "", devoluciones = "", facturas = "";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement stmtCompras = connection.prepareStatement(sqlCompras);
             PreparedStatement stmtDevoluciones = connection.prepareStatement(sqlDevoluciones);
             PreparedStatement stmtFacturas = connection.prepareStatement(sqlFacturas);
             ResultSet rsCompras = stmtCompras.executeQuery();
             ResultSet rsDevoluciones = stmtDevoluciones.executeQuery();
             ResultSet rsFacturas = stmtFacturas.executeQuery()) {

            if (rsCompras.next()) {
                compras = rsCompras.getString(1) != null ? rsCompras.getString(1) : "";
            }

            if (rsDevoluciones.next()) {
                devoluciones = rsDevoluciones.getString(1) != null ? rsDevoluciones.getString(1) : "";
            }

            if (rsFacturas.next()) {
                facturas = rsFacturas.getString(1) != null ? rsFacturas.getString(1) : "";
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener reportes de ventas: " + e.getMessage());
        }

        return new ReporteVenta(compras, devoluciones, facturas);
    }


    public ReporteAdmin obtenerReportesAdmin() {
        String sqlVentas = "SELECT GROUP_CONCAT(molde SEPARATOR ', ') FROM computadora WHERE estado = 'Vendida'";
        String sqlDevoluciones = "SELECT GROUP_CONCAT(molde SEPARATOR ', ') FROM computadora WHERE estado = 'Devuelta'";
        String sqlGanancias = "SELECT SUM(precio_venta) FROM computadora WHERE estado = 'Vendida'";
        String sqlVendedorMasVentas = "SELECT vendedor, COUNT(*) FROM computadora WHERE estado = 'Vendida' GROUP BY vendedor ORDER BY COUNT(*) DESC LIMIT 1";
        String sqlVendedorMasGanancias = "SELECT vendedor, SUM(precio_venta) FROM computadora WHERE estado = 'Vendida' GROUP BY vendedor ORDER BY SUM(precio_venta) DESC LIMIT 1";
        String sqlComputadoraMasVendida = "SELECT molde, COUNT(*) FROM computadora WHERE estado = 'Vendida' GROUP BY molde ORDER BY COUNT(*) DESC LIMIT 1";
        String sqlComputadoraMenosVendida = "SELECT molde, COUNT(*) FROM computadora WHERE estado = 'Vendida' GROUP BY molde ORDER BY COUNT(*) ASC LIMIT 1";

        String ventas = "", devoluciones = "", ganancias = "", vendedorMasVentas = "", vendedorMasGanancias = "", computadoraMasVendida = "", computadoraMenosVendida = "";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement stmtVentas = connection.prepareStatement(sqlVentas);
             PreparedStatement stmtDevoluciones = connection.prepareStatement(sqlDevoluciones);
             PreparedStatement stmtGanancias = connection.prepareStatement(sqlGanancias);
             PreparedStatement stmtVendedorMasVentas = connection.prepareStatement(sqlVendedorMasVentas);
             PreparedStatement stmtVendedorMasGanancias = connection.prepareStatement(sqlVendedorMasGanancias);
             PreparedStatement stmtComputadoraMasVendida = connection.prepareStatement(sqlComputadoraMasVendida);
             PreparedStatement stmtComputadoraMenosVendida = connection.prepareStatement(sqlComputadoraMenosVendida);
             ResultSet rsVentas = stmtVentas.executeQuery();
             ResultSet rsDevoluciones = stmtDevoluciones.executeQuery();
             ResultSet rsGanancias = stmtGanancias.executeQuery();
             ResultSet rsVendedorMasVentas = stmtVendedorMasVentas.executeQuery();
             ResultSet rsVendedorMasGanancias = stmtVendedorMasGanancias.executeQuery();
             ResultSet rsComputadoraMasVendida = stmtComputadoraMasVendida.executeQuery();
             ResultSet rsComputadoraMenosVendida = stmtComputadoraMenosVendida.executeQuery()) {

            // Obteniendo datos
            if (rsVentas.next()) {
                ventas = rsVentas.getString(1) != null ? rsVentas.getString(1) : "";
            }

            if (rsDevoluciones.next()) {
                devoluciones = rsDevoluciones.getString(1) != null ? rsDevoluciones.getString(1) : "";
            }

            if (rsGanancias.next()) {
                ganancias = rsGanancias.getString(1) != null ? rsGanancias.getString(1) : "";
            }

            if (rsVendedorMasVentas.next()) {
                vendedorMasVentas = rsVendedorMasVentas.getString(1) != null ? rsVendedorMasVentas.getString(1) : "";
            }

            if (rsVendedorMasGanancias.next()) {
                vendedorMasGanancias = rsVendedorMasGanancias.getString(1) != null ? rsVendedorMasGanancias.getString(1) : "";
            }

            if (rsComputadoraMasVendida.next()) {
                computadoraMasVendida = rsComputadoraMasVendida.getString(1) != null ? rsComputadoraMasVendida.getString(1) : "";
            }

            if (rsComputadoraMenosVendida.next()) {
                computadoraMenosVendida = rsComputadoraMenosVendida.getString(1) != null ? rsComputadoraMenosVendida.getString(1) : "";
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener reportes de admin: " + e.getMessage());
        }

        return new ReporteAdmin(ventas, devoluciones, ganancias, vendedorMasVentas, vendedorMasGanancias, computadoraMasVendida, computadoraMenosVendida);
    }

    
}
