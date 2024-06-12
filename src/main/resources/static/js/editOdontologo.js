function editOdontologo(id, nombre, apellido, nroMatricula) {

  // Construir la URL con parámetros
  const urlParams = new URLSearchParams();
  urlParams.append("id", id);
  urlParams.append("nombre", nombre);
  urlParams.append("apellido", apellido);
  urlParams.append("matricula", nroMatricula);

  // Navegar a la página de agregar/editar odontólogos con los parámetros en la URL
  window.location.href = `agregar_odontologos.html?${urlParams.toString()}`;
}
