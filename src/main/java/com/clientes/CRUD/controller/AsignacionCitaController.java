/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.AsignacionCita;
import com.clientes.CRUD.entity.Cita;
import com.clientes.CRUD.entity.Cliente;
import com.clientes.CRUD.entity.Mascota;
import com.clientes.CRUD.exceptions.ResourceNotFoundException;
import com.clientes.CRUD.repository.AsignacionCitaRepository;
import com.clientes.CRUD.repository.CitaRepository;
import com.clientes.CRUD.repository.ClienteRepository;
import com.clientes.CRUD.repository.MascotaRepository;
import java.util.List;
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
public class AsignacionCitaController {
    @Autowired
    AsignacionCitaRepository repo;
    
    @Autowired
    MascotaRepository repom;
    
    @Autowired
    ClienteRepository repoc;
    
    @Autowired
    CitaRepository repocita;
    
    //Obtener todas las asignaciones
    //URL localhost:8080/asignacioncitas
    @GetMapping("/asignacioncitas")
    public List<AsignacionCita> getAllAsignacion(){
        List<AsignacionCita> asignacion = repo.findAll();
        return asignacion;
    }
    //Buscar por Id
    @GetMapping("/asignacioncita/{id}")
    public AsignacionCita getAsignacion(@PathVariable Integer id){
        return repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Asignación de Cita no encontrada con ID: " + id));
    }
    //Crear Asignacion
    @PostMapping("/asignacioncita/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AsignacionCita createAsignacion(@RequestBody AsignacionCita asignacion){
        return repo.save(asignacion);
    }
    
    //Modificar Asignacion
    @PutMapping("/asignacioncita/update/{id}")
    public AsignacionCita updateAsignacion(@PathVariable Integer id, @RequestBody AsignacionCita asignacionActualizada){
        //comprobacion si el id a modificar existe
        AsignacionCita asignacionExistente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignacion de Cita no encontrada con ID: " + id));
        
        //Actualizacion de campos
        //Actualizacion de cita
        if(asignacionActualizada.getCita() != null && asignacionActualizada.getCita().getIdcita() != null){
            Cita citaExistente = repocita.findById(asignacionActualizada.getCita().getIdcita())
                    .orElseThrow(() -> new ResourceNotFoundException("cita no encontrada con el ID: " + asignacionActualizada.getCita().getIdcita()));
            asignacionExistente.setCita(citaExistente);
        }
       //Actualizacion Cliente
       if(asignacionActualizada.getCliente() != null && asignacionActualizada.getCliente().getIdCliente() != null){
           Cliente clienteExistente = repoc.findById(asignacionActualizada.getCliente().getIdCliente())
                   .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + asignacionActualizada.getCliente().getIdCliente()));
           asignacionExistente.setCliente(clienteExistente);
       }
       //Actualizacion Mascota
       if(asignacionActualizada.getMascota() != null && asignacionActualizada.getMascota().getIdMascota() != null){
           Mascota mascotaExistente = repom.findById(asignacionActualizada.getMascota().getIdMascota())
                   .orElseThrow(() -> new ResourceNotFoundException("Macota No encontrada con el ID: " + asignacionActualizada.getMascota().getIdMascota()));
           asignacionExistente.setMascota(mascotaExistente);
           
       }
       //Guarda la asignacion actualizadada
       return repo.save(asignacionExistente);
       
    }
    
    //Eliminar Asignacion
    @DeleteMapping("/asignacioncitas/delete/{id}")
    public void removeAsignacion(@PathVariable Integer id){
        //En caso que no exista el id brindado se maneja el erro  
        repo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Asignación de Cita no encontrada con ID: " + id));

  // en caso que exista el ID se elimina
    repo.deleteById(id);
    }
}
      
        
   
    
