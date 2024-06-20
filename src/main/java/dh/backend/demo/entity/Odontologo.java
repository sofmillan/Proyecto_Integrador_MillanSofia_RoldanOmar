package dh.backend.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "odontologos")
public class Odontologo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nroMatricula;
  private String nombre;
  private String apellido;

  @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Turno> turnoSet = new HashSet<>();

  @Override
  public String toString() {
    return "Odontologo{" +
            "id=" + id +
            ", nroMatricula='" + nroMatricula + '\'' +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            '}';
  }
}
