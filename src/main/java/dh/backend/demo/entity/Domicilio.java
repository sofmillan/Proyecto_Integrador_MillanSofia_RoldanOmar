package dh.backend.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Domicilio (Calle, Número, Localidad, Provincia)
@Entity
@Data
public class Domicilio {
  // id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String calle;
  private Integer numero;
  private String localidad;
  private String provincia;
}
