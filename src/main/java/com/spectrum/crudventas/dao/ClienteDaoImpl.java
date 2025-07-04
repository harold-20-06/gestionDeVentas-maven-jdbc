package com.spectrum.crudventas.dao;

import com.spectrum.crudventas.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.spectrum.crudventas.utilities.BaseDatos.cerrarConexion;
import static com.spectrum.crudventas.utilities.BaseDatos.obtenerConexion;

public class ClienteDaoImpl implements ClienteDao{


    @Override
    public int addCliente(Cliente cliente) throws SQLException {
        boolean addExitoso = false;
        int idGenerado = 0;
        try{
            Connection conn = obtenerConexion();

            PreparedStatement stmt =conn.prepareStatement("INSERT INTO Cliente (nombre,apellido1,apellido2,ciudad,categoría) values(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2,cliente.getApellido1());
            stmt.setString(3, cliente.getApellido2());
            stmt.setString(4, cliente.getCiudad());
            stmt.setInt(5,cliente.getCategoria());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                 idGenerado = generatedKeys.getInt(1);
            }
            addExitoso = true;
            cerrarConexion(conn);

        } catch (SQLException e) {
            System.out.println("Error al insertar  a la base de datos.");
            e.printStackTrace();
        }
        return idGenerado;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {
        boolean updateExitoso=false;
        try{
            Connection conn = obtenerConexion();

            PreparedStatement stmt =conn.prepareStatement("UPDATE Cliente set nombre = ?,apellido1 = ?,apellido2 =?,ciudad = ?,categoría = ? where id = ?");

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2,cliente.getApellido1());
            stmt.setString(3, cliente.getApellido2());
            stmt.setString(4, cliente.getCiudad());
            stmt.setInt(5,cliente.getCategoria());
            stmt.setInt(6,cliente.getId());

            stmt.executeUpdate();

            updateExitoso = true;
            cerrarConexion(conn);
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos.");
            e.printStackTrace();
        }
        return updateExitoso;
    }

    @Override
    public boolean deleteCliente(Cliente cliente) {
        try{
            Connection conn = obtenerConexion();

            PreparedStatement stmt =conn.prepareStatement("DELETE FROM Cliente where id = ?");

            stmt.setInt(1,cliente.getId());
            stmt.executeUpdate();
            cerrarConexion(conn);
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión a la base de datos.");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = obtenerConexion();

             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente ORDER BY id")) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoría"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente getClienteById(int id) {
        Cliente cliente = null;
        try (Connection conn = obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Cliente WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoría"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    public int getCantidadClientes(){
        int cantidad = 0;
        try{
            Connection conn = obtenerConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id) FROM CLIENTE");

            if (rs.next()) {
                cantidad = rs.getInt(1);
            }

        } catch (SQLException e) {
        e.printStackTrace();
    }
        return cantidad;
    }

    @Override
    public List<Cliente> getClientePorCriterio(String criterio, String valor) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Connection conn = obtenerConexion();

            String sql = "SELECT * FROM CLIENTE WHERE " + criterio + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, valor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoría"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
    @Override
    public List<Cliente> getClientePorCriterio(String criterio, int valor) {
        List<Cliente> clientes = new ArrayList<>();
        try {
            Connection conn = obtenerConexion();

            String sql = "SELECT * FROM CLIENTE WHERE " + criterio + " = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1,valor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoría"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> getClientePorLetra(String letra) {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = obtenerConexion()) {
            String query = "SELECT * FROM obtener_clientes_por_letra(?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, letra);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoría"));
                clientes.add(cliente);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

}