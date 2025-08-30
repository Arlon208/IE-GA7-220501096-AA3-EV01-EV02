package com.clientes.CRUD.controller;

import com.clientes.CRUD.entity.Cliente;
import com.clientes.CRUD.exceptions.ResourceNotFoundException;
import com.clientes.CRUD.repository.ClienteRepository;
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

@RestController //Define clases como un controldor RESTful es deicir enviar el cuerpo de respuesta HTTP
public class ClienteController {
    //Llamado al controlador
    @Autowired
    ClienteRepository repo;
    //Obtener lista de clientes
    //url localhost:8080/clientes
    @GetMapping("/clientes") //GetMapping permite el mapeo del metodo GET a la asignacion de una ruta(URL) para la respuesta que se solicita
    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = repo.findAll();
        return clientes;
    }
    
    //Obtener Cliente por cedula (id) GET Request
    //URL localhost:8080/clientes/id (se reemplaza el id por la cedula del cliente)
    @GetMapping("/clientes/{id}")
    public Cliente getCliente(@PathVariable Long id) {
        Cliente cliente = repo.findById(id).get();
        
        return cliente;
    }
     //Crear Cliente POST Request
    //URL localhost:8080/clientes/add 
    //Se debe enviar un desde postman un Json con la informacion del clienteo en su defecto desde el frontend elejido
    @PostMapping("/cliente/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCliente(@RequestBody Cliente cliente){
        repo.save(cliente);
    }
    
    //Modificar (Actualizar cliente) PUT Request
    //URL localhost:8080/cliente/update/{id}
    //@PathVariable, opermite extraer parametros de la URL y asociarlos al controller
    @PutMapping("/cliente/update/{id}") //PUT se utiliza para la modificacion de datos existentes
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado ){
        

        // Busca un cliente por ID 
        Optional<Cliente> clienteExistenteOptional = repo.findById(id);

        //revisa si la variable clienteExistenceOptional tiene datos en caso contraio indica que el cliente no existe
        if (clienteExistenteOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        
        Cliente clienteExistente = clienteExistenteOptional.get();

        //Verifica y actualiza solo los campos que no son nulos es decir lo que introdujo el cliente
        if (clienteActualizado.getNombre() != null) {
            clienteExistente.setNombre(clienteActualizado.getNombre());
        }
        
        if (clienteActualizado.getCiudad() != null) {
            clienteExistente.setCiudad(clienteActualizado.getCiudad());
        }
        if (clienteActualizado.getDireccion() != null) {
            clienteExistente.setDireccion(clienteActualizado.getDireccion());
        }
        if (clienteActualizado.getTelefono() != null) {
            clienteExistente.setTelefono(clienteActualizado.getTelefono());
        }

        //Guardar el cliente modificado con los campos especificados
        repo.save(clienteExistente);
        //Devuelve como quedo el cliente al finalizar la actualizacion
        return clienteExistente;
    }
        
    
    //Borrar Cliente
    //URL localhost:8080/cliente/delete/{id}
    @DeleteMapping("/cliente/delete/{id}")
    public void removeCliente(@PathVariable Long id) {
        Cliente cliente = repo.findById(id).get();
        repo.delete(cliente);
        
    }
}
