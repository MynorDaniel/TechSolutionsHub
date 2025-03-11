/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Cliente;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class ClienteDAO {

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nit = rs.getString("nit");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                listaClientes.add(new Cliente(nit, nombre, direccion));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listaClientes;
    }

    public boolean crearCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (nit, nombre, direccion) VALUES (?, ?, ?)";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNit());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDireccion());

            int filasInsertadas = stmt.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(String nit) {
        String sql = "DELETE FROM cliente WHERE nit = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nit);

            int filasEliminadas = stmt.executeUpdate();
            return filasEliminadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean editarCliente(Cliente cliente) {
        StringBuilder sql = new StringBuilder("UPDATE cliente SET ");

        boolean camposActualizados = false;

        if (cliente.getNombre() != null && !cliente.getNombre().isEmpty()) {
            sql.append("nombre = ?, ");
            camposActualizados = true;
        }

        if (cliente.getDireccion() != null && !cliente.getDireccion().isEmpty()) {
            sql.append("direccion = ?, ");
            camposActualizados = true;
        }

        if (!camposActualizados) {
            System.out.println("No hay campos para actualizar.");
            return false;
        }

        sql.delete(sql.length() - 2, sql.length());

        // Añadimos la condición WHERE
        sql.append(" WHERE nit = ?");

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            int index = 1;

            if (cliente.getNombre() != null && !cliente.getNombre().isEmpty()) {
                preparedStatement.setString(index++, cliente.getNombre());
            }

            if (cliente.getDireccion() != null && !cliente.getDireccion().isEmpty()) {
                preparedStatement.setString(index++, cliente.getDireccion());
            }

            preparedStatement.setString(index, cliente.getNit());

            int filasAfectadas = preparedStatement.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

}
