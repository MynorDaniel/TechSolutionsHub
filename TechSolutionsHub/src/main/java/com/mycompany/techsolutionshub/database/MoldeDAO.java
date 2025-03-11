/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub.database;

import com.mycompany.techsolutionshub.models.Componente;
import com.mycompany.techsolutionshub.models.Molde;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class MoldeDAO {

    public ArrayList<Molde> obtenerMoldes() {
        ArrayList<Molde> moldes = new ArrayList<>();
        String sqlMoldes = "SELECT nombre FROM molde";
        String sqlComponentes = "SELECT c.nombre, c.costo FROM componente c " +
                                "JOIN molde_componente mc ON c.id = mc.componente " +
                                "WHERE mc.molde = ?";

        try (Connection connection = ConexionDB.getInstance().getConnection();
             PreparedStatement psMoldes = connection.prepareStatement(sqlMoldes);
             ResultSet rsMoldes = psMoldes.executeQuery()) {

            while (rsMoldes.next()) {
                String nombreMolde = rsMoldes.getString("nombre");

                ArrayList<Componente> componentes = new ArrayList<>();

                try (PreparedStatement psComponentes = connection.prepareStatement(sqlComponentes)) {
                    psComponentes.setString(1, nombreMolde);
                    try (ResultSet rsComponentes = psComponentes.executeQuery()) {
                        while (rsComponentes.next()) {
                            componentes.add(new Componente(rsComponentes.getDouble("costo"), rsComponentes.getString("nombre")));
                        }
                    }
                }

                moldes.add(new Molde(nombreMolde, componentes));
            }
        } catch (SQLException e) {
            return null;
        }

        return moldes;
    }

    public boolean crearMolde(String nombreMolde, String[] componentesSeleccionados) {
        String sqlMolde = "INSERT INTO molde (nombre) VALUES (?)";
        String sqlRelacion = "INSERT INTO molde_componente (molde, componente) VALUES (?, ?)";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmtMolde = conn.prepareStatement(sqlMolde);
             PreparedStatement stmtRelacion = conn.prepareStatement(sqlRelacion)) {

            // Insertar el molde en la tabla `molde`
            stmtMolde.setString(1, nombreMolde);
            int filasMolde = stmtMolde.executeUpdate();

            // Insertar la relación en `molde_componente` solo si hay componentes seleccionados
            if (filasMolde > 0 && componentesSeleccionados != null) {
                for (String componenteId : componentesSeleccionados) {
                    stmtRelacion.setString(1, nombreMolde);
                    stmtRelacion.setInt(2, Integer.parseInt(componenteId));
                    stmtRelacion.executeUpdate();
                }
            }

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean eliminarMolde(String nombreMolde) {
        String sqlEliminarRelacion = "DELETE FROM molde_componente WHERE molde = ?";
        String sqlEliminarMolde = "DELETE FROM molde WHERE nombre = ?";

        try (Connection conn = ConexionDB.getInstance().getConnection();
             PreparedStatement stmtRelacion = conn.prepareStatement(sqlEliminarRelacion);
             PreparedStatement stmtMolde = conn.prepareStatement(sqlEliminarMolde)) {

            // Eliminar relaciones en la tabla `molde_componente`
            stmtRelacion.setString(1, nombreMolde);
            stmtRelacion.executeUpdate();

            // Eliminar el molde en la tabla `molde`
            stmtMolde.setString(1, nombreMolde);
            int filasEliminadas = stmtMolde.executeUpdate();

            return filasEliminadas > 0;
        } catch (SQLException e) {
            return false;
        }
    }



    
}
