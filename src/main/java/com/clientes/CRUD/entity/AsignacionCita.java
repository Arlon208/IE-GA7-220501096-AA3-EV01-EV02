/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Arlon
 */
@Entity
public class AsignacionCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asignacion;
    
   
    private Cita cita;
    private Cliente cliente;
    private Mascota mascota;
    
}
