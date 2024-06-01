package dh.backend.demo.model;

import java.time.LocalDate;

public class Paciente {

  private Integer id;
  private String apellido;
  private String nombre;
  private String email;
  private String dni;
  private LocalDate fechaIngreso;

  public Paciente(
      Integer id,
      String apellido,
      String nombre,
      String email,
      String dni,
      LocalDate fechaIngreso) {
    this.id = id;
    this.apellido = apellido;
    this.nombre = nombre;
    this.email = email;
    this.dni = dni;
    this.fechaIngreso = fechaIngreso;
  }

  public Paciente(
      String apellido, String nombre, String email, String dni, LocalDate fechaIngreso) {
    this.apellido = apellido;
    this.nombre = nombre;
    this.email = email;
    this.dni = dni;
    this.fechaIngreso = fechaIngreso;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public LocalDate getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDate fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }
}
