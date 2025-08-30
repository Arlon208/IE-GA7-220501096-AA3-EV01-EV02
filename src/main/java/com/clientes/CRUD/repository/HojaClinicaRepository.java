/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clientes.CRUD.repository;

import com.clientes.CRUD.entity.HojaClinica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arlon
 */
public interface HojaClinicaRepository extends JpaRepository<HojaClinica, Integer>{
    //Controlador para buscar lista de HojasClinicas asociadas a una Mascota
    List<HojaClinica> findByMascotaIdMascota(Integer id_mascota);
}
