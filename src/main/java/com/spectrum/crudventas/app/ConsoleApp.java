package com.spectrum.crudventas.app;

import com.spectrum.crudventas.dao.ClienteDaoImpl;
import com.spectrum.crudventas.model.Cliente;
import com.spectrum.crudventas.service.ClienteService;
import com.spectrum.crudventas.service.ClienteServiceImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    // Inicializar las capas y dependencias
    private static ClienteService clienteService = new ClienteServiceImpl(new ClienteDaoImpl());

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("-------- Sales Management --------");
            System.out.println("1. Client Management");
            System.out.println("2. Commercial Management");
            System.out.println("3. Order Management");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea
            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuComerciales();
                case 3 -> menuPedidos();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please enter a valid option.");
            }
        }
        System.out.println("Goodbye!");
    }

    private static void menuClientes() {
        boolean exit = false;

        while (!exit) {
            System.out.println("-------- Client Management --------");
            System.out.println("1. Add Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. Get All Clients");
            System.out.println("5. search by criteria");
            System.out.println("6. Get All Clients by letter");
            System.out.println("0. Back");

            System.out.print("Enter option: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea

            switch (opcion) {
                case 1 -> addCliente();
                case 2 -> updateCliente();
                case 3 -> deleteCliente();
                case 4 -> getAllClientes();
                case 5 -> searchByCriterio();
                case 6 -> searchByLetter();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please enter a valid option.");
            }
        }
    }

    private static void addCliente() {
        System.out.println("-------- Add Client --------");

        System.out.print("Enter nombre*: ");
        String nombre = scanner.nextLine();

            if (nombre.isEmpty()) {
                System.out.println("El nombre es un campo requerido");
                return;
            }

        System.out.print("Enter apellido 1*: ");
        String apellido1 = scanner.nextLine();
            if (apellido1.isEmpty()) {
                System.out.println("El apellido 1 es un campo requerido");
                return;
            }
        System.out.print("Enter apellido 2: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Enter city: ");
        String ciudad = scanner.nextLine();
        System.out.print("Enter category: ");
        String categoriaStr = scanner.nextLine();
            int categoria;
            try {
                if (categoriaStr.equals("")) {
                    categoria = 0;
                } else {
                    categoria = Integer.parseInt(categoriaStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("La categoría debe ser un número válido");
                return;
            }
            try {
                Cliente cliente = new Cliente(nombre, apellido1, apellido2, ciudad, categoria);
                int addExitoso = clienteService.addCliente(cliente);
                if (addExitoso!=0) {
                    System.out.println("client added successfully.");
                } else {
                    System.out.println("\n" +
                            "The client was not inserted, an error occurred while inserting into the database.");
                }
            } catch (Exception e) {
                System.out.println("Error adding client: " + e.getMessage());
                e.printStackTrace();
            }
    }
    private static void updateCliente() {
        System.out.println("-------- Update Client --------");
        System.out.print("Enter the client ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el salto de línea

        Cliente cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            System.out.println("No client found with that ID.");
            return;
        }

        System.out.print("Enter nombre*: ");
        String nombre = scanner.nextLine();
        if (nombre.isEmpty()) {
            System.out.println("El nombre es un campo requerido");
            return;
        }
        System.out.print("Enter apellido 1*: ");
        String apellido1 = scanner.nextLine();
        if (apellido1.isEmpty()) {
            System.out.println("El apellido 1 es un campo requerido");
            return;
        }
        System.out.print("Enter apellido 2: ");
        String apellido2 = scanner.nextLine();
        System.out.print("Enter city: ");
        String ciudad = scanner.nextLine();
        System.out.print("Enter category: ");
        String categoriaStr = scanner.nextLine();
        int categoria;
        try {
            if(categoriaStr.equals("")){
                    categoria = 0;
            }else {
                categoria = Integer.parseInt(categoriaStr);
            }
        } catch (NumberFormatException e) {
            System.out.println("La categoría debe ser un número válido");
            return;
        }
        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setApellido1(apellido1);
        cliente.setApellido2(apellido2);
        cliente.setCiudad(ciudad);
        cliente.setCategoria(categoria);

        boolean updateExitoso = clienteService.updateCliente(cliente);
        if(updateExitoso) {
            System.out.println("Client updated successfully.");
        }
        else {
            System.out.println("The client was not updated, an error occurred while updating into the database.");
        }
    }

    private static void deleteCliente() {
        System.out.println("-------- Delete Client --------");
        System.out.print("Enter the ID client to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("El id debe ser un número válido");
            return;
        }
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente == null) {
            System.out.println("No client found with that ID.");
            return;
        }
        System.out.println(cliente);
        System.out.print("Are you sure you want to delete the client? (y/n): ");
        String confirm = scanner.nextLine().toLowerCase();
        if (confirm.equals("y")) {
            cliente.setId(id);
            clienteService.deleteCliente(cliente);
            System.out.println("Client deleted successfully.");
        } else if (confirm.equals("n")) {
            System.out.println("Deletion canceled.");
            return;
        } else {
            System.out.println("Invalid input. Please enter either 'y' or 'n'. Deletion canceled.");
            return;
        }
    }

    private static void getAllClientes() {
        System.out.println("-------- All Clientes --------");
        List<Cliente> clientes = clienteService.getAllClientes();
        imprimirClientes(clientes);
    }
    private static void imprimirClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private static void searchByCriterio(){
        boolean exit = false;

        while (!exit) {
            System.out.println("-------- Buscar Cliente --------");
            System.out.println("1. Buscar por Id");
            System.out.println("2. Buscar por Nombre");
            System.out.println("3. Buscar por Apellido 1");
            System.out.println("4. Buscar por Apellido 2");
            System.out.println("5. Buscar por Ciudad");
            System.out.println("6. Buscar por Categoria");
            System.out.println("0. Back");

            int criterio = scanner.nextInt();
            scanner.nextLine();
            switch (criterio) {
                case 1:
                    System.out.println("Introduzca id a buscar");
                    int id;
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                        scanner.nextLine();

                        List<Cliente> clientesId = clienteService.getClientePorCriterio("id",id);
                        imprimirClientes(clientesId);
                    } else {
                        String input = scanner.nextLine();
                        System.out.println("Entrada inválida. Se esperaba un número entero.");
                    }
                    break;
                case 2:
                    System.out.println("Introduzca nombre a buscar");
                    String nombre = scanner.nextLine();
                    List<Cliente> clientesNombre = clienteService.getClientePorCriterio("nombre", nombre);
                    imprimirClientes(clientesNombre);
                    break;
                case 3:
                    System.out.println("Introduzca apellido 1 a buscar");
                    String apellido1 = scanner.nextLine();
                    List<Cliente> clientesApellido1 = clienteService.getClientePorCriterio("apellido1", apellido1);
                    imprimirClientes(clientesApellido1);
                    break;
                case 4:
                    System.out.println("Introduzca apellido 2 a buscar");
                    String apellido2 = scanner.nextLine();
                    List<Cliente> clientesApellido2 = clienteService.getClientePorCriterio("apellido2", apellido2);
                    imprimirClientes(clientesApellido2);
                    break;
                case 5:
                    System.out.println("Introduzca ciudad a buscar");
                    String ciudad = scanner.nextLine();
                    List<Cliente> clientesCiudad = clienteService.getClientePorCriterio("ciudad", ciudad);
                    imprimirClientes(clientesCiudad);
                    break;
                case 6:
                    System.out.println("Introduzca categoría a buscar");
                    int categoria;
                    if (scanner.hasNextInt()) {
                        categoria = scanner.nextInt();
                        scanner.nextLine();

                        List<Cliente> clientesCategoria = clienteService.getClientePorCriterio("categoría", categoria);
                        imprimirClientes(clientesCategoria);
                    } else {
                        String input = scanner.nextLine();
                        System.out.println("Entrada inválida. Se esperaba un número entero.");
                    }
                    break;
                case 0: exit = true;
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }
        System.out.println("Terminando busquedas por criterio");
}

    private static void searchByLetter(){
        System.out.println("Introduzca 1er letra Apellido1 a buscar");
        String letra = scanner.nextLine();
        imprimirClientes(clienteService.getClientePorLetra(letra));
    }


    private static void menuComerciales() {
        boolean exit = false;

        while (!exit) {
            System.out.println("-------- Commercial Management --------");
            System.out.println("1. Add Commercial");
            System.out.println("2. Update Commercial");
            System.out.println("3. Delete Commercial");
            System.out.println("4. Get All Commercials");
            System.out.println("0. Back");
            System.out.print("Enter option: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea

            switch (opcion) {
                case 1 -> agregarComercial();
                case 2 -> actualizarComercial();
                case 3 -> eliminarComercial();
                case 4 -> obtenerTodosLosComerciales();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please enter a valid option.");
            }
        }
    }

    private static void agregarComercial() {
        System.out.println("-------- Add Commercial --------");
        // Aquí va la lógica para agregar un comercial
    }

    private static void actualizarComercial() {
        System.out.println("-------- Update Commercial --------");
        // Aquí va la lógica para actualizar un comercial
    }

    private static void eliminarComercial() {
        System.out.println("-------- Delete Commercial --------");
        // Aquí va la lógica para eliminar un comercial
    }

    private static void obtenerTodosLosComerciales() {
        System.out.println("-------- All Commercials --------");
        // Aquí va la lógica para obtener todos los comerciales
    }

    private static void menuPedidos() {
        boolean exit = false;

        while (!exit) {
            System.out.println("-------- Order Management --------");
            System.out.println("1. Add Order");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Get All Orders");
            System.out.println("0. Back");
            System.out.print("Enter option: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea

            switch (opcion) {
                case 1 -> agregarPedido();
                case 2 -> actualizarPedido();
                case 3 -> eliminarPedido();
                case 4 -> obtenerTodosLosPedidos();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please enter a valid option.");
            }
        }
    }

    private static void agregarPedido() {
        System.out.println("-------- Add Order --------");
        // Aquí va la lógica para agregar un pedido
    }

    private static void actualizarPedido() {
        System.out.println("-------- Update Order --------");
        // Aquí va la lógica para actualizar un pedido
    }

    private static void eliminarPedido() {
        System.out.println("-------- Delete Order --------");
        // Aquí va la lógica para eliminar un pedido
    }

    private static void obtenerTodosLosPedidos() {
        System.out.println("-------- All Orders --------");
        // Aquí va la lógica para obtener todos los pedidos
    }
}