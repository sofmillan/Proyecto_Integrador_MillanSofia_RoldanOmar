package dh.backend.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String apellido;
  private String nombre;
  private String email;
  private String dni;
  private LocalDate fechaIngreso;
  private Domicilio domicilio;
}
