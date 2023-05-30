package com.spectrum.crudventas.service;

import com.spectrum.crudventas.model.Cliente;

import java.util.List;

public interface ClienteService {

    int addCliente(Cliente cliente);

    boolean updateCliente(Cliente cliente);

    Cliente getClienteById(int id);

    boolean deleteCliente(Cliente cliente);

    List<Cliente> getAllClientes();

    int getCantidadClientes();

    List<Cliente> getClientePorCriterio(String criterio, String valor);

    List<Cliente> getClientePorCriterio(String criterio, int valor);
}
