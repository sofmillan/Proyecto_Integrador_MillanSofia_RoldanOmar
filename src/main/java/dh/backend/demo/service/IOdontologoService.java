package dh.backend.demo.service;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
  OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo);

  OdontologoResponseDto buscarOdontologoPorId(Integer id);

  List<OdontologoResponseDto> buscarTodos();

  void actualizarOdontologo(OdontologoRequestDto paciente, Integer idOdontologo);

  void eliminarOdontologo(Integer id);

  List<OdontologoResponseDto> buscarPorNombre(String nombre);

  OdontologoResponseDto buscarPorMatricula(String matricula);
}
