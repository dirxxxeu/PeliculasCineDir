import java.sql.*;
import java.util.Scanner;

public class MenuPpal {
    public static void main(String[] args) {
        // Creamos las tablas automáticamente al arrancar
        CrearTablas.crear();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE PELÍCULAS ---");
            System.out.println("1. Registrar película completa (Director, Género y Película)");
            System.out.println("2. Listar todas las películas");
            System.out.println("3. Eliminar película por ID");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            try (Connection con = Conexion.conectar()) {
                if (con == null) continue;

                if (opcion == 1) {
                    // Pedir datos del Director
                    System.out.print("Nombre del director: ");
                    String nombreDir = sc.nextLine();
                    System.out.print("Nacionalidad del director: ");
                    String nacDir = sc.nextLine();
                    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                    String fechaDir = sc.nextLine();

                    // Pedir datos del Género
                    System.out.print("Nombre del género: ");
                    String nombreGen = sc.nextLine();

                    // Pedir datos de la Película
                    System.out.print("Título de la película: ");
                    String titulo = sc.nextLine();
                    System.out.print("Año de estreno: ");
                    int anio = sc.nextInt();
                    System.out.print("Duración en minutos: ");
                    int duracion = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer

                    // 1. Insertar Director y capturar su ID autogenerado
                    String sqlDir = "INSERT INTO DIRECTORES (NOMBRE, NACIONALIDAD, FECHA_NACIMIENTO) VALUES (?, ?, ?)";
                    PreparedStatement psDir = con.prepareStatement(sqlDir, Statement.RETURN_GENERATED_KEYS);
                    psDir.setString(1, nombreDir);
                    psDir.setString(2, nacDir);
                    psDir.setDate(3, Date.valueOf(fechaDir));
                    psDir.executeUpdate();

                    ResultSet rsDir = psDir.getGeneratedKeys();
                    int idDirector = 0;
                    if (rsDir.next()) {
                        idDirector = rsDir.getInt(1);
                    }

                    // 2. Insertar Género y capturar su ID autogenerado
                    String sqlGen = "INSERT INTO GENEROS (NOMBRE) VALUES (?)";
                    PreparedStatement psGen = con.prepareStatement(sqlGen, Statement.RETURN_GENERATED_KEYS);
                    psGen.setString(1, nombreGen);
                    psGen.executeUpdate();

                    ResultSet rsGen = psGen.getGeneratedKeys();
                    int idGenero = 0;
                    if (rsGen.next()) {
                        idGenero = rsGen.getInt(1);
                    }

                    // 3. Insertar Película vinculando los dos IDs anteriores
                    String sqlPeli = "INSERT INTO PELICULAS (TITULO, ANIO_ESTRENO, DURACION_MINUTOS, ID_DIRECTOR, ID_GENERO) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement psPeli = con.prepareStatement(sqlPeli);
                    psPeli.setString(1, titulo);
                    psPeli.setInt(2, anio);
                    psPeli.setInt(3, duracion);
                    psPeli.setInt(4, idDirector);
                    psPeli.setInt(5, idGenero);
                    psPeli.executeUpdate();

                    System.out.println("¡Película, director y género guardados con éxito!");

                } else if (opcion == 2) {
                    // Listar películas con JOIN
                    String sql = """
                        SELECT p.ID_PELICULA, p.TITULO, p.ANIO_ESTRENO, d.NOMBRE AS DIRECTOR, g.NOMBRE AS GENERO 
                        FROM PELICULAS p 
                        LEFT JOIN DIRECTORES d ON p.ID_DIRECTOR = d.ID_DIRECTOR
                        LEFT JOIN GENEROS g ON p.ID_GENERO = g.ID_GENERO
                        """;
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);

                    System.out.println("\n--- LISTADO DE PELÍCULAS ---");
                    boolean hayDatos = false;
                    while (rs.next()) {
                        hayDatos = true;
                        System.out.println("ID       : " + rs.getInt("ID_PELICULA"));
                        System.out.println("Título   : " + rs.getString("TITULO"));
                        System.out.println("Año      : " + rs.getInt("ANIO_ESTRENO"));
                        System.out.println("Director : " + (rs.getString("DIRECTOR") != null ? rs.getString("DIRECTOR") : "Desconocido"));
                        System.out.println("Género   : " + (rs.getString("GENERO") != null ? rs.getString("GENERO") : "Desconocido"));
                        System.out.println("-----------------------------");
                    }
                    if (!hayDatos) {
                        System.out.println("No hay películas registradas.");
                    }

                } else if (opcion == 3) {
                    // Eliminar película
                    System.out.print("Introduce el ID de la película que deseas eliminar: ");
                    int idPeli = sc.nextInt();
                    sc.nextLine();

                    String sql = "DELETE FROM PELICULAS WHERE ID_PELICULA = ?";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, idPeli);
                    int filas = ps.executeUpdate();

                    if (filas > 0) {
                        System.out.println("¡Película eliminada con éxito!");
                    } else {
                        System.out.println("No se encontró ninguna película con ese ID.");
                    }
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 4);

        sc.close();
        System.out.println("Programa finalizado.");
    }
}