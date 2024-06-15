package dh.backend.demo.controller;

import dh.backend.demo.entity.Turno;
import dh.backend.demo.service.ITurnoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController {
  private ITurnoService turnoService;

  public TurnoController(ITurnoService turnoService) {
    this.turnoService = turnoService;
  }

  @PostMapping
  public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno) {
    Turno turnoADevolver = turnoService.registrar(turno);
    if (turnoADevolver == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } else {
      return ResponseEntity.status(HttpStatus.CREATED).body(turnoADevolver);
    }
  }

  @GetMapping
  public ResponseEntity<List<Turno>> buscarTodosTurnos() {
    return ResponseEntity.ok(turnoService.buscarTodos());
  }

  @PutMapping
  public ResponseEntity<String> modificarTurno(@RequestBody Turno turno) {
    turnoService.actualizarTurno(turno);
    return ResponseEntity.ok("Turno modificado");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
    turnoService.eliminarTurno(id);
    return ResponseEntity.ok("Turno eliminado");
  }

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @GetMapping("/fechas")
  public ResponseEntity<List<Turno>> buscarEntreFechas(@RequestParam String inicio, @RequestParam String fin) {
    LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
    LocalDate fechaFinal = LocalDate.parse(fin, formatter);

    return ResponseEntity.ok(turnoService.buscarTurnoEntreFechas(fechaInicio, fechaFinal));
  }

  @GetMapping("/paciente/{nombre}")
  public ResponseEntity<List<Turno>> buscarPorPaciente(@PathVariable String nombre) {
    return ResponseEntity.ok(turnoService.buscarTurnoPorPaciente(nombre));
  }

}
