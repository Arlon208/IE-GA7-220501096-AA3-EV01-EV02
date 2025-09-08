document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const clienteSelect = document.getElementById('clienteSelect');
    const nombreMascotaInput = document.getElementById('nMascota');
    const especieInput = document.getElementById('especie');
    const edadInput = document.getElementById('edad');
    const generoInput = document.getElementById('genero');
    const cancelarBtn = document.getElementById('cancelar-btn');
    const baseURL = 'http://localhost:8080/mascotas';

    // Obtiene el id que se envio en la URL
    const urlParams = new URLSearchParams(window.location.search);
    const mascotaId = urlParams.get('idMascota');

    if (!mascotaId) {
        alert('ID de mascota no encontrado en la URL. Volviendo a la página de búsqueda.');
        window.history.back();
        return;
    }

    // Carga de clientes de la bd en el select
    async function fetchClients() {
        try {
            const response = await fetch('http://localhost:8080/clientes');
            if (response.ok) {
                const clientes = await response.json();
                clienteSelect.innerHTML = '';
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
    
    //Carga los datos en las variables correspondientes
    async function fetchMascotaData(id) {
        try {
            const response = await fetch(`${baseURL}/${id}`);
            if (response.ok) {
                const mascota = await response.json();

        
                nombreMascotaInput.value = mascota.nombre;
                especieInput.value = mascota.especie;
                edadInput.value = mascota.edad;
                generoInput.value = mascota.genero;

                if (mascota.cliente) {
                    clienteSelect.value = mascota.cliente.idCliente;
                } else {
                    console.warn('Advertencia: El cliente para esta mascota no está disponible.');
                }

                
            } else if (response.status === 404) {
                alert('Mascota no encontrada.');
                window.history.back();
            } else {
                alert('Error al obtener los datos de la mascota.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }


    fetchClients().then(() => {
        fetchMascotaData(mascotaId);
    });


    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const clienteId = clienteSelect.value;
        const nombreMascota = nombreMascotaInput.value.trim();
        const especie = especieInput.value.trim();
        const edad = edadInput.value.trim();
        const genero = generoInput.value.trim();

        if (!clienteId || !nombreMascota || !especie || !edad || !genero) {
            alert('Por favor, complete todos los campos.');
            return;
        }
        //Envio de datos en JSON
        const mascotaData = {
            idMascota: mascotaId,
            cliente: {
                idCliente: clienteId
            },
            nombre: nombreMascota,
            especie: especie,
            edad: edad,
            genero: genero
        };

        try {
            // Send a PUT request to your API to update the pet
            const response = await fetch(`${baseURL}/update/${mascotaId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(mascotaData)
            });

            if (response.ok) {
                alert('Mascota actualizada correctamente.');
                // Redirect back to the search page or a success page
                window.history.back();
            } else {
                const error = await response.text();
                alert('Error al actualizar mascota: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    });

    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', function () {
            window.history.back();
        });
    }
});