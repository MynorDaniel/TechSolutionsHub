/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.Encriptacion;
import com.mycompany.techsolutionshub.models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class UsuarioDAO {

    public boolean loguearUsuario(Usuario usuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE nombre = ? AND clave = ?";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Encriptacion enc = new Encriptacion();

            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, enc.encriptar(usuario.getClave()));

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al loguear usuario: " + e.getMessage());
            return false;
        }
        return false;
    }


    public String obtenerRol(String nombre) {
        String sql = "SELECT rol FROM usuario WHERE nombre = ?";
        String rol = "";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, nombre);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    rol = rs.getString("rol");
                }
            }

        } catch (SQLException e) {
        }

        return rol;
    }


    public boolean crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, clave, estado, rol) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            Encriptacion enc = new Encriptacion();
            
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, enc.encriptar(usuario.getClave()));
            preparedStatement.setString(3, usuario.getEstado());
            preparedStatement.setString(4, usuario.getRol());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setClave(""); 
                usuario.setEstado(rs.getString("estado"));
                usuario.setRol(rs.getString("rol"));
                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            return null;
        }

        return listaUsuarios;
    }

    public boolean eliminarUsuario(String nombre) {
        String sql = "DELETE FROM usuario WHERE nombre = ? AND protegido = FALSE";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public void editarUsuario(Usuario usuario) {
        StringBuilder sql = new StringBuilder("UPDATE usuario SET ");

        boolean camposActualizados = false;

        if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
            sql.append("clave = ?, ");
            camposActualizados = true;
        }

        if (usuario.getRol() != null && !usuario.getRol().isEmpty()) {
            sql.append("rol = ?, ");
            camposActualizados = true;
        }

        if (!camposActualizados) {
            return;
        }

        sql.delete(sql.length() - 2, sql.length());
        
        sql.append(" WHERE nombre = ?");

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            int index = 1;
            
            Encriptacion enc = new Encriptacion();

            if (usuario.getClave() != null && !usuario.getClave().isEmpty()) {
                preparedStatement.setString(index++, enc.encriptar(usuario.getClave()));
            }

            if (usuario.getRol() != null && !usuario.getRol().isEmpty()) {
                preparedStatement.setString(index++, usuario.getRol());
            }

            preparedStatement.setString(index, usuario.getNombre());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
