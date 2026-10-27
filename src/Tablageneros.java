import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tablageneros {
    public static void main(String[] args) {
        String url = "jdbc:h2:./peliculasbd";
        try (Connection con = DriverManager.getConnection(url, "sa", "")) {
            Statement st = con.createStatement();

            String sql = """
                CREATE TABLE IF NOT EXISTS GENEROS (
                    ID_GENERO INT PRIMARY KEY AUTO_INCREMENT,
                    NOMBRE VARCHAR(50) NOT NULL
                )
                """;

            st.executeUpdate(sql);
            System.out.println("Tabla Generos lista");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}