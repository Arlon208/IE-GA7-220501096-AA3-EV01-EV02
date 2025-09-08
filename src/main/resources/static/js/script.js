document.addEventListener("DOMContentLoaded", function () {
  const loginBtn = document.getElementById("inicio");
  const forgotPasswordLink = document.querySelector(".forgot-password");
  const logoutBtn = document.getElementById("logout-btn");
  const cancelarBtn = document.getElementById("cancelar-btn");
  const guardarBtn = document.getElementById("guardar-btn");
  const modal = document.getElementById("alerta-cruce");
  const cerrarModal = document.getElementById("cerrar-modal");
  const usuario = document.getElementById("usuario");
  const contrasena = document.getElementById("contrasena");
  const busqueda = document.getElementById("busqueda");
  const enviado = document.getElementById("enviado");

  if (busqueda) {
    busqueda.addEventListener("click", function (event) {
      event.preventDefault();
      alert("Realizando busqueda.");
    });
  }
  // Cerrar sesión
  if (logoutBtn) {
    logoutBtn.addEventListener("click", function () {
      alert("Sesión cerrada");
      window.location.href = "index.html";
    });
  }

  if (cancelarBtn) {
    cancelarBtn.addEventListener("click", function () {
      history.back();
    });
  }

  if (guardarBtn && modal) {
    guardarBtn.addEventListener("click", function (event) {
      event.preventDefault();
      modal.classList.add("mostrar");
      modal.classList.remove("ocultar");
      modal.style.display = "flex";
    });
  }

  if (cerrarModal && modal) {
    cerrarModal.addEventListener("click", function () {
      modal.classList.add("ocultar");
      setTimeout(() => {
        modal.classList.remove("mostrar", "ocultar");
        modal.style.display = "none";
      }, 500);
    });
  }
});
