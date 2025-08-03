/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clientes.CRUD.repository;

import com.clientes.CRUD.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author artur
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
