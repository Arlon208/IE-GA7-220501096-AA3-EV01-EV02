/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.Cita;
import com.clientes.CRUD.exceptions.ResourceNotFoundException;
import com.clientes.CRUD.repository.CitaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Arlon
 */
@RestController 
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class CitaController {
    //Llamador al repository
    @Autowired
    CitaRepository repo;
    
    //Obtener Citas
    @GetMapping("/citas")
    public List<Cita> getAllCitas(){
        List<Cita> citas = repo.findAll();
        return citas;
    }
    
    //Obtener Citas por id
    @GetMapping("/cita/{id}")
    public Cita getCita(@PathVariable Integer id){
        Cita cita = repo.findById(id).get();
        
        return cita;
    }
    
    //Crear cita
    @PostMapping("/cita/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCita(@RequestBody Cita cita){
        repo.save(cita);
    }
    //Modificar Cita
    @PutMapping("/cita/update/{id}")
    public Cita updateCita(@PathVariable Integer id,@RequestBody Cita citaActualizada){
        //Optional permite guardar o no un valor, por tanto se le pasa la busqueda
        //si esta esta vacia es por que el id buscado no existe en la DB
        Optional<Cita> citaExistenteOptional = repo.findById(id);
        
        if (citaExistenteOptional.isEmpty()){
            throw new ResourceNotFoundException("Cita no encontrada con ID: " + id);
        }
        Cita citaExistente = citaExistenteOptional.get();
   
        if(citaActualizada.getFecha() != null){
            citaExistente.setFecha(citaActualizada.getFecha());
        }
        
        if(citaActualizada.getHora() != null){
            citaExistente.setHora(citaActualizada.getHora());
        }
        
        if(citaActualizada.getTipoCita() != null){
            citaExistente.setTipoCita(citaActualizada.getTipoCita());
        }
        
        repo.save(citaExistente);
        return citaExistente;
     }
    
    //Borrar Cita
    @DeleteMapping("/cita/delete/{id}")
    public void removeCita (@PathVariable Integer id){
        Cita cita = repo.findById(id).get();
        repo.delete(cita);
    }
}