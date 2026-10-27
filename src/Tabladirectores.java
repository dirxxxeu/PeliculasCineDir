import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabladirectores {
    public static void main(String[] args) {
        String url = "jdbc:h2:./peliculasbd";
        try (Connection con = DriverManager.getConnection(url, "sa", "")) {
            Statement st = con.createStatement();

            // 1. El SQL va en un bloque de texto (tres comillas): permite
            //    escribirlo en varias lineas, igual que en la consola
            String sql = """
               
                    CREATE TABLE IF NOT EXISTS DIRECTORES (
                               ID_DIRECTOR INT PRIMARY KEY AUTO_INCREMENT,
                               NOMBRE VARCHAR(100) NOT NULL,
                               NACIONALIDAD VARCHAR(50),
                               FECHA_NACIMIENTO DATE
                           )
                """;

            st.executeUpdate(sql); // 2. enviamos la orden: crea la tabla
            System.out.println("Tabla Directores lista");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
