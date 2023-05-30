package com.spectrum.crudventas.service;

import com.spectrum.crudventas.dao.ClienteDao;
import com.spectrum.crudventas.dao.ClienteDaoImpl;
import com.spectrum.crudventas.model.Cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClienteServiceImpl implements ClienteService {
    ClienteDao clienteDao= new ClienteDaoImpl();

    public ClienteServiceImpl(ClienteDaoImpl clienteDao) {
    }

    @Override
    public int addCliente(Cliente cliente) {
        int addExitoso = 0;
        try{
            if (cliente.getNombre().isEmpty()) {
                System.out.println("El nombre... es un campo requerido");
                return addExitoso;
            }
            if (cliente.getApellido1().isEmpty()) {
                System.out.println("El apellido1...  es un campo requerido");
                return addExitoso;
            }
            try {
                if((String.valueOf(cliente.getCategoria())) ==""){
                    cliente.setCategoria(0);
                }
                int categoria = Integer.parseInt(String.valueOf(cliente.getCategoria()));
                if (categoria < 0) {
                    System.out.println("La categoría debe ser un número válido no negativo");
                    return addExitoso;
                }
            } catch (NumberFormatException e) {
                System.out.println("La categoría debe ser un número válido");
            }
            addExitoso = clienteDao.addCliente(cliente);
        }
        catch(Exception e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return addExitoso;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {

        boolean updateExitoso = false;
        try{
            if (cliente.getNombre().isEmpty()) {
                System.out.println("El nombre... es un campo requerido");
                return updateExitoso;
            }
            if (cliente.getApellido1().isEmpty()) {
                System.out.println("El apellido1...  es un campo requerido");
                return updateExitoso;
            }

            try {
                if((String.valueOf(cliente.getCategoria())) ==""){
                    cliente.setCategoria(0);
                }
                int categoria = Integer.parseInt(String.valueOf(cliente.getCategoria()));
                if (categoria < 0) {
                    System.out.println("La categoría debe ser un número válido no negativo");
                    return updateExitoso;
                }

            } catch (NumberFormatException e) {
                System.out.println("La categoría debe ser un número válido");
            }
            updateExitoso = clienteDao.updateCliente(cliente);
        }
        catch(Exception e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return updateExitoso;

    }

    @Override
    public Cliente getClienteById(int id) {
        try {
            Cliente cliente = clienteDao.getClienteById(id);
            return cliente;
        } catch (Exception e) {
            System.out.println("Error al obtener el cliente por ID: " + e.getMessage());
            e.printStackTrace();
            return null; // Retornar null en caso de error
        }
    }

    @Override
    public boolean deleteCliente(Cliente cliente) {
        boolean deleteExitoso = false;
        try {
            int id = Integer.parseInt(String.valueOf(cliente.getId()));
            deleteExitoso = clienteDao.deleteCliente(cliente);
        } catch (NumberFormatException e) {
            System.out.println("El id debe ser un número válido");
        } catch (Exception e) {
            System.out.println("Error al borrar el cliente: " + e.getMessage());
            e.printStackTrace();
        }
        return deleteExitoso;
    }

    @Override
    public List<Cliente> getAllClientes() {
        try {
            List<Cliente> clientes = clienteDao.getAllClientes();
            return clientes;
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retornar una lista vacía en caso de error
        }
    }

    @Override
    public int getCantidadClientes() {

        return clienteDao.getCantidadClientes();

    }
    @Override
    public List<Cliente> getClientePorCriterio(String criterio, String valor) {
        if (criterio.isEmpty() || valor.isEmpty()) {
            return Collections.emptyList();
        }
        // Verificar criterio válido
        if (!isValidCriterio(criterio)) {
            return Collections.emptyList();
        }
        return clienteDao.getClientePorCriterio(criterio,valor);
    }
    private boolean isValidCriterio(String criterio) {

        return criterio.equals("id") || criterio.equals("nombre") || criterio.equals("apellido1") || criterio.equals("apellido2") || criterio.equals("ciudad")|| criterio.equals("categoría");
    }

    @Override
    public List<Cliente> getClientePorCriterio(String criterio, int valor) {
        if (criterio.isEmpty()) {
            return Collections.emptyList();
        }
        // Verificar criterio válido
        if (!isValidCriterio(criterio)) {
            return Collections.emptyList();
        }
        int intValue;
        try {
            intValue = Integer.parseInt(String.valueOf(valor));
        } catch (NumberFormatException e) {
            // El valor no es un entero válido
            return Collections.emptyList();
        }
        return clienteDao.getClientePorCriterio(criterio,valor);
    }
}