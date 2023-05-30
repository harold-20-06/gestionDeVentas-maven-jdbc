package com.spectrum.crudventas.dao;

import com.spectrum.crudventas.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDao {

    int addCliente(Cliente cliente) throws SQLException;
    boolean updateCliente(Cliente cliente);
    boolean deleteCliente(Cliente cliente);

    List<Cliente> getAllClientes();

    Cliente getClienteById(int id);

    int getCantidadClientes();

    List<Cliente> getClientePorCriterio(String criterio, String valor);

    List<Cliente> getClientePorCriterio(String criterio, int valor);
}
