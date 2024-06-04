# Sistema de reserva de turnos para clínica odontológica

## Descripción del Proyecto

Implementaciónde un sistema de reserva de turnos para una clínica odontológica, permitiendo la administración de datos de odontólogos y pacientes, así como la asignación de turnos.
El sistema también incluye un mecanismo de autenticación y roles.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3.0**
- **Spring Data JPA**
- **Spring Security**
- **Spring Logging**
- **H2 Database**
- **Maven**
- **HTML, CSS, JavaScript**

## Uso

1. Clona el repositorio:

   ```bash
   git clone https://github.com/sofmillan/Proyecto_Integrador_MillanSofia_RoldanOmar.git
   cd Proyecto_Integrador_MillanSofia_RoldanOmar
   ```

2. Ejecuta el proyecto:

   ```bash
   # Inicia el proyecto en http://localhost:8080
   mvn spring-boot:run
   ```

## Requerimientos Funcionales

1. **Administración de Odontólogos**:

   - Listar, agregar, modificar y eliminar odontólogos.
   - Datos registrados: apellido, nombre y matrícula.

2. **Administración de Pacientes**:

   - Listar, agregar, modificar y eliminar pacientes.
   - Datos registrados: nombre, apellido, domicilio, DNI y fecha de alta.

3. **Registro de Turnos**:

   - Asignar un turno a un paciente con un odontólogo a una fecha y hora específicas.

4. **Login y Autorización**:

   - Validar ingreso al sistema mediante usuario y contraseña.
   - Los usuarios con rol `ROLE_USER` pueden registrar turnos.
   - Los usuarios con rol `ROLE_ADMIN` pueden gestionar odontólogos y pacientes.
   - Un usuario puede tener **solo un rol**, definido en la base de datos.

## Requerimientos Técnicos

1. **Capas**:

   - **Entidades**: Clases `model` del negocio.
   - **Acceso a Datos (DAO/Repository)**: Clases `dao` que manejan el acceso a la base de datos.
   - **Negocio**: Clases `service` que desacoplan el acceso a datos de la vista / controlador REST.
   - **Presentación**: Clases `controller` ya sea para Pantallas HTML o Controladores REST.
   - **Base de datos**: Clase `db` para la conectarse a la base de datos H2.

2. **Manejo de Excepciones**:

   - Logueo de cualquier excepción generada.

3. **Test Unitarios**:

   - Realización de test unitarios para garantizar la calidad del código.
