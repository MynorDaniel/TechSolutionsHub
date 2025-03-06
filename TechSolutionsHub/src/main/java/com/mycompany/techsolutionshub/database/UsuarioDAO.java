/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class UsuarioDAO {

    public boolean loguearUsuario(Usuario usuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nombre = ? AND clave = ?";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getClave());
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public String obtenerRol(String nombre) {
        String sql = "SELECT rol FROM usuario WHERE nombre = ?";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }

        } catch (SQLException e) {
            return "";
        }
        return "";
    }

}
