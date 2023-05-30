package utilities;

import com.spectrum.crudventas.utilities.BaseDatos;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BaseDatosTest {

    @Test
    public void testConexionsuccess() throws SQLException {
        BaseDatos baseDatos = new BaseDatos();
        Connection conexion = baseDatos.obtenerConexion();
        assertNotNull(conexion, "La conexión no debe ser nula");
        conexion.close();
    }

    @Test
    public void testConexionfallida() throws SQLException {
        BaseDatos baseDatos = new BaseDatos();
        Connection conexion = baseDatos.obtenerConexionmock();
        assertNull(conexion, "La conexión debe ser nula");
    }

}
