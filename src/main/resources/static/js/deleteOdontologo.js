function deleteOdontologo(id) {
  fetch(`http://localhost:8080/odontologo/${id}`, {
    method: "DELETE",
  })
    .then((response) => {
      if (response.ok) {
        console.log("Odontologo eliminado con exito")
        window.location.reload();
      }
      else {
        console.log("Error al eliminar ")
      }
    })
    .catch((error) => {
      console.error("Error en la solicitud:", error);
    });
}
