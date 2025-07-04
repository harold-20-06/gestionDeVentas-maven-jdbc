# 🧾 Sistema de Gestión de Ventas – java + JDBC + Maven

[![Java](https://img.shields.io/badge/Java-SE%208%2B-%23f89820?logo=java)](https://www.oracle.com/java/)
[![JDBC](https://img.shields.io/badge/JDBC-Direct%20Connection-%23007396)](https://docs.oracle.com/javase/tutorial/jdbc/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-%23316192?logo=postgresql)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Build%20Tool-%23C71A36?logo=apache-maven)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue)](https://opensource.org/licenses/MIT)

---

Este proyecto es una aplicación de consola construida en Java que gestiona operaciones CRUD sobre productos, clientes y ventas. Utiliza JDBC para conectarse directamente a una base de datos PostgreSQL, empleando arquitectura en capas y gestión con Maven. Sirve como demostración práctica de dominio en bases de datos relacionales y buenas prácticas en backend Java sin frameworks intermedios.

---

## 🛠️ Tecnologías utilizadas

- Java SE 8+
- JDBC (Java Database Connectivity)
- PostgreSQL
- Maven
- Arquitectura multicapa (Entidad, DAO, Servicio, Vista)

---

## 🎯 Propósito del proyecto

Desarrollar un sistema de gestión de ventas funcional desde consola, que permita el mantenimiento completo de registros a través de operaciones CRUD. Está enfocado en reforzar habilidades fundamentales del backend Java, acceso directo a base de datos mediante SQL, y estructuración lógica del código en capas modulares.

---

## ✅ Estructura de codigo

gestionDeVentas-maven-jdbc/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── spectrum/
│                   └── crudventas/
│                       ├── appdeconsola/           # Clases con menús y lógica de consola (main)
│                       │   └── ConsoleApp.java
│                       ├── dao/                    # Interfaces e implementaciones JDBC
│                       │   ├── ClienteDAO.java
│                       │   └── ClienteDAOImpl.java
│                       │   ├── ComercialDAO.java
│                       │   └── ComercialDAOImpl.java
│                       │   ├── PedidoDAO.java
│                       │   └── PedidoDAOImpl.java                             
│                       ├── model/                  # Clases de entidad (modelo de datos)
│                       │   └── Cliente.java
│                       │   └── Comercial.java
│                       │   └── Pedido.java
│                       ├── service/                # Lógica de negocio
│                       │   └── ClienteService.java
│                       │   └── ClienteServiceImpl.java
│                       │   └── ComercialService.java
│                       │   └── ComercialServiceImpl.java
│                       │   └── PedidoService.java
│                       │   └── PedidoServiceImpl.java
│                       └── utilities/              # Utilidades como la conexión a la base de datos
│                           └── BaseDatos.java


## ✅ Descripción de carpetas clave

app/
Contiene el punto de entrada de la aplicación (ConsoleApp.java) y lógica de interfaz por consola.

dao/
Define la capa de acceso a datos usando JDBC (ClienteDAO, ClienteDAOImpl, ComercialDAO, ComercialDAOImpl,PedidoDAO, PedidoDAOImpl).

model/
Clases que representan las entidades del dominio, en este caso cliente, comercial y pedido.

service/
Contiene la lógica de negocio y operaciones intermedias entre consola y DAO.

utilities/
Herramientas comunes, como la clase BaseDatos.java que gestiona la conexión a PostgreSQL.

---

## 🚀 Ejecución

1. **Clonar el repositorio**:

   git clone https://github.com/harold-20-06/gestionDeVentas-maven-jdbc.git

2. 	Configurar base de datos PostgreSQL:
• 	Crear tablas necesarias (cliente, comercial, pedido).
• 	Actualizar credenciales en  /utilities/BaseDatos.java para tu entorno local.

3. 	Ejecutar el programa:
• 	Abrir el proyecto en tu IDE favorito (IntelliJ, NetBeans, Eclipse).
• 	Correr desde  com.spectrum.crudventas.appdeconsola.ConsoleApp en el paquete app.

---

## ✅ Funcionalidades principales
• 	Registro, edición y eliminación de cliente, comercial, pedido.
• 	Consulta individual y listados generales.
• 	Validaciones simples para consistencia de datos.
• 	Separación lógica y ordenada en capas de desarrollo.

---

## 📜 Licencia
Este proyecto está licenciado bajo la [MIT License](https://opensource.org/licenses/MIT) — consulta el archivo [`LICENSE`](./LICENSE) para más información.

---

## 👨‍💻 Autor
Creado con ❤️ por [@harold-20-06](https://github.com/harold-20-06)
🔗 [LinkedIn](https://www.linkedin.com/in/harold-montecinos/)
🔗 [GitHub](https://github.com/harold-20-06)
