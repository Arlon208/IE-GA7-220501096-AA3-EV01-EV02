package com.clientes.CRUD.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author artur
 */

//Clase cliente entidad principal que nos permite instanciar objetos de tipo cliente

@Entity //Esta anotacion permite indicar que la clase es una entidad a interactuar en la BD
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCliente")
public class Cliente {
    @Id 
    private Long idCliente;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;

    //@JsonManagedReference("mascota-cliente")
     @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;
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

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", ciudad=" + ciudad + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    
}
