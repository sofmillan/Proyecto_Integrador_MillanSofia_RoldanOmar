package dh.backend.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
public class Turno {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  Paciente paciente;
  Odontologo odontologo;
  LocalDate fecha;
}
