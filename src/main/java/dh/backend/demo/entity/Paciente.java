package dh.backend.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
