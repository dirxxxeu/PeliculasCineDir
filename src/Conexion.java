import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection conectar() {
        try {
            String url = "jdbc:h2:C:/Estudio/MF0965_3 Desarrollo de software basado/PeliculasCineDir/peliculasbd";
            return DriverManager.getConnection(url, "sa", "");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}