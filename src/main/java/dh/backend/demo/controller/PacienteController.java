package dh.backend.demo.controller;

import dh.backend.demo.dto.request.PacienteRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.dto.response.PacienteResponseDto;
import dh.backend.demo.entity.Paciente;
import dh.backend.demo.service.IPacienteService;
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

  private final IPacienteService pacienteService;

  public PacienteController(IPacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }

  @GetMapping
  public ResponseEntity<List<PacienteResponseDto>> buscarTodosLosPacientes() {
    List<PacienteResponseDto> pacientes = pacienteService.buscarTodos();
    return ResponseEntity.ok(pacientes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteResponseDto> buscarPacientePorId(@PathVariable Integer id) {
    PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorId(id);
    return ResponseEntity.ok(pacienteEncontrado);

  }

  @PostMapping
  public ResponseEntity<PacienteResponseDto> guardarPaciente(@RequestBody PacienteRequestDto paciente) {
    PacienteResponseDto odontologoGuardado = this.pacienteService.registrarPaciente(paciente);
    return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
  }

  @PutMapping("/{idPaciente}")
  public ResponseEntity<String> actualizarPaciente(@RequestBody PacienteRequestDto paciente, @PathVariable Integer idPaciente) {
    pacienteService.actualizarPaciente(paciente, idPaciente);
    return ResponseEntity.ok("paciente actualizado");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
    pacienteService.eliminarPaciente(id);
    return ResponseEntity.ok("paciente eliminado");
  }
}
