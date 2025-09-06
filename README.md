# Sistema de Gestión para Clínica Veterinaria

Este proyecto es una API RESTful desarrollada en Java utilizando el framework Spring Boot. Proporciona un sólido sistema CRUD (Crear, Leer, Actualizar, Eliminar) para una clínica veterinaria, gestionando clientes, mascotas, hojas clínicas y citas.

---

## 🚀 Tecnologías y Dependencias

El proyecto se construyó con las siguientes tecnologías y dependencias clave:

### Tecnologías Principales
* **Java**: JDK 21
* **Spring Boot**: Un potente framework para crear aplicaciones basadas en Spring, listas para producción.
* **MySQL**: La base de datos relacional utilizada para almacenar todos los datos de la aplicación.
* **Maven**: La herramienta de automatización de construcción para gestionar las dependencias del proyecto.

### Dependencias de Spring
* `Spring Web`: Permite la construcción de aplicaciones web, incluyendo APIs RESTful.
* `Spring Data JPA`: Simplifica el acceso a los datos utilizando la API de Persistencia de Java.
* `MySQL Driver`: El controlador JDBC para la conexión con la base de datos MySQL.
* `Spring Boot DevTools`: Ofrece reinicios automáticos y recarga en vivo para una experiencia de desarrollo más rápida.

---

## 🛠️ Configuración e Instalación

1.  **Clonar el Repositorio**:
    ```bash
    git clone [(https://github.com/Arlon208/IE-GA7-220501096-AA3-EV01-EV02.git)]
    ```

2.  **Configuración de la Base de Datos**:
    * Asegúrate de tener un servidor MySQL local en funcionamiento.
    * Importa el archivo `cliente.sql` que se encuentra en el directorio raíz del proyecto a tu instancia de MySQL. Este script creará las tablas necesarias y las llenará con datos de ejemplo.

3.  **Ejecutar la Aplicación**:
    * Abre el proyecto en un IDE de Java como NetBeans o IntelliJ IDEA.
    * Ejecuta la clase principal de la aplicación. La API se iniciará en `http://localhost:8080`.

---

## 🗺️ Endpoints de la API

Puedes probar los endpoints de la API usando una herramienta como [Postman](https://www.postman.com/) o clientes similares. A continuación, se presenta una lista completa de los endpoints disponibles.

### Clientes (`/cliente`)

| Método | Endpoint | Descripción | Ejemplo de JSON |
| :--- | :--- | :--- | :--- |
| `POST` | `/cliente/add` | **Añade un nuevo cliente.** | `{"idCliente": 1111111,"nombre": "Roberto","ciudad": "Cartagena","direccion": "Calle 8c","telefono": "321888795"}` |
| `GET` | `/clientes` | **Lista todos los clientes.** | |
| `PUT` | `/cliente/update/{id}` | **Actualiza un cliente por ID.** | `{"nombre": "Nuevo Nombre", "telefono": "123456789"}` |
| `GET` | `/clientes/{id}` | **Busca un solo cliente por ID.** | |
| `DELETE` | `/clientes/{id}` | **Elimina un cliente por ID.** | |

---

### Mascotas (`/mascotas`)

| Método | Endpoint | Descripción | Ejemplo de JSON |
| :--- | :--- | :--- | :--- |
| `POST` | `/mascotas/add` | **Añade una nueva mascota.** | `{"nombre": "Dory", "especie": "Canino", "edad": 2, "genero": "Hembra", "cliente": {"idCliente": 1111111}}` |
| `GET` | `/mascotas` | **Lista todas las mascotas.** | |
| `PUT` | `/mascotas/update/{id}` | **Actualiza una mascota por ID.** | `{"edad": 3, "genero": "Macho"}` |
| `GET` | `/mascotas/{id}` | **Busca una sola mascota por ID.** | |
| `DELETE` | `/mascotas/delete/{id}` | **Elimina una mascota por ID.** | |

---

### Hojas Clínicas (`/hojaclinica`)

| Método | Endpoint | Descripción | Ejemplo de JSON |
| :--- | :--- | :--- | :--- |
| `POST` | `/hojaclinica/add` | **Añade una nueva hoja clínica.** | `{"motivo": "Fiebre y apatía", "examenes": "Hemograma completo", "medicamentos": "Amoxicilina", "fecha": "2025-09-06", "mascota": {"idMascota": 1}}` |
| `GET` | `/hojasclinicas` | **Lista todas las hojas clínicas.** | |
| `GET` | `/hojaclinica/{id}` | **Busca una sola hoja por ID.** | |
| `PUT` | `/hojaclinica/update/{id}` | **Actualiza una hoja clínica por ID.** | `{"motivo": "Sin cambios", "examenes": "Pendiente"}` |
| `DELETE` | `/hojaclinica/delete/{id}` | **Elimina una hoja clínica por ID.** | |

---

### Citas (`/citas`)

| Método | Endpoint | Descripción | Ejemplo de JSON |
| :--- | :--- | :--- | :--- |
| `POST` | `/cita/add` | **Creacion de cita.** | `{  "fecha": "2024-11-11", "hora": "15:30:00", "tipoCita": "Peluqueria"}` |
| `GET` | `/citas` | **Lista todas las citas.** | |
| `GET` | `/cita/{id}` | **Busca una sola cita por ID.** | |
| `DELETE` | `/cita/delete/{id}` | **Elimina una cita por ID.** | |

---

### Asignacion de Citas (`/asignacioncita`)

| Método | Endpoint | Descripción | Ejemplo de JSON |
| :--- | :--- | :--- | :--- |
| `POST` | `/cita/add` | **Creacion de cita.** | `{  "fecha": "2024-11-11", "hora": "15:30:00", "tipoCita": "Peluqueria"}` |
| `GET` | `/citas` | **Lista todas las citas.** | |
| `GET` | `/cita/{id}` | **Busca una sola cita por ID.** | |
| `DELETE` | `/cita/delete/{id}` | **Elimina una cita por ID.** | |
