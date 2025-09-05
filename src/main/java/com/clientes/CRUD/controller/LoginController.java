/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.User;
import com.clientes.CRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arlon
 */
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class LoginController {
    
    @Autowired
    UserRepository repo;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        User usuarioExistente = repo.findByUsername(user.getUsername());
        
        if(usuarioExistente != null && usuarioExistente.getPassword().equals(user.getPassword())){
            return new ResponseEntity<>("Login Exitoso!!", HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>("Usuario o contraseña incorrectos",HttpStatus.UNAUTHORIZED);
        }
    }
    
}
