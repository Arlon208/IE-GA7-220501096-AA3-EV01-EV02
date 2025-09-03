/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clientes.CRUD.repository;

import com.clientes.CRUD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Arlon
 */
public interface UserRepository extends JpaRepository<User, Integer> {
      User findByUsername(String username);
    
}
