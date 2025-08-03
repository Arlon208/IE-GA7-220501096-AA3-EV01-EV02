package com.clientes.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author artur
 */

//Clase cliente entidad principal que nos permite instanciar objetos de tipo cliente

@Entity //Esta anotacion permite indicar que la clase es una entidad a interactuar en la BD
public class Cliente {
    @Id 
    private Long idCliente;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;

    //Constructores
    public Cliente(Long idCliente, String nombre, String ciudad, String direccion, String telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente() {
    }
    
    

    //Setter y Getter (Para asignar y obtener datos de la clase cliente)
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    
}
