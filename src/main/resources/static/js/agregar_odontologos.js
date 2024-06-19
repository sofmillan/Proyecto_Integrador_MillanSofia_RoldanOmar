const form = document.getElementById("agregarForm");

form.addEventListener("submit", function (event) {
  event.preventDefault();

  let method = "POST";
  let url = `http://localhost:8080/odontologo`;
  const nombre = document.getElementById("nombre").value;
  const apellido = document.getElementById("apellido").value;
  const matricula = document.getElementById("matricula").value;
  let body = { nombre: nombre, apellido: apellido, nroMatricula: matricula }
  const params = getUrlParams();

  if (params.id) {
    method = "PUT";
    body = { nombre: nombre, apellido: apellido, nroMatricula: matricula }
    url = `http://localhost:8080/odontologo/${params.id}`;
    console.log(url)
  }

  // llamando al endpoint de agregar
  fetch(url, {
    method: method,
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  })
    .then((response) => {
      const mensaje = params.id ? "Odontólogo modificado" : "Odontólogo agregado";
      alert(mensaje);
      // Navegar a la página de ver odontólogos
      window.location.href = "../pages/listar_odontologos.html";
    })
    .catch((error) => {
      console.error("Error agregando odontólogo:", error);
    });
});

// Función para obtener parámetros de la URL
function getUrlParams() {
  const searchParams = new URLSearchParams(window.location.search);
  const id = searchParams.get("id");
  const nombre = searchParams.get("nombre");
  const apellido = searchParams.get("apellido");
  const matricula = searchParams.get("matricula");
  return { id, nombre, apellido, matricula };
}

// Rellenar el formulario si hay parámetros en la URL
function fillForm() {
  const params = getUrlParams();
  if (params.id) {
    document.getElementById("tituloFormulario").textContent = "Editar Odontólogo";
    document.getElementById("nombre").value = params.nombre;
    document.getElementById("apellido").value = params.apellido;
    document.getElementById("matricula").value = params.matricula;
    document.getElementById("botonFormulario").textContent = "Guardar Cambios";
  }
}

fillForm();
