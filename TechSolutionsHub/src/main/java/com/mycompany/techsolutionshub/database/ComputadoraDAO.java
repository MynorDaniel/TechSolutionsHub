/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Computadora;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ComputadoraDAO {

    public ArrayList<Computadora> obtenerComputadoras() {
        ArrayList<Computadora> listaComputadoras = new ArrayList<>();
        String sql = "SELECT * FROM computadora";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String usuario = rs.getString("usuario");
                String cliente = rs.getString("cliente");
                String molde = rs.getString("molde");
                String estado = rs.getString("estado");
                double precioVenta = rs.getDouble("precio_venta");
                LocalDate fechaEnsamble = rs.getDate("fecha_ensamble").toLocalDate();
                LocalDate fechaVenta = (rs.getDate("fecha_venta") != null) ? rs.getDate("fecha_venta").toLocalDate() : null;

                usuario = (usuario != null) ? usuario : "---";
                cliente = (cliente != null) ? cliente : "---";
                molde = (molde != null) ? molde : "---";
                estado = (estado != null) ? estado : "---";
                precioVenta = (rs.getObject("precio_venta") != null) ? precioVenta : 0.0;

                listaComputadoras.add(new Computadora(id, cliente, precioVenta, molde, estado, fechaEnsamble, fechaVenta, usuario));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaComputadoras;
    }

    public boolean crearComputadora(Computadora computadora) {
        String sql = "INSERT INTO computadora (usuario, molde, estado, precio_venta, fecha_ensamble) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getInstance().getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los parámetros en el PreparedStatement
            stmt.setString(1, computadora.getUsuario());
            stmt.setString(2, computadora.getMolde());
            stmt.setString(3, computadora.getEstado());
            stmt.setDouble(4, computadora.getPrecioVenta());
            stmt.setDate(5, java.sql.Date.valueOf(computadora.getFechaEnsamble()));

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarComputadora(int id) {
        String sql = "DELETE FROM computadora WHERE id = ?";
        
        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            return false;
        }
    }
}
