document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const cedulaInput = document.getElementById('cedula');
    const nombreInput = document.getElementById('nombre');
    const telefonoInput = document.getElementById('telefono');
    const ciudadInput = document.getElementById('ciudad');
    const direccionInput = document.getElementById('direccion');
    const cancelarBtn = document.getElementById('cancelar-btn');

    let clientToEdit = null;

    // Almacenamiento en localstorage
    const storedData = localStorage.getItem('editingClientData');
    if (storedData) {
        clientToEdit = JSON.parse(storedData);
        cedulaInput.value = clientToEdit.idCliente;
        nombreInput.value = clientToEdit.nombre;
        telefonoInput.value = clientToEdit.telefono;
        ciudadInput.value = clientToEdit.ciudad;
        direccionInput.value = clientToEdit.direccion;
        
        // La cedula no se permite editar
        cedulaInput.disabled = true;
    } else {
        alert('No se encontró información del cliente para editar.');
        window.location.href = 'mClientes.html'; 
    }

    // Handle form submission for the update operation
    form.addEventListener('submit', async function (event) {
        event.preventDefault();

        const updatedClient = {
            idCliente: clientToEdit.idCliente, 
            nombre: nombreInput.value,
            telefono: telefonoInput.value,
            ciudad: ciudadInput.value,
            direccion: direccionInput.value
        };

        try {
            const response = await fetch(`http://localhost:8080/cliente/update/${updatedClient.idCliente}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedClient)
            });

            if (response.ok) {
                alert('Cliente actualizado correctamente.');
                localStorage.removeItem('editingClientData'); // Clear stored data
                window.location.href = 'mClientes.html'; // Redirect back to the search page
            } else {
                const error = await response.text();
                alert('Error al actualizar cliente: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    });

    // Funcion del boton cancelar
    if (cancelarBtn) {
        cancelarBtn.addEventListener('click', function () {
            window.location.href = 'mClientes.html';
        });
    }
});