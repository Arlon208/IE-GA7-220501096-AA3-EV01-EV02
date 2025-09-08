package com.clientes.CRUD.service;


import com.clientes.CRUD.dto.CitaRequestDTO;
import com.clientes.CRUD.entity.AsignacionCita;
import com.clientes.CRUD.entity.Cita;
import com.clientes.CRUD.repository.AsignacionCitaRepository;
import com.clientes.CRUD.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repoc;

     @Autowired
    private AsignacionCitaRepository repoa;

    @Transactional
    public void guardarCitaYAsignacion(CitaRequestDTO citaRequest) {
        // 
        // Validacion que evita dos citas a una misma hora
        if (repoc.existsByFechaAndHora(citaRequest.getFecha(), citaRequest.getHora())) {
            throw new IllegalArgumentException("La fecha y hora elegidas ya están ocupadas.");
        }

        // Creacion de cita con sus campos
        // 
        Cita nuevaCita = new Cita();
        nuevaCita.setFecha(citaRequest.getFecha());
        nuevaCita.setHora(citaRequest.getHora());
        nuevaCita.setTipoCita(citaRequest.getServicio());
        
        Cita savedCita = repoc.save(nuevaCita);

        // Se crea la asignacion de cita con sus parametros
        // 
        AsignacionCita asignacion = new AsignacionCita();
        asignacion.setCita(savedCita);
        asignacion.setCliente(citaRequest.getCliente());
        asignacion.setMascota(citaRequest.getMascota());
        
        repoa.save(asignacion);
    }
}