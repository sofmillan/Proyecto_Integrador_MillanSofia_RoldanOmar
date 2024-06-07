package dh.backend.demo.controller;

import dh.backend.demo.model.Odontologo;
import dh.backend.demo.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping("/{idOdontologo}")
    public ResponseEntity<Odontologo>  buscarOdontologoPorId(@PathVariable Integer idOdontologo){
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorId(idOdontologo);
        if(odontologo.isPresent()){
            Odontologo odontologoARetornar = odontologo.get();
            return ResponseEntity.ok(odontologoARetornar);
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoOptional = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if(odontologoOptional.isPresent()){
            odontologoService.modificarOdontologo(odontologo);
            return ResponseEntity.ok("{\"message\": \"odontologo modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{idOdontologo}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer idOdontologo){
        Optional<Odontologo> odontologo = odontologoService.buscarOdontologoPorId(idOdontologo);
        if(odontologo.isPresent()){
            odontologoService.eliminarOdontologo(idOdontologo);
            return ResponseEntity.ok("{\"message\": \"odontologo eliminado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}