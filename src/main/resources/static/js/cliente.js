document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const guardarBtn = document.getElementById('enviado');
    const cancelarBtn = document.getElementById('cancelar-btn');

    if (form) {
        form.addEventListener('submit', async function (event) {
            event.preventDefault(); 

            // Obtener valores del formulario
            const idCliente = document.getElementById('cedula').value;
            const nombre = document.getElementById('nombre').value;
            const telefono = document.getElementById('telefono').value;
            const ciudad = document.getElementById('ciudad').value;
            const direccion = document.getElementById('direccion').value;

            // Validacion para evitar campos vacios
            if (!idCliente || !nombre || !telefono || !ciudad || !direccion) {
                alert('Por favor, complete todos los campos.');
                return;
            }

            // JSON para enviar al API obtenido del formulario
            const clienteData = {
                idCliente: parseInt(idCliente), 
                nombre: nombre,
                telefono: telefono,
                ciudad: ciudad,
                direccion: direccion
            };

            try {
                // Se envia el JSON usando fecth
                const response = await fetch('http://localhost:8080/cliente/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(clienteData)
                });

                if (response.ok) { //Respuesta positiva registra el cliente
                    alert('Cliente registrado correctamente.');
                    form.reset(); // Borra todos los campos al guardar correctamente
                } else {
                    const error = await response.text();
                    alert('Error al registrar cliente: ' + error);
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Error de conexión con la API.');
            }
        });
    }

    // permite volver atras al presionar cancelar
    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', function () {
            // Navigate back to the previous page
            window.history.back(); 
        });
    }
});