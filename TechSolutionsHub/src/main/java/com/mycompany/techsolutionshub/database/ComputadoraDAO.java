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

    public boolean devolverComputadora(String computadoraId, String vendedor) {
        String sqlActualizarComputadora = "UPDATE computadora SET estado = 'Devuelta' WHERE id = ? AND vendedor = ? AND fecha_venta >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)";
        String sqlObtenerMolde = "SELECT molde FROM computadora WHERE id = ?";
        String sqlActualizarComponentes = "UPDATE componente SET estado = 'Disponible' WHERE id IN (SELECT componente FROM molde_componente WHERE molde = ?)";

        Connection conn = null;
        PreparedStatement stmtActualizarComputadora = null;
        PreparedStatement stmtObtenerMolde = null;
        PreparedStatement stmtActualizarComponentes = null;
        ResultSet rs = null;

        try {
            conn = ConexionDB.getInstance().getConnection();
            conn.setAutoCommit(false);

            // 1. Actualizar el estado de la computadora si el vendedor coincide y no ha pasado una semana
            stmtActualizarComputadora = conn.prepareStatement(sqlActualizarComputadora);
            stmtActualizarComputadora.setString(1, computadoraId);
            stmtActualizarComputadora.setString(2, vendedor);

            int rowsAffected = stmtActualizarComputadora.executeUpdate();
            if (rowsAffected == 0) {
                conn.rollback();
                return false;
            }

            // 2. Obtener el molde de la computadora
            stmtObtenerMolde = conn.prepareStatement(sqlObtenerMolde);
            stmtObtenerMolde.setString(1, computadoraId);
            rs = stmtObtenerMolde.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return false;
            }
            String molde = rs.getString("molde");

            // 3. Actualizar el estado de los componentes a "Disponible"
            stmtActualizarComponentes = conn.prepareStatement(sqlActualizarComponentes);
            stmtActualizarComponentes.setString(1, molde);
            stmtActualizarComponentes.executeUpdate();

            conn.commit(); // Confirmar cambios
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                if (conn != null) conn.rollback(); // Deshacer cambios en caso de error
            } catch (SQLException ex) {
                System.out.println(e.getMessage());
            }
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtActualizarComputadora != null) stmtActualizarComputadora.close();
                if (stmtObtenerMolde != null) stmtObtenerMolde.close();
                if (stmtActualizarComponentes != null) stmtActualizarComponentes.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // Restaurar auto-commit
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
