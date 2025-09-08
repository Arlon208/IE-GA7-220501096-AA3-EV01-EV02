document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const idMascotaInput = document.getElementById('idMascota');
     const mascotaDisplay = document.getElementById('mascotaDisplay');
    const motivoInput = document.getElementById('motivo');
    const examenesInput = document.getElementById('examenes'); // Corrected ID
    const medicamentosInput = document.getElementById('medicamentos');
    const fechaInput = document.getElementById('fecha ');
    const cancelarBtn = document.getElementById('cancelar-btn');
    const baseURL = 'http://localhost:8080/hojaclinica';

    // Obtiene el id mascota mediante la URL
    const urlParams = new URLSearchParams(window.location.search);
    const mascotaId = urlParams.get('idMascota');
    const mascotaNombre = urlParams.get('nombreMascota');

    if (mascotaId && mascotaNombre) {
    
        mascotaDisplay.textContent = `${mascotaId} - ${mascotaNombre}`;
        
        idMascotaInput.value = mascotaId;
    } else {
        alert('ID de mascota no encontrado en la URL. Volviendo a la página anterior.');
        window.history.back();
    }

    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const motivo = motivoInput.value.trim();
        const examenes = examenesInput.value.trim();
        const medicamentos = medicamentosInput.value.trim();
        const fecha = fechaInput.value.trim();

        //Comprueba que todos los campos esten diligenciados
        if (!motivo || !examenes || !medicamentos || !fecha) {
            alert('Por favor, complete todos los campos.');
            return;
        }

        // JSON para la API
        const clinicalRecordData = {
            mascota: {
                idMascota: mascotaId
            },
            motivo: motivo,
            examenes: examenes,
            medicamentos: medicamentos,
            fecha: fecha
        };

        try {
            const response = await fetch(`${baseURL}/add`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(clinicalRecordData)
            });

            if (response.ok) {
                alert('Hoja clínica registrada correctamente.');
                form.reset();
            } else {
                const error = await response.text();
                alert('Error al registrar la hoja clínica: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    });


    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', function () {
            window.location.href = 'mMascotas.html';
        });
    }
});