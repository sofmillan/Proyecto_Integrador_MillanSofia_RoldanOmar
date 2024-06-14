package dh.backend.demo.controller;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
  private final IOdontologoService service;

  public OdontologoController(IOdontologoService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
    List<Odontologo> odontologos = service.buscarTodos();
    return ResponseEntity.ok(odontologos);
  }

  @PostMapping
  public ResponseEntity<OdontologoResponseDto> registrar(@RequestBody OdontologoRequestDto odontologo) {
    OdontologoResponseDto odontologoGuardado = this.service.registrarOdontologo(odontologo);
    if (odontologoGuardado != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @GetMapping("/{idOdontologo}")
  public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer idOdontologo) {
    Optional<Odontologo> odontologoGuardado = this.service.buscarOdontologoPorId(idOdontologo);
    if (odontologoGuardado.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado.get());
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PutMapping
  public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
    this.service.actualizarOdontologo(odontologo);
    return ResponseEntity.ok("Odontólogo actualizado");
  }

  @DeleteMapping("/{idOdontologo}")
  public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer idOdontologo) {
    this.service.eliminarOdontologo(idOdontologo);
    return ResponseEntity.ok("Odontólogo eliminado");
  }
}
