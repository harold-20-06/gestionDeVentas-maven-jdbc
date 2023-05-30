package com.spectrum.crudventas.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class BaseDatos {
        private static final String URL = "jdbc:postgresql://localhost:5432/ventas";  //ventas nombre de la DB
        private static final String USUARIO = "dev";//usuario
        private static final String CONTRASENA = "password"; //password a la DB

        public BaseDatos() {
        // Constructor
        }
        public static Connection obtenerConexion() throws SQLException {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        }

        public static Connection obtenerConexionmock() throws SQLException {
        return null;
    }
        public static void cerrarConexion(Connection conn) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
}
