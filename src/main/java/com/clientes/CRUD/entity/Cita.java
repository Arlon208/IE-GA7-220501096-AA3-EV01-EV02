/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Arlon
 */
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcita;
    private LocalDate fecha;
    private LocalTime hora;
    private String tipoCita;

    public Cita(Integer idcita, LocalDate fecha, LocalTime hora, String tipoCita) {
        this.idcita = idcita;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoCita = tipoCita;
    }

    public Cita() {
    }

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    @Override
    public String toString() {
        return "Cita{" + "idcita=" + idcita + ", fecha=" + fecha + ", hora=" + hora + ", tipoCita=" + tipoCita + '}';
    }
    
    
}
