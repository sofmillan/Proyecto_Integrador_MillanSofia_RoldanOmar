package dh.backend.demo.controller;

import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.service.ITurnoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController {
  private final ITurnoService turnoService;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public TurnoController(ITurnoService turnoService) {
    this.turnoService = turnoService;
  }

  @PostMapping
  public ResponseEntity<TurnoResponseDto> agregarTurno(@RequestBody TurnoRequestDto turno) {
    TurnoResponseDto turnoADevolver = turnoService.registrar(turno);
    return ResponseEntity.ok(turnoADevolver);
  }

  @GetMapping
  public ResponseEntity<List<TurnoResponseDto>> buscarTodosTurnos() {
    return ResponseEntity.ok(turnoService.buscarTodos());
  }

  @PutMapping("/{turnoId}")
  public ResponseEntity<String> modificarTurno(@RequestBody TurnoRequestDto turno, @PathVariable Integer turnoId) {
    turnoService.actualizarTurno(turno, turnoId);
    return ResponseEntity.ok("Turno modificado");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) {
    turnoService.eliminarTurno(id);
    return ResponseEntity.ok("Turno eliminado");
  }

  @GetMapping("/fechas")
  public ResponseEntity<List<TurnoResponseDto>> buscarEntreFechas(@RequestParam String inicio, @RequestParam String fin) {
    LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
    LocalDate fechaFinal = LocalDate.parse(fin, formatter);

    return ResponseEntity.ok(turnoService.buscarTurnoEntreFechas(fechaInicio, fechaFinal));
  }

  @GetMapping("/paciente/{nombre}")
  public ResponseEntity<List<TurnoResponseDto>> buscarPorPaciente(@PathVariable String nombre) {
    return ResponseEntity.ok(turnoService.buscarTurnoPorPaciente(nombre));
  }

  @GetMapping("/{turnoId}")
  public ResponseEntity<TurnoResponseDto> buscarPorId(@PathVariable Integer turnoId) {
    return ResponseEntity.ok(turnoService.buscarPorId(turnoId));
  }

}
