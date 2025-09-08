document.addEventListener('DOMContentLoaded', function () {
    const hojaTableBody = document.getElementById('hoja-table-body');
    const baseURL = 'http://localhost:8080';

    // Get the mascotaId from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const mascotaId = urlParams.get('idMascota');
    const mascotaNombre = urlParams.get('nombreMascota');

    if (!mascotaId) {
        alert('ID de mascota no encontrado en la URL. Regresando a la página anterior.');
        window.history.back();
        return;
    }

    // Update the page title to show the pet's name
    const pageTitle = document.querySelector('h2');
    if (pageTitle && mascotaNombre) {
        pageTitle.textContent = `Hojas Clínicas de ${mascotaNombre}`;
    }

    // Function to fetch and display the clinical records for a pet
    async function getHojasClinicasByMascotaId(id) {
        try {
            // Your API should have an endpoint like this: /historia/mascota/{id}
            const response = await fetch(`${baseURL}/mascotas/${id}/hojasClinicas`);
            
            if (response.ok) {
                const historias = await response.json();
                displayHojasClinicasInTable(historias);
            } else if (response.status === 404) {
                hojaTableBody.innerHTML = '<tr><td colspan="6">No hay hojas clínicas para esta mascota.</td></tr>';
            } else {
                const error = await response.text();
                alert('Error al buscar hojas clínicas: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Function to populate the table with the data
    function displayHojasClinicasInTable(historias) {
        hojaTableBody.innerHTML = '';
        if (historias.length === 0) {
            hojaTableBody.innerHTML = '<tr><td colspan="6">No hay hojas clínicas para esta mascota.</td></tr>';
            return;
        }

        historias.forEach(historia => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${mascotaId} - ${mascotaNombre}</td>
                <td>${historia.motivo}</td>
                <td>${historia.examenes || 'N/A'}</td>
                <td>${historia.medicamentos || 'N/A'}</td>
                <td>${historia.fecha}</td>
                <td>
                    <button class="btn-editar" data-id="${historia.idHojaClinica}">Editar</button>
                    <button class="btn-eliminar" data-id="${historia.idHojaClinica}">Eliminar</button>
                </td>
            `;
            hojaTableBody.appendChild(row);

            row.querySelector('.btn-eliminar').addEventListener('click', () => {
                if (confirm('¿Está seguro de que desea eliminar esta hoja clínica?')) {
                    deleteHojaClinica(historia.idHojaClinica);
                }
            });
            
            row.querySelector('.btn-editar').addEventListener('click', () => {
                window.location.href = `editarHojaClinica.html?idHojaClinica=${historia.idHojaClinica}&idMascota=${mascotaId}&nombreMascota=${mascotaNombre}`;
        });
        });
    }
    
    // Function to handle the deletion of a clinical record
    async function deleteHojaClinica(id) {
        try {
            const response = await fetch(`${baseURL}/hojaclinica/delete/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Hoja clínica eliminada correctamente.');
                getHojasClinicasByMascotaId(mascotaId); // Refresh the list
            } else {
                const error = await response.text();
                alert('Error al eliminar hoja clínica: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Carga el listado de mascotas cuando lapagina cargue
    getHojasClinicasByMascotaId(mascotaId);
});