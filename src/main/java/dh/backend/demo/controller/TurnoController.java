package dh.backend.demo.controller;

import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.entity.Turno;
import dh.backend.demo.service.ITurnoService;
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
  public ResponseEntity<Turno> agregarTurno(@RequestBody TurnoRequestDto turno) {
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
}
