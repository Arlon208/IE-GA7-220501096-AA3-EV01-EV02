/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

/**
 *
 * @author Arlon
 */
@Entity
public class HojaClinica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer idHojaClinica;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_mascota")
     private Mascota mascota;
     private String motivo;
     private String examenes;
     private String medicamentos;
     private LocalDate fecha;

    public HojaClinica(Integer idHojaClinica, Mascota mascota, String motivo, String examenes, String medicamentos, LocalDate fecha) {
        this.idHojaClinica = idHojaClinica;
        this.mascota = mascota;
        this.motivo = motivo;
        this.examenes = examenes;
        this.medicamentos = medicamentos;
        this.fecha = fecha;
    }

    public HojaClinica() {
    }

    public Integer getIdHojaClinica() {
        return idHojaClinica;
    }

    public void setIdHojaClinica(Integer IdHojaClinica) {
        this.idHojaClinica = IdHojaClinica;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getExamenes() {
        return examenes;
    }

    public void setExamenes(String examenes) {
        this.examenes = examenes;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "HojaClinica{" + "idHojaClinica=" + idHojaClinica + ", mascota=" + mascota + ", motivo=" + motivo + ", examenes=" + examenes + ", medicamentos=" + medicamentos + ", fecha=" + fecha + '}';
    }
     
     
}
