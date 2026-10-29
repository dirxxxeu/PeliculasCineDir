import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {
    public static void crear() {
        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement()) {

            String sqlDirectores = """
                CREATE TABLE IF NOT EXISTS DIRECTORES (
                    ID_DIRECTOR INT PRIMARY KEY AUTO_INCREMENT,
                    NOMBRE VARCHAR(100),
                    NACIONALIDAD VARCHAR(50),
                    FECHA_NACIMIENTO DATE
                )
                """;
            st.execute(sqlDirectores);

            String sqlGeneros = """
                CREATE TABLE IF NOT EXISTS GENEROS (
                    ID_GENERO INT PRIMARY KEY AUTO_INCREMENT,
                    NOMBRE VARCHAR(50)
                )
                """;
            st.execute(sqlGeneros);

            String sqlPeliculas = """
                CREATE TABLE IF NOT EXISTS PELICULAS (
                    ID_PELICULA INT PRIMARY KEY AUTO_INCREMENT,
                    TITULO VARCHAR(150),
                    ANIO_ESTRENO INT,
                    DURACION_MINUTOS INT,
                    ID_DIRECTOR INT,
                    ID_GENERO INT,
                    FOREIGN KEY (ID_DIRECTOR) REFERENCES DIRECTORES(ID_DIRECTOR) ON DELETE SET NULL,
                    FOREIGN KEY (ID_GENERO) REFERENCES GENEROS(ID_GENERO) ON DELETE SET NULL
                )
                """;
            st.execute(sqlPeliculas);

            System.out.println("Tablas verificadas correctamente.");

        } catch (Exception e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    }
}