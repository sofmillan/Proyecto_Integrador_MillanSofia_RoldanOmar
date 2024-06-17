package dh.backend.demo.controller;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.service.IOdontologoService;
import java.util.List;

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
  public ResponseEntity<List<OdontologoResponseDto>> buscarTodosOdontologos() {
    List<OdontologoResponseDto> odontologos = service.buscarTodos();
    return ResponseEntity.ok(odontologos);
  }

  @PostMapping
  public ResponseEntity<OdontologoResponseDto> registrar(@RequestBody OdontologoRequestDto odontologo) {
    OdontologoResponseDto odontologoGuardado = this.service.registrarOdontologo(odontologo);
      return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);

  }

  @GetMapping("/{idOdontologo}")
  public ResponseEntity<OdontologoResponseDto> buscarPorId(@PathVariable Integer idOdontologo) {
    OdontologoResponseDto odontologoEncontrado = this.service.buscarOdontologoPorId(idOdontologo);
      return ResponseEntity.ok(odontologoEncontrado);
  }

  @PutMapping("/{idOdontologo}")
  public ResponseEntity<String> actualizarOdontologo(@RequestBody OdontologoRequestDto odontologo, @PathVariable Integer idOdontologo) {
    this.service.actualizarOdontologo(odontologo, idOdontologo);
    return ResponseEntity.ok("Odontólogo actualizado");
  }

  @DeleteMapping("/{idOdontologo}")
  public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer idOdontologo) {
    this.service.eliminarOdontologo(idOdontologo);
    return ResponseEntity.ok("Odontólogo eliminado");
  }

  @GetMapping("/nombre/{nombreOdontologo}")
  public ResponseEntity<List<OdontologoResponseDto>> buscarPorNombre(@PathVariable String nombreOdontologo) {
    return ResponseEntity.ok(service.buscarPorNombre(nombreOdontologo));
  }

  @GetMapping("/matricula/{nroMatricula}")
  public ResponseEntity<List<OdontologoResponseDto>> buscarOdontologoPorMatricula(@PathVariable String nroMatricula) {
    return ResponseEntity.ok(service.buscarPorMatricula(nroMatricula));
  }
}
