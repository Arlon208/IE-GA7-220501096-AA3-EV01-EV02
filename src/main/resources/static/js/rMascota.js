document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const clienteSelect = document.getElementById('clienteSelect');
    const nombreMascotaInput = document.getElementById('nMascota');
    const especieInput = document.getElementById('especie');
    const edadInput = document.getElementById('edad');
    const generoInput = document.getElementById('genero');
    const cancelarBtn = document.getElementById('cancelar-btn');

    // Funcion fecth que se encarga de cargar los cliente en un listado select para 
    //elegir el dueño de la mascota esto evita fallos en la bd
    async function fetchClients() {
        try {
            const response = await fetch('http://localhost:8080/clientes');
            if (response.ok) {
                const clientes = await response.json();
                
                // limpia el select
                clienteSelect.innerHTML = ''; 

                // Opcion vacia del select
                const defaultOption = document.createElement('option');
                defaultOption.value = '';
                defaultOption.textContent = 'Seleccione un cliente';
                clienteSelect.appendChild(defaultOption);
                //Cargue de los clientes existentes en la BD
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

    // Carga los cliente cuando la pagina esta lista
    fetchClients();

    // Registro de mascota
    form.addEventListener('submit', async function (event) {
        event.preventDefault(); 
        //toma los valores del formulario
        const clienteId = clienteSelect.value;
        const nombreMascota = nombreMascotaInput.value.trim();
        const especie = especieInput.value.trim();
        const edad = edadInput.value.trim();
        const genero = generoInput.value.trim();
        //Comprueba que todo este diligenciado
        if (!clienteId || !nombreMascota || !especie || !edad || !genero) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        const mascotaData = {
            cliente: {
                idCliente: clienteId
            },
            nombre: nombreMascota,
            especie: especie,
            edad: edad,
            genero: genero
        };

        try {
            const response = await fetch('http://localhost:8080/mascotas/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(mascotaData)
            });

            if (response.ok) {
                alert('Mascota registrada correctamente.');
                form.reset();
            } else {
                const error = await response.text();
                alert('Error al registrar mascota: ' + error);
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