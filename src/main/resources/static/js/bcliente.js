document.addEventListener('DOMContentLoaded', function () {
    const searchForm = document.getElementById('search-form');
    const clientTableBody = document.getElementById('client-table-body');
    const baseURL = 'http://localhost:8080/clientes';

    // Obtener clientes por ID
    async function getClienteById(id) {
        try {
            const response = await fetch(`${baseURL}/${id}`);
            if (response.ok) {
                const cliente = await response.json();
                displayClientInTable(cliente);
            } else if (response.status === 404) {
                alert('Cliente no encontrado.');
                clientTableBody.innerHTML = ''; 
            } else {
                const error = await response.text();
                alert('Error al buscar cliente: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión con la API.');
        }
    }

    // Crea la tabla con los datos obtenidos
    function displayClientInTable(cliente) {
        clientTableBody.innerHTML = ''; //Limpia resultado anteriores
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${cliente.idCliente}</td>
            <td>${cliente.nombre}</td>
            <td>${cliente.telefono}</td>
            <td>${cliente.ciudad}</td>
            <td>${cliente.direccion}</td>
            <td>
                <button class="btn-editar" data-id="${cliente.idCliente}">Editar</button>
                <button class="btn-eliminar" data-id="${cliente.idCliente}">Eliminar</button>
            </td>
        `;
        clientTableBody.appendChild(row);

        row.querySelector('.btn-eliminar').addEventListener('click', () => {
            if (confirm('¿Está seguro de que desea eliminar este cliente?')) {
                deleteCliente(cliente.idCliente);
            }
        });
        
        row.querySelector('.btn-editar').addEventListener('click', () => {
            localStorage.setItem('editingClientData', JSON.stringify(cliente));
            window.location.href = 'editarCliente.html';
        });
    }

    // Funcion para Eliminar cliente
    async function deleteCliente(id) {
        try {
            const response = await fetch(`http://localhost:8080/cliente/delete/${id}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                alert('Cliente eliminado correctamente.');
                clientTableBody.innerHTML = '';
            } else {
                const error = await response.text();
                alert('Error al eliminar cliente: ' + error);
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }

    // Boton busqueda
    searchForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const searchId = document.getElementById('searchId').value;
        if (searchId) {
            getClienteById(searchId);
        }
    });
});