const tableBody = document.querySelector("#odontologosTable tbody");
function fetchOdontologos() {
  // listando los odontologos

  fetch(`http://127.0.0.1:8080/odontologo`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      console.log(tableBody);
      // Limpiar el contenido actual de la tabla
      tableBody.innerHTML = "";

      // Insertar los datos en la tabla
      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                <td>${odontologo.id}</td>
                <td>${odontologo.nombre}</td>
                <td>${odontologo.apellido}</td>
                <td>${odontologo.nroMatricula}</td>
                <td>
                  <button class="btn btn-primary btn-sm" onclick="editOdontologo(${odontologo.id}, '${odontologo.nombre}', '${odontologo.apellido}', '${odontologo.nroMatricula}')">Modificar</button>
                  <button class="btn btn-danger btn-sm" onclick="deleteOdontologo(${odontologo.id})">Eliminar</button>
                </td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });

  // modificar un odontologo

  // eliminar un odontologo
}

fetchOdontologos();
