/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author Arlon
 */
@Entity
public class Mascota {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMascota;
    @JsonBackReference
    @ManyToOne //Relacion de uno a muchos
    @JoinColumn(name = "id_cliente")//Clave foranea del cliente en la mascota
    private Cliente cliente;
    private String nombre;
    private String especie;
    private Integer edad;
    private String genero;
    
    @OneToMany(mappedBy = "mascota")
    @JsonManagedReference
    private List<HojaClinica> hojasClinica;
    
    public Mascota(Integer idMascota, Cliente cliente, String nombre, String especie, Integer edad, String genero) {
        this.idMascota = idMascota;
        this.cliente = cliente;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.genero = genero;
    }

    public Mascota() {
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
        public List<HojaClinica> getHojasClinica() {
        return hojasClinica;
    }

    public void setHojasClinica(List<HojaClinica> hojasClinica) {
        this.hojasClinica = hojasClinica;
    }

    @Override
    public String toString() {
        return "Mascota{" + "idMascota=" + idMascota + ", cliente=" + cliente + ", nombre=" + nombre + ", especie=" + especie + ", edad=" + edad + ", genero=" + genero + ", hojasClinica=" + hojasClinica + '}';
    }

    
}

