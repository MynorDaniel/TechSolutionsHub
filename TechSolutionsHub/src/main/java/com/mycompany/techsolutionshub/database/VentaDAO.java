/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Cliente;
import com.mycompany.techsolutionshub.models.Computadora;
import com.mycompany.techsolutionshub.models.Factura;
import com.mycompany.techsolutionshub.models.Venta;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mynordma
 */
public class VentaDAO {

    public ArrayList<Venta> obtenerVentasDelDia() {
        String sql = "SELECT c.id, c.cliente, c.precio_venta, c.molde, c.fecha_venta, cl.nit, cl.nombre " +
                     "FROM computadora c " +
                     "JOIN cliente cl ON c.cliente = cl.nit " +
                     "WHERE c.estado = 'Vendida' AND c.fecha_venta = CURDATE()";

        ArrayList<Venta> ventas = new ArrayList<>();
        HashMap<String, ArrayList<Computadora>> ventasPorCliente = new HashMap<>();
        HashMap<String, Cliente> clientesMap = new HashMap<>();

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String clienteNit = rs.getString("nit");
                String clienteNombre = rs.getString("nombre");
                double precioVenta = rs.getDouble("precio_venta");
                String molde = rs.getString("molde");
                LocalDate fechaVenta = rs.getDate("fecha_venta").toLocalDate();

                Computadora computadora = new Computadora(id, clienteNit, precioVenta, molde, "Vendida", null, fechaVenta, null);

                if (!ventasPorCliente.containsKey(clienteNit)) {
                    ventasPorCliente.put(clienteNit, new ArrayList<>());
                    clientesMap.put(clienteNit, new Cliente(clienteNit, clienteNombre));
                }

                ventasPorCliente.get(clienteNit).add(computadora);
            }

            for (String nit : ventasPorCliente.keySet()) {
                ventas.add(new Venta(ventasPorCliente.get(nit), clientesMap.get(nit)));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ventas del día: " + e.getMessage());
        }

        return ventas;
    }

    public Factura crearVenta(String nitCliente, String[] computadorasSeleccionadas, String vendedor) {
        if (computadorasSeleccionadas == null || computadorasSeleccionadas.length == 0) {
            return null;
        }

        String sqlComputadora = "UPDATE computadora SET cliente = ?, estado = 'Vendida', fecha_venta = NOW(), vendedor = ? WHERE id = ?";
        String sqlFactura = "INSERT INTO factura (cliente, computadoras) VALUES (?, ?)";
        String sqlObtenerComputadoras = "SELECT id, molde, precio_venta FROM computadora WHERE id = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmtComputadora = conn.prepareStatement(sqlComputadora);
             PreparedStatement stmtFactura = conn.prepareStatement(sqlFactura);
             PreparedStatement stmtObtenerComputadoras = conn.prepareStatement(sqlObtenerComputadoras)) {

            // Actualizar computadoras vendidas
            for (String idComputadora : computadorasSeleccionadas) {
                stmtComputadora.setString(1, nitCliente);
                stmtComputadora.setString(2, vendedor);
                stmtComputadora.setInt(3, Integer.parseInt(idComputadora));
                stmtComputadora.addBatch();
            }

            int[] filasAfectadas = stmtComputadora.executeBatch();
            for (int filas : filasAfectadas) {
                if (filas == 0) {
                    return null;
                }
            }

            // Insertar en la tabla factura
            String computadorasText = String.join(",", computadorasSeleccionadas);
            stmtFactura.setString(1, nitCliente);
            stmtFactura.setString(2, computadorasText);
            stmtFactura.executeUpdate();

            // Obtener las computadoras vendidas
            ArrayList<Computadora> listaComputadoras = new ArrayList<>();
            for (String idComputadora : computadorasSeleccionadas) {
                stmtObtenerComputadoras.setInt(1, Integer.parseInt(idComputadora));
                try (ResultSet rs = stmtObtenerComputadoras.executeQuery()) {
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String molde = rs.getString("molde");
                        double precio = rs.getDouble("precio_venta");

                        listaComputadoras.add(new Computadora(id, molde, precio));
                    }
                }
            }

            return new Factura(nitCliente, listaComputadoras);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public ArrayList<Venta> obtenerVentas() {
        String sql = "SELECT c.id, c.cliente, c.precio_venta, c.molde, c.fecha_venta, cl.nit, cl.nombre " +
                     "FROM computadora c " +
                     "JOIN cliente cl ON c.cliente = cl.nit " +
                     "WHERE c.estado = 'Vendida'";

        ArrayList<Venta> ventas = new ArrayList<>();
        HashMap<String, ArrayList<Computadora>> ventasPorCliente = new HashMap<>();
        HashMap<String, Cliente> clientesMap = new HashMap<>();

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String clienteNit = rs.getString("nit");
                String clienteNombre = rs.getString("nombre");
                double precioVenta = rs.getDouble("precio_venta");
                String molde = rs.getString("molde");
                LocalDate fechaVenta = rs.getDate("fecha_venta").toLocalDate();

                Computadora computadora = new Computadora(id, clienteNit, precioVenta, molde, "Vendida", null, fechaVenta, null);

                if (!ventasPorCliente.containsKey(clienteNit)) {
                    ventasPorCliente.put(clienteNit, new ArrayList<>());
                    clientesMap.put(clienteNit, new Cliente(clienteNit, clienteNombre));
                }

                ventasPorCliente.get(clienteNit).add(computadora);
            }

            for (String nit : ventasPorCliente.keySet()) {
                ventas.add(new Venta(ventasPorCliente.get(nit), clientesMap.get(nit)));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ventas: " + e.getMessage());
        }

        return ventas;
    }



    
}
