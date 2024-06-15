package dh.backend.demo.service;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
  OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo);

  Optional<Odontologo> buscarOdontologoPorId(Integer id);

  List<Odontologo> buscarTodos();

  void actualizarOdontologo(Odontologo paciente);

  void eliminarOdontologo(Integer id);

  List<Odontologo> buscarPorNombre(String nombre);

  List<Odontologo> buscarPorMatricula(String matricula);
}
