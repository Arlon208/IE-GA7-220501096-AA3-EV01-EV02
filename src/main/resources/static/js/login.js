document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector(".login-form");
    const usuarioInput = document.getElementById("usuario");
    const contrasenaInput = document.getElementById("contrasena");
    const forgotPasswordLink = document.querySelector(".forgot-password");

    // Manejar el envío del formulario
    if (loginForm) {
        loginForm.addEventListener("submit", async function (event) {
            // Previene el comportamiento por defecto de recargar la página
            event.preventDefault(); 

            const username = usuarioInput.value.trim();
            const password = contrasenaInput.value.trim();

            if (!username || !password) {
                alert("Por favor, complete todos los campos.");
                return;
            }

            const loginData = {
                username: username,
                password: password,
            };

            try {
                const response = await fetch('http://localhost:8080/login', {
                    method: 'POST',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(loginData),
                });

                if (response.ok) {
                    // Login exitoso
                    alert("Inicio de sesión correcto.");
                    window.location.href = "menu.html"; // Redirige al menú principal
                } else {
                    // Login fallido, el servidor devuelve un error
                    const error = await response.text();
                    alert("Inicio de sesión fallido: " + error);
                }
            } catch (error) {
                // Error de conexión con la API
                console.error("Error al conectar con la API:", error);
                alert("Error de conexión. Asegúrese de que la API esté funcionando.");
            }
        });
    }

    // Funcionalidad para "Olvidó su contraseña?"
    if (forgotPasswordLink) {
        forgotPasswordLink.addEventListener("click", function (event) {
            event.preventDefault();
            alert("Comuníquese con el administrador.");
        });
    }
});