/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.Cliente;
import com.clientes.CRUD.entity.Mascota;
import com.clientes.CRUD.exceptions.ResourceNotFoundException;
import com.clientes.CRUD.repository.ClienteRepository;
import com.clientes.CRUD.repository.MascotaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
public class MascotaController {
    @Autowired
    MascotaRepository repo;
    @Autowired
    ClienteRepository repoc;
    
    //Obtener Lista de Mascotas
    //Url localhost:8080/mascotas
    @GetMapping("/mascotas") //Asignacion url para obtener el listado de mascotas
    public List<Mascota> getAllMascotas(){
        List<Mascota> mascotas = repo.findAll();
        return mascotas;
    }
    //Buscar mascota por Id
    @GetMapping("/mascotas/{id}")
    public Mascota getMascota(@PathVariable Integer id) {
        Mascota mascota = repo.findById(id).get();
        
        return mascota;
}
    //Crear Mascota
     @PostMapping(path = "/mascotas/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createMascota(@RequestBody Mascota mascota) {
        repo.save(mascota);
    }
    
    //Buscar mascotas asociadas a un determinado cliente
    @GetMapping("/cliente/{idCliente}/mascotas")
    public List<Mascota> getMascotasByClienteId(@PathVariable Long idCliente){
        return repo.findByClienteIdCliente(idCliente);
    }
    //Modificar Mascota PUT request
    @PutMapping("/mascotas/update/{id}")
    public Mascota updateMascota(@PathVariable Integer id,@RequestBody Mascota mascotaActualizada){
        //Optional permite guardar o no un valor, por tanto se le pasa la busqueda
        //si esta esta vacia es por que el id buscado no existe en la DB
        Optional<Mascota> mascotaExistenteOptional = repo.findById(id);
        
        if (mascotaExistenteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Mascota no encontrado con ID: " + id);
        }
        Mascota mascotaExistente = mascotaExistenteOptional.get();
        
        if(mascotaActualizada.getNombre() !=null){
            mascotaExistente.setNombre(mascotaActualizada.getNombre());
        }
        if(mascotaActualizada.getEspecie() !=null){
            mascotaExistente.setEspecie(mascotaActualizada.getEspecie());
        }
        if(mascotaActualizada.getEdad() !=null){
            mascotaExistente.setEdad(mascotaActualizada.getEdad());
        }
        
         if(mascotaActualizada.getGenero() !=null){
            mascotaExistente.setGenero(mascotaActualizada.getGenero());
        }
          //Cambio de cliente para una mascota
          //rervision si se hace cambio del cliente en el JSON
        if (mascotaActualizada.getCliente() != null) {
            Long nuevoClienteId = mascotaActualizada.getCliente().getIdCliente();
            
            // revision si el nuevo cliente usado existe antes de hacer el cambio
            Cliente nuevoCliente = repoc.findById(nuevoClienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + nuevoClienteId));
            
            // Asignacion del nuevo dueño
            mascotaExistente.setCliente(nuevoCliente);
        }
        
        // Guardar cambios en mascota
        repo.save(mascotaExistente);
        
        return mascotaExistente;
    }
     //Borrar Mascota
    //URL localhost:8080/mascota/delete/{id}
    @DeleteMapping("/mascotas/delete/{id}")
    public void removeMascota(@PathVariable Integer id) {
        Mascota mascota = repo.findById(id).get();
        repo.delete(mascota);
        
    }
    
}
