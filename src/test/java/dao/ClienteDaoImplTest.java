package dao;

import com.spectrum.crudventas.dao.ClienteDao;
import com.spectrum.crudventas.dao.ClienteDaoImpl;
import com.spectrum.crudventas.model.Cliente;
import com.spectrum.crudventas.service.ClienteService;
import com.spectrum.crudventas.service.ClienteServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDaoImplTest {

    @Test
    public void testAddCliente() throws SQLException {
        // Crear una instancia de la clase ClienteDao
        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        // Crear un cliente para agregar
        Cliente cliente = new Cliente("John", "Doe", "Smith", "Ciudad", 100);
        // Llamar al método a probar
        int idGenerado = clienteService.addCliente(cliente);
        cliente.setId(idGenerado);
        // Verificar que el cliente se agregó correctamente utilizando el método de búsqueda por ID
        Cliente clienteEncontrado = clienteService.getClienteById(cliente.getId());

        assertNotNull(clienteEncontrado, "El cliente debe estar presente en la base de datos");
        assertEquals(cliente, clienteEncontrado, "El cliente encontrado debe ser el mismo objeto que se agregó");

        clienteService.deleteCliente(cliente);
    }

    @Test
    public void testSuccessUpdateCliente() {
        // Crear una instancia de la clase ClienteDao
        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

//        find CLient by id
        Cliente clienteParaActualizar = clienteService.getClienteById(1);
        Cliente clienteinicial = clienteService.getClienteById(1);
        System.out.println(clienteinicial.toString());
        assertNotNull(clienteParaActualizar, "El cliente no existe. No se puede realizar la actualización.");

//      insert new data

        clienteParaActualizar.setId(1);
        clienteParaActualizar.setNombre("Aron");
        clienteParaActualizar.setApellido1("trigo");
        clienteParaActualizar.setApellido2("Taboada");
        clienteParaActualizar.setCiudad("Bagdad");
        clienteParaActualizar.setCategoria(300);

//        Actualizar
        clienteService.updateCliente(clienteParaActualizar);

//        find client add by id
        Cliente clienteActualizado = clienteService.getClienteById(1);
        assertNotNull(clienteParaActualizar, "El cliente no existe. No se puede realizar.");

//        assert.equals
        assertEquals(clienteActualizado,clienteParaActualizar,"deben ser iguales");

        clienteService.updateCliente(clienteinicial);
    }

    @Test
    public void testFailUpdateCliente() {
        // Crear una instancia de la clase ClienteDao
        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

//        find CLient by id
        Cliente clienteParaActualizar = clienteService.getClienteById(1);
        assertNotNull(clienteParaActualizar, "El cliente no existe. No se puede realizar la actualización.");

//      insert new data

        clienteParaActualizar.setId(1);
        clienteParaActualizar.setNombre("Marlene");
        clienteParaActualizar.setApellido1("trigoso");
        clienteParaActualizar.setApellido2("Taborga");
        clienteParaActualizar.setCiudad("Bagdad");
        clienteParaActualizar.setCategoria(300);

//        Actualizar
        clienteService.updateCliente(clienteParaActualizar);

//        find client add by id seusa 4 para que sea distinto del 1 que si actualizamos
        Cliente clienteActualizado = clienteService.getClienteById(4);
        assertNotNull(clienteActualizado, "El cliente no existe.");

//        assert.equals
        assertNotEquals(clienteActualizado, clienteParaActualizar, "No deverian ser iguales");
    }

    @Test
    public void testSuccessDeleteCliente() throws SQLException {

        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        // Crear un cliente para agregar
        Cliente cliente = new Cliente("John", "Doe", "Smith", "Ciudad", 100);
        // Agregar el cliente a la base de datos
        int idGenerado = clienteService.addCliente(cliente);
//       buscar id a borrar
        Cliente clienteABorrar = clienteService.getClienteById(idGenerado);
//       borrar cliente

        clienteService.deleteCliente(clienteABorrar);
//       verificar que se borro

        assertNull(clienteService.getClienteById(idGenerado), "El cliente no se borro");
    }

    @Test
    public void testGetAllClientes() {
//
        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        int cantidad = clienteService.getCantidadClientes();

        // Obtener la lista de clientes
        List<Cliente> clientes = clienteService.getAllClientes();

        assertEquals(cantidad, clientes.size(), "La cantidad de clientes no coincide con la cantidad esperada");
    }

    @Test
    public void testGetClienteById() throws SQLException {
//
        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        Cliente cliente = new Cliente("pedrito","guzman","Loayza","Orlando",400);
        int idGenerado = clienteService.addCliente(cliente);
        cliente.setId(idGenerado);


        Cliente clienteObtenido = clienteService.getClienteById(cliente.getId());

        assertEquals(clienteObtenido,cliente,"El cliente obtenido debe ser igual al buscado");
        clienteService.deleteCliente(cliente);
    }

    @Test
    public void testFailGetClienteById() throws SQLException {

        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        Cliente clienteObtenido = clienteService.getClienteById(999);

        assertNull(clienteObtenido,"El cliente no existe asi que debe ser null");

    }

    @Test
    public void testgetCantidadClientes(){

        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        int cantidad = clienteService.getCantidadClientes();

        assertEquals(cantidad,48,"La cantidad de registros debe ser igual a la actual");
    }

    @Test
    public void testFailgetCantidadClientes(){

        ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

        int cantidad = clienteService.getCantidadClientes();

        assertNotEquals(cantidad,2,"La cantidad de registros debe ser distinta a la actual");
    }
}

