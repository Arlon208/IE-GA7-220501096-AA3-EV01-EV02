document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const clienteSelect = document.getElementById('clienteSelect');
    const mascotaSelect = document.getElementById('mascotaSelect');
    const especieInput = document.getElementById('especie');
    const servicioInput = document.getElementById('servicio');
    const fechaInput = document.getElementById('fecha');
    const horaInput = document.getElementById('hora');
    const modal = document.getElementById('alerta-cruce');
    const cerrarModalBtn = document.getElementById('cerrar-modal');
    const cancelarBtn = document.getElementById('cancelar-btn');
    const baseURL = 'http://localhost:8080';

    let allMascotas = [];

    cerrarModalBtn.addEventListener('click', function () {
    modal.classList.add("ocultar");
    setTimeout(() => {
        modal.classList.remove("mostrar", "ocultar");
        modal.style.display = "none";
    }, 500);
});
    // Llena el select con los clientes de la bd
    async function fetchClients() {
        try {
            const response = await fetch(`${baseURL}/clientes`);
            if (response.ok) {
                const clientes = await response.json();
                clienteSelect.innerHTML = '';
                const defaultOption = document.createElement('option');
                defaultOption.value = '';
                defaultOption.textContent = 'Seleccione un cliente';
                clienteSelect.appendChild(defaultOption);

                clientes.forEach(cliente => {
                    const option = document.createElement('option');
                    option.value = cliente.idCliente;
                    option.textContent = `${cliente.idCliente} - ${cliente.nombre}`;
                    clienteSelect.appendChild(option);
                });
            } else {
                alert('Error al cargar clientes.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // consulta de mascota pertenecientes al cliente
    async function fetchMascotasByClienteId(clienteId) {
        try {
            const response = await fetch(`${baseURL}/cliente/${clienteId}/mascotas`);
            if (response.ok) {
                const mascotas = await response.json();
                allMascotas = mascotas; // Guarda las mascotas del cliente en la lista
                mascotaSelect.innerHTML = '';
                const defaultOption = document.createElement('option');
                defaultOption.value = '';
                defaultOption.textContent = 'Seleccione una mascota';
                mascotaSelect.appendChild(defaultOption);

                mascotas.forEach(mascota => {
                    const option = document.createElement('option');
                    option.value = mascota.idMascota;
                    option.textContent = mascota.nombre;
                    mascotaSelect.appendChild(option);
                });
            } else {
                mascotaSelect.innerHTML = '<option value="">No hay mascotas para este cliente</option>';
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // carga las mascota del cliente elegido
    clienteSelect.addEventListener('change', function () {
        const clienteId = clienteSelect.value;
        if (clienteId) {
            fetchMascotasByClienteId(clienteId);
        } else {
            mascotaSelect.innerHTML = '<option value="">Seleccione un cliente primero</option>';
            especieInput.value = '';
        }
    });

    // Llena el campo especie con el de la mascota elegida
    mascotaSelect.addEventListener('change', function () {
        const mascotaId = mascotaSelect.value;
        if (mascotaId) {
            const selectedMascota = allMascotas.find(m => m.idMascota == mascotaId);
            if (selectedMascota) {
                especieInput.value = selectedMascota.especie;
            }
        } else {
            especieInput.value = '';
        }
    });

    //toma los valores del formulario quita los espacios y compureba que esten completos
    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const clienteId = clienteSelect.value;
        const mascotaId = mascotaSelect.value;
        const servicio = servicioInput.value.trim();
        const fecha = fechaInput.value.trim();
        const hora = horaInput.value.trim();

        if (!clienteId || !mascotaId || !servicio || !fecha || !hora) {
            alert('Por favor, complete todos los campos obligatorios.');
            return;
        }

        const citaData = {
            cliente: { idCliente: clienteId },
            mascota: { idMascota: mascotaId },
            servicio: servicio,
            fecha: fecha,
            hora: hora
        };

        try {
            const response = await fetch(`${baseURL}/cita/agendar`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(citaData)
            });

            if (response.ok) {
                alert('Cita agendada y asignación creada correctamente.');
                form.reset();
                mascotaSelect.innerHTML = '<option value="">Seleccione un cliente primero</option>';
                especieInput.value = '';
            } else if (response.status === 409) {
                //modal que avisa sobre cruce de fechas
                 modal.classList.add("mostrar");
                 modal.classList.remove("ocultar");
                 modal.style.display = "flex";

                 
            } else {
                const error = await response.text();
                alert('Error al agendar la cita: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    });

    // boton cancelar
    cancelarBtn.addEventListener('click', function () {
        window.history.back();
    });

    cerrarModalBtn.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    fetchClients();
});