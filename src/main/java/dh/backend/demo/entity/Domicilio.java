package dh.backend.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Domicilio (Calle, Número, Localidad, Provincia)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "domicilios")
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
