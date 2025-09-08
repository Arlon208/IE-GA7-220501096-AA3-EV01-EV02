document.addEventListener('DOMContentLoaded', function () {
    const searchForm = document.getElementById('search-form');
    const mascotaTableBody = document.getElementById('mascota-table-body');
    const baseURL = 'http://localhost:8080';

    // Función para obtener y mostrar las mascotas de un cliente por su ID
    async function getMascotasByClienteId(clienteId) {
        try {
            const response = await fetch(`${baseURL}/cliente/${clienteId}/mascotas`);

            if (response.ok) {
                const mascotas = await response.json();
                displayMascotasInTable(mascotas);
            } else if (response.status === 404) {
                alert('No se encontraron mascotas para el cliente con ID: ' + clienteId);
                mascotaTableBody.innerHTML = '';
            } else {
                const error = await response.text();
                alert('Error al buscar mascotas: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Función para poblar la tabla con los datos de las mascotas
    function displayMascotasInTable(mascotas) {
        mascotaTableBody.innerHTML = '';
        if (mascotas.length === 0) {
            mascotaTableBody.innerHTML = '<tr><td colspan="6">Este cliente no tiene mascotas registradas.</td></tr>';
            return;
        }

        mascotas.forEach(mascota => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${mascota.idMascota}</td>
                <td>${mascota.nombre}</td>
                <td>${mascota.especie}</td>
                <td>${mascota.edad}</td>
                <td>${mascota.genero || 'N/A'}</td>
                <td>
                    <button class="btn-editar" data-id="${mascota.idMascota}">Editar</button>
                    <button class="btn-eliminar" data-id="${mascota.idMascota}">Eliminar</button>
                    <button class="btn-hoja-clinica" data-id="${mascota.idMascota}">Crear Hoja Clínica</button>
                    <button class="btn-ver-historias" data-id="${mascota.idMascota}">Ver Hojas Clínicas</button>
                </td>
            `;
            mascotaTableBody.appendChild(row);


            row.querySelector('.btn-eliminar').addEventListener('click', () => {
                if (confirm('¿Está seguro de que desea eliminar esta mascota?')) {
                    deleteMascota(mascota.idMascota);
                }
            });

            row.querySelector('.btn-editar').addEventListener('click', () => {
                window.location.href = `modificarMascota.html?idMascota=${mascota.idMascota}`;
            });

            row.querySelector('.btn-hoja-clinica').addEventListener('click', () => {
                window.location.href = `crearHojaClinica.html?idMascota=${mascota.idMascota}&nombreMascota=${mascota.nombre}`;
            });

            row.querySelector('.btn-ver-historias').addEventListener('click', () => {
                window.location.href = `listadoHojasClinicas.html?idMascota=${mascota.idMascota}&nombreMascota=${mascota.nombre}`;
            });
        });
    }

    // Función para manejar la eliminación de una mascota
    async function deleteMascota(id) {
        try {
            const response = await fetch(`${baseURL}/mascotas/delete/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Mascota eliminada correctamente.');
                const clienteId = document.getElementById('searchId').value;
                getMascotasByClienteId(clienteId);
            } else {
                const error = await response.text();
                alert('Error al eliminar mascota: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }

    // Evento de escucha para el formulario de búsqueda
    searchForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const searchId = document.getElementById('searchId').value;
        if (searchId) {
            getMascotasByClienteId(searchId);
        }
    });
});