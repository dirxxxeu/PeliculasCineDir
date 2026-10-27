import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tablapeliculas {
    public static void main(String[] args) {
        String url = "jdbc:h2:./peliculasbd";
        try (Connection con = DriverManager.getConnection(url, "sa", "")) {
            Statement st = con.createStatement();

            String sql = """
                CREATE TABLE IF NOT EXISTS PELICULAS (
                    ID_PELICULA INT PRIMARY KEY AUTO_INCREMENT,
                    TITULO VARCHAR(150) NOT NULL,
                    ANIO_ESTRENO INT,
                    DURACION_MINUTOS INT,
                    ID_DIRECTOR INT,
                    ID_GENERO INT,
                    FOREIGN KEY (ID_DIRECTOR) REFERENCES DIRECTORES(ID_DIRECTOR) ON DELETE SET NULL,
                    FOREIGN KEY (ID_GENERO) REFERENCES GENEROS(ID_GENERO) ON DELETE SET NULL
                )
                """;

            st.executeUpdate(sql);
            System.out.println("Tabla Peliculas lista");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}