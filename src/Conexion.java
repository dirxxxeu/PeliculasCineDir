import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static void main(String[] args) {
        // 1. Las tres piezas de la conexion, guardadas en variables
        String url = "jdbc:h2:./peliculasbd";   // donde esta la base de datos
        String user = "sa";                // usuario por defecto de H2
        String password = "";              // contrasena vacia por defecto

        // 2. try-with-resources: abre la conexion y la CIERRA SOLA al terminar
        try (Connection con = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexion abierta con H2");
        } catch (SQLException e) { // 3. si algo falla, entra aqui
            System.out.println("No se pudo conectar: " + e.getMessage());
        }
        System.out.println("Programa terminado");
    }
}
