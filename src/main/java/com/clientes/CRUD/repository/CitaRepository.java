/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clientes.CRUD.repository;

import com.clientes.CRUD.entity.Cita;
import java.time.LocalTime;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arlon
 */
public interface CitaRepository extends JpaRepository<Cita, Integer>{
     boolean existsByFechaAndHora(LocalDate fecha, LocalTime hora);
}
