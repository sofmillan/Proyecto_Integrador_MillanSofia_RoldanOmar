package dh.backend.demo.controller;

import dh.backend.demo.model.Odontologo;
import dh.backend.demo.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Odontologo> registrar(@RequestBody Odontologo odontologo){
        Odontologo odontologoGuardado = this.service.registrarOdontologo(odontologo);
        if (odontologoGuardado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{idOdontologo}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer idOdontologo){
        Odontologo odontologoGuardado = this.service.buscarOdontologoPorId(idOdontologo);
        if (odontologoGuardado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(odontologoGuardado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        this.service.actualizarOdontologo(odontologo);
        return ResponseEntity.ok("Odontólogo actualizado");
    }

    @DeleteMapping("/{idOdontologo}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer idOdontologo){
        this.service.eliminarOdontologo(idOdontologo);
        return ResponseEntity.ok("Odontólogo actualizado");
    }

}
