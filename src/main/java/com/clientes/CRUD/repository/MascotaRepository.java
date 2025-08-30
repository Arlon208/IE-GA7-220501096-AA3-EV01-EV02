/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clientes.CRUD.repository;

import com.clientes.CRUD.entity.Cliente;
import com.clientes.CRUD.entity.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arlon
 */
public interface MascotaRepository  extends JpaRepository<Mascota, Integer>{
    //Controlador para buscar lista de mascotas asociadas a un cliente
    List<Mascota> findByClienteIdCliente(Long idCliente);
    
}
