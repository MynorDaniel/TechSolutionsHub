/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ComponenteDAO {

    public ArrayList<Componente> obtenerComponentes() {
        ArrayList<Componente> componentes = new ArrayList<>();
        String sql = "SELECT * FROM componente WHERE estado = 'Disponible'";
        
        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Componente componente = new Componente();
                componente.setId(rs.getInt("id"));
                componente.setNombre(rs.getString("nombre"));
                componente.setCosto(rs.getDouble("costo"));
                
                componentes.add(componente);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return componentes;
    }

    public boolean crearComponente(Componente componente) {
        String sql = "INSERT INTO componente (nombre, costo, estado) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, componente.getNombre());
            stmt.setDouble(2, componente.getCosto());
            stmt.setString(3, "Disponible");

            int filasAfectadas = stmt.executeUpdate();
            
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarComponente(int id) {
        String sql = "UPDATE componente SET estado = 'Usado' WHERE id = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el estado del componente: " + e.getMessage());
            return false;
        }
    }


    public boolean editarComponente(Componente componente) {
        StringBuilder sql = new StringBuilder("UPDATE componente SET ");
        boolean camposActualizados = false;

        if (componente.getNombre() != null && !componente.getNombre().isEmpty()) {
            sql.append("nombre = ?, ");
            camposActualizados = true;
        }

        if (componente.getCosto() > 0) {
            sql.append("costo = ?, ");
            camposActualizados = true;
        }

        if (!camposActualizados) {
            return false;
        }

        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE id = ?");

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (componente.getNombre() != null && !componente.getNombre().isEmpty()) {
                stmt.setString(index++, componente.getNombre());
            }

            if (componente.getCosto() > 0) {
                stmt.setDouble(index++, componente.getCosto());
            }

            stmt.setInt(index, componente.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }



    
}
