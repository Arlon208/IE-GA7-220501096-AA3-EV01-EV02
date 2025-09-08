document.addEventListener('DOMContentLoaded', function () {
    const tableBody = document.getElementById('client-table-body');
    const searchForm = document.getElementById('search-form');
    const fechaFiltroInput = document.getElementById('fecha-filtro');
    const baseURL = 'http://localhost:8080';

    let allAsignaciones = []; // This will store all fetched appointments

    // Function to fetch all appointments from the API
    async function fetchAllAsignaciones() {
        try {
            const response = await fetch(`${baseURL}/asignacioncitas`);
            if (response.ok) {
                allAsignaciones = await response.json();
                renderTable(allAsignaciones); // Render all appointments initially
            } else {
                alert('Error al cargar las citas.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Function to render the table with filtered or all data
    function renderTable(asignaciones) {
        tableBody.innerHTML = ''; // Clear the table first

        if (asignaciones.length === 0) {
            tableBody.innerHTML = '<tr><td colspan="6">No se encontraron citas.</td></tr>';
            return;
        }

        asignaciones.forEach(asignacion => {
            const row = document.createElement('tr');
            
            // Add a null-check for each property you're trying to access
            const clienteNombre = asignacion.cliente ? asignacion.cliente.nombre : 'N/A';
            const mascotaNombre = asignacion.mascota ? asignacion.mascota.nombre : 'N/A';
            const servicio = (asignacion.cita && asignacion.cita.tipoCita) ? asignacion.cita.tipoCita : 'N/A';
            const fecha = asignacion.cita ? asignacion.cita.fecha : 'N/A';
            const hora = asignacion.cita ? asignacion.cita.hora : 'N/A';
            const id = asignacion.id_asignacion || '';

            row.innerHTML = `
                <td>${clienteNombre}</td>
                <td>${mascotaNombre}</td>
                <td>${servicio}</td>
                <td>${fecha}</td>
                <td>${hora}</td>
                <td>
                    <button class="btn-eliminar" data-id="${asignacion.id_asignacion}">Eliminar</button>
                    </td>
            `;
            tableBody.appendChild(row);
        });
    }

    async function deleteAsignacion(id) {
               
        try {
            const response = await fetch(`${baseURL}/asignacioncitas/delete/${id}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Cita eliminada correctamente.');
                // Re-fetch and re-render the list after deletion
                fetchAllAsignaciones();
            } else {
                const error = await response.text();
                alert('Error al eliminar la cita: ' + error);
                //console.log(error);
                console.log(id);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Add a click event listener to the table body using event delegation
    tableBody.addEventListener('click', function(event) {
        if (event.target.classList.contains('btn-eliminar')) {
            const id = event.target.getAttribute('data-id');
            deleteAsignacion(id);
        }
    });

    // Event listener for the search form submission
    searchForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const fecha = fechaFiltroInput.value;

        if (fecha) {
            // Filter the local data if a date is selected
            const filteredAsignaciones = allAsignaciones.filter(asignacion => {
                // Assuming the date field in AsignacionCita's 'cita' object is a simple string like "YYYY-MM-DD"
                return asignacion.cita.fecha === fecha;
            });
            renderTable(filteredAsignaciones);
        } else {
            // If the date field is empty, render all data
            renderTable(allAsignaciones);
        }
    });

    // Initial call to load all appointments from the API
    fetchAllAsignaciones();
});

