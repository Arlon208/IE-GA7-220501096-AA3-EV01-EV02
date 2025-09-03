/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author Arlon
 */
@Entity
public class AsignacionCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asignacion;
    
   @ManyToOne
   @JoinColumn(name = "idcita",  nullable = false)
    private Cita cita;
   
   @ManyToOne
   @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
   @ManyToOne
   @JoinColumn(name = "id_mascota",  nullable = false)
    private Mascota mascota;

    public AsignacionCita(Integer id_asignacion, Cita cita, Cliente cliente, Mascota mascota) {
        this.id_asignacion = id_asignacion;
        this.cita = cita;
        this.cliente = cliente;
        this.mascota = mascota;
    }

    public AsignacionCita() {
    }

    public Integer getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(Integer id_asignacion) {
        this.id_asignacion = id_asignacion;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public String toString() {
        return "AsignacionCita{" + "id_asignacion=" + id_asignacion + ", cita=" + cita + ", cliente=" + cliente + ", mascota=" + mascota + '}';
    }
    
   
}
