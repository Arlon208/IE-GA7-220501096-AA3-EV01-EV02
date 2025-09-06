package com.clientes.CRUD.dto;


import com.clientes.CRUD.entity.Cliente;
import com.clientes.CRUD.entity.Mascota;
import java.time.LocalDate;
import java.time.LocalTime;

public class CitaRequestDTO {
    private Cliente cliente;
    private Mascota mascota;
    private String servicio;
    private LocalDate fecha;
    private LocalTime hora;

    // Getters and Setters
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
  
    public Mascota getMascota() {
        return mascota;
    }
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    public String getServicio() {
        return servicio;
    }
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public LocalTime getHora() {
        return hora;
    }
    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}