# 🎬 Sistema de Gestión de Películas (Java + H2)

Aplicación modular desarrollada en **Java** y conectada a una base de datos embebida **H2**, diseñada para gestionar películas, directores y géneros de forma sencilla, directa y automatizada.

---

## 🚀 Características Principales

* **Arquitectura Modular (3 archivos principales):** Código limpio y separado por responsabilidades (`Conexión`, `Creación de Tablas` y `Menú Principal`).
* **Base de Datos H2:** Utiliza almacenamiento local basado en archivos (`jdbc:h2:./peliculasbd`).
* **Bloques de Texto SQL:** Uso de sintaxis moderna (`"""`) para sentencias DDL limpias y legibles.
* **Registro Automatizado:** Con una sola entrada por consola, el sistema registra de forma enlazada el director, el género y la película correspondiente capturando sus IDs automáticamente.
* **Consultas con JOIN:** Permite listar las películas mostrando sus relaciones (título, año, director y género).
* **CRUD Básico:** Funcionalidades para registrar, listar y eliminar registros.

---

## 📂 Estructura del Proyecto

El proyecto se compone de tres clases principales ubicadas en el mismo paquete:

1. **`Conexion.java`**: Centraliza la apertura de la conexión a la base de datos H2 mediante JDBC.
2. **`CrearTablas.java`**: Verifica y crea automáticamente las tablas relacionales (`DIRECTORES`, `GENEROS`, `PELICULAS`) si no existen previamente.
3. **`MenuPrincipal.java`**: Contiene la lógica interactiva por consola (`main`), permitiendo al usuario navegar por las opciones del sistema.

---

## 🗄️ Esquema Relacional

El sistema opera sobre 3 tablas conectadas mediante claves foráneas (`FOREIGN KEY`):
* **`DIRECTORES`**: `ID_DIRECTOR` (PK), `NOMBRE`, `NACIONALIDAD`, `FECHA_NACIMIENTO`
* **`GENEROS`**: `ID_GENERO` (PK), `NOMBRE`
* **`PELICULAS`**: `ID_PELICULA` (PK), `TITULO`, `ANIO_ESTRENO`, `DURACION_MINUTOS`, `ID_DIRECTOR` (FK), `ID_GENERO` (FK)

---

## ⚙️ Requisitos Previos

* **Java JDK 15 o superior** (necesario para la compatibilidad con los bloques de texto `"""`).
* Dependencia o Driver de **H2** configurado en tu entorno o IDE (IntelliJ, Eclipse, etc.).

---

## 🛠️ Instrucciones de Ejecución

1. Clona o descarga este repositorio en tu equipo.
2. Abre el proyecto en tu entorno de desarrollo Java favorito (como IntelliJ IDEA).
3. Asegúrate de incluir la librería del driver de H2 en el *Classpath* de tu proyecto.
4. Ejecuta el archivo **`MenuPrincipal.java`**. El sistema creará las tablas automáticamente y te mostrará el siguiente menú en consola:

```text
--- GESTIÓN DE PELÍCULAS ---
1. Registrar película completa (Director, Género y Película)
2. Listar todas las películas
3. Eliminar película por ID
4. Salir
Elige una opción:
