package dh.backend.demo.controller;

import dh.backend.demo.entity.Paciente;
import dh.backend.demo.service.impl.PacienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

  private PacienteService pacienteService;

  public PacienteController(PacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }

  @GetMapping
  public ResponseEntity<List<Paciente>> buscarTodosLosPacientes() {
    List<Paciente> pacientes = pacienteService.buscarTodos();
    return ResponseEntity.ok(pacientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) {
    Optional<Paciente> pacienteEncontrado = pacienteService.buscarPorId(id);
    if (pacienteEncontrado.isPresent()) {
      return ResponseEntity.ok(pacienteEncontrado.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @PostMapping
  public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
    Paciente pacienteGuardado = pacienteService.registrarPaciente(paciente);
    if (pacienteGuardado != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(pacienteGuardado);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PutMapping
  public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {
    pacienteService.actualizarPaciente(paciente);
    return ResponseEntity.ok("paciente actualizado");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
    pacienteService.eliminarPaciente(id);
    return ResponseEntity.ok("paciente eliminado");
  }

  @GetMapping("/dni/{dni}")
  public ResponseEntity<List<Paciente>> buscartPorDni(@PathVariable String dni) {
    List<Paciente> pacientesEncontrados = pacienteService.buscarPorDni(dni);
    return ResponseEntity.status(HttpStatus.OK).body(pacientesEncontrados);
  }

  @GetMapping("/domicilio/provincia/{provincia}")
  public ResponseEntity<List<Paciente>> buscarPorDomicilio(@PathVariable String provincia) {
    List<Paciente> pacientesEncontrados = pacienteService.buscarPorDomicilioProvincia(provincia);
    return ResponseEntity.status(HttpStatus.OK).body(pacientesEncontrados);
  }

}
