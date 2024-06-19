package dh.backend.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String apellido;
  private String nombre;
  private String email;
  private String dni;
  private LocalDate fechaIngreso;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "domicilio_id")
  private Domicilio domicilio;

  @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
  @JsonIgnore
  private Set<Turno> turnoSet = new HashSet<>();

  public Paciente(Integer id, String apellido, String nombre, String email, String dni, LocalDate fechaIngreso, Domicilio domicilio) {
    this.id = id;
    this.apellido = apellido;
    this.nombre = nombre;
    this.email = email;
    this.dni = dni;
    this.fechaIngreso = fechaIngreso;
    this.domicilio = domicilio;
  }
}
