<h1><em> Proyecto CRUD Back-end Java + Springboot Framework </em></h1><br>
<p>Este proyecto fue desarrollado en el Lenguaje JAVA utilizando el framework SpringBoot realizando el modulo clientes, es un CRUD con conexion JPA a base de datos MYSQL, por tanto
cuenta con las funciones de Crear, Leer, Modificar y Eliminar, Se realizo con el IDE Netbeans version 19, JDK Java 21, La base de datos esta realizada en MYSQL ejecutada desde MYSQL WORKBENCH, en los archivos del proyectos encontrara la 
base de datos llamada "cliente.sql" a fin de importala y puedan ejecutar el programa.</p>

<p>Si desea realizar pruebas puede utilizar POSTMAN con el fin de conectarse a la API, las URL de mapeo son:</p>
<ul>
<li>Añadir Usuario: localhost:8080/cliente/add  
  Debe entregarle un JSON con los datos Requeridos Ejemplo
  {
    "idCliente": 1111111,
    "nombre": "Roberto",
    "ciudad": "Cartagena",
    "direccion": "Calle 8c",
    "telefono": "321888795"
} 
</li>
<li>Modificar Cliente: localhost:8080/cliente/update/ID (Para la modificacion debe settear los datos a cambiar dentro del metodo y luego ejecutar la orden en postman) </li>
<li>Listar Cliente: localhost:8080/clientes (Trae una lista de todos lo cliente en la base de datos)</li>  
<li>Eliminar Cliente: localhost:8080/cliente/delete/ID (Reemplazar el ID por la cedula del cliente a borrar</li>
</ul>

<h3>Dependencias Spring Usadas</h3>
<ul>
  <li>Spring Web</li>
  <li>Spring Data JPAL </li>
  <li>Spring Boot DevTools</li>
  <li>MySQL Driver </li>
</ul>

<h3>Tecnologias Usadas</h3>
<ul>
  <li>Java + SpringBoot</li>
  <li>JDBC + SQL </li>
</ul>
