/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.HojaClinica;
import com.clientes.CRUD.entity.Mascota;
import com.clientes.CRUD.exceptions.ResourceNotFoundException;
import com.clientes.CRUD.repository.HojaClinicaRepository;
import com.clientes.CRUD.repository.MascotaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class HojaClinicaController {
    @Autowired
    HojaClinicaRepository repo;
    @Autowired
    MascotaRepository repom;
    
    //Obtener Lista de Hojas Clinicas
    //Url localhost:8080/hojasclinicas
    @GetMapping("/hojasclinicas") //Asignacion url para obtener el listado de HC
    public List<HojaClinica> getAllHojaClinica(){
        List<HojaClinica> hojaclinica = repo.findAll();
        return hojaclinica;
    }
    //Buscar hojaclinica por Id
    @GetMapping("/hojaclinica/{id}")
    public HojaClinica getHojaClinica(@PathVariable Integer id) {
        HojaClinica hojaclinica = repo.findById(id).get();
        
        return hojaclinica;
}
    //Crear hojaclinica
    @PostMapping("/hojaclinica/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public HojaClinica createHojaClinica(@RequestBody HojaClinica hojaClinica){
        return repo.save(hojaClinica);
    }
    
    //Buscar HojaClinica asociadas a una determinado Mascota
    @GetMapping("/mascotas/{idMascota}/hojasClinicas")
    public List<HojaClinica> getHojaClinicaByMascotaId(@PathVariable Integer idMascota){
        return repo.findByMascotaIdMascota(idMascota);
    }
     //Modificar HojaClinica PUT request
    @PutMapping("/hojaclinica/update/{id}")
    public HojaClinica updateHojaClinica(@PathVariable Integer id,@RequestBody HojaClinica hCActualizada){
        //Optional permite guardar o no un valor, por tanto se le pasa la busqueda
        //si esta esta vacia es por que el id buscado no existe en la DB
        Optional<HojaClinica> hCExistenteOptional = repo.findById(id);
        
        if (hCExistenteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Hoja Clinica no encontrado con ID: " + id);
        }
        HojaClinica hCExistente = hCExistenteOptional.get();
        
        if(hCActualizada.getMotivo() !=null){
            hCExistente.setMotivo(hCActualizada.getMotivo());
        }
        if(hCActualizada.getExamenes() !=null){
            hCExistente.setExamenes(hCActualizada.getExamenes());
        }
        if(hCActualizada.getMedicamentos() !=null){
            hCExistente.setMedicamentos(hCActualizada.getMedicamentos());
        }
        
         if(hCActualizada.getFecha() !=null){
            hCExistente.setFecha(hCActualizada.getFecha());
        }
          //Cambio de Hoja clinica para una mascota
          //revision si se hace cambio de la mascota en el JSON
        if (hCActualizada.getMascota() != null) {
            Integer nuevaMascotaId = hCActualizada.getMascota().getIdMascota();
            
            // revision si la nueva Mascota existe antes de hacer el cambio
            Mascota nuevaMascota = repom.findById(nuevaMascotaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrado con ID: " + nuevaMascotaId));
            
            // Asignacion del nuevo dueño
            hCExistente.setMascota(nuevaMascota);
        }
        
        // Guardar cambios en mascota
        repo.save(hCExistente);
        
        return hCExistente;
        
     }
     //Borrar hojaclinica
    //URL localhost:8080/hojaclinica/delete/{id}
    @DeleteMapping("/hojaclinica/delete/{id}")
    public void removeHojaClinica(@PathVariable Integer id) {
        HojaClinica hojaclinica = repo.findById(id).get();
        repo.delete(hojaclinica);
        
    }
}
