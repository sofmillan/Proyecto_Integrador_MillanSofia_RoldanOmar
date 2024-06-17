package dh.backend.demo.controller;

import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.service.ITurnoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController {
  private final ITurnoService turnoService;

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

  @PutMapping
  public ResponseEntity<String> modificarTurno(@RequestBody TurnoRequestDto turno) {
    turnoService.actualizarTurno(turno);
    return ResponseEntity.ok("Turno modificado");
  }
}
