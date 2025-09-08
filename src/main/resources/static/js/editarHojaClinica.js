document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const mascotaDisplay = document.getElementById('mascotaDisplay');
    const idMascotaInput = document.getElementById('idMascota');
    const motivoInput = document.getElementById('motivo');
    const examenesInput = document.getElementById('examenes');
    const medicamentosInput = document.getElementById('medicamentos');
    const fechaInput = document.getElementById('fecha ');
    const cancelarBtn = document.getElementById('cancelar-btn');
    const baseURL = 'http://localhost:8080/hojaclinica';

    // Get the hojaClinicaId and other details from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const hojaClinicaId = urlParams.get('idHojaClinica');
    const mascotaId = urlParams.get('idMascota');
    const mascotaNombre = urlParams.get('nombreMascota');

    if (!hojaClinicaId || !mascotaId) {
        alert('ID de hoja clínica o mascota no encontrado en la URL. Volviendo a la página anterior.');
        window.history.back();
        return;
    }

    // Display pet details and set hidden input
    if (mascotaDisplay && mascotaNombre) {
        mascotaDisplay.textContent = `${mascotaId} - ${mascotaNombre}`;
    }
    idMascotaInput.value = mascotaId;

    // Fetch the current clinical record data to pre-fill the form
    async function fetchHojaClinicaData(id) {
        try {
            const response = await fetch(`${baseURL}/${id}`);
            if (response.ok) {
                const historia = await response.json();
                motivoInput.value = historia.motivo;
                examenesInput.value = historia.examenes;
                medicamentosInput.value = historia.medicamentos;
                fechaInput.value = historia.fecha;
            } else if (response.status === 404) {
                alert('Hoja clínica no encontrada.');
                window.history.back();
            } else {
                alert('Error al obtener los datos de la hoja clínica.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Handle form submission to update the record
    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const motivo = motivoInput.value.trim();
        const examenes = examenesInput.value.trim();
        const medicamentos = medicamentosInput.value.trim();
        const fecha = fechaInput.value.trim();

        if (!motivo || !examenes || !medicamentos || !fecha) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        const historiaData = {
            idHojaClinica: hojaClinicaId,
            mascota: {
                idMascota: mascotaId
            },
            motivo: motivo,
            examenes: examenes,
            medicamentos: medicamentos,
            fecha: fecha
        };

        try {
            const response = await fetch(`${baseURL}/update/${hojaClinicaId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(historiaData)
            });

            if (response.ok) {
                alert('Hoja clínica actualizada correctamente.');
                window.history.back();
            } else {
                const error = await response.text();
                alert('Error al actualizar la hoja clínica: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    });

    // Handle "Cancelar" button
    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', function () {
            window.history.back();
        });
    }

    // Initial data fetch
    fetchHojaClinicaData(hojaClinicaId);
});