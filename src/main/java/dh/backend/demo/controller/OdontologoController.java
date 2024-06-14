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
  private final IOdontologoService odontologoService;

  public OdontologoController(IOdontologoService service) {
    this.odontologoService = service;
  }

  @GetMapping
  public ResponseEntity<List<Odontologo>> buscarTodosOdontologos() {
    List<Odontologo> odontologos = odontologoService.buscarTodos();
    return ResponseEntity.ok(odontologos);
  }

  @PostMapping
  public ResponseEntity<OdontologoResponseDto> registrar(@RequestBody OdontologoRequestDto odontologo) {
    OdontologoResponseDto odontologoGuardado = this.odontologoService.registrarOdontologo(odontologo);
      return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);

  }

  @GetMapping("/{idOdontologo}")
  public ResponseEntity<OdontologoResponseDto> buscarPorId(@PathVariable Integer idOdontologo) {
    OdontologoResponseDto odontologoGuardado = this.odontologoService.buscarOdontologoPorId(idOdontologo);
    if (odontologoGuardado != null) {
      return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PutMapping
  public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
    this.odontologoService.actualizarOdontologo(odontologo);
    return ResponseEntity.ok("Odontólogo actualizado");
  }

  @DeleteMapping("/{idOdontologo}")
  public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer idOdontologo) {
    this.odontologoService.eliminarOdontologo(idOdontologo);
    return ResponseEntity.ok("Odontólogo eliminado");
  }
}
