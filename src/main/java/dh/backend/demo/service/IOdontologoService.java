package dh.backend.demo.service;

import dh.backend.demo.dto.request.OdontologoRequestDto;
import dh.backend.demo.dto.request.OdontologoUpdateDto;
import dh.backend.demo.dto.response.OdontologoResponseDto;
import dh.backend.demo.entity.Odontologo;
import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
  OdontologoResponseDto registrarOdontologo(OdontologoRequestDto odontologo);

  OdontologoResponseDto buscarOdontologoPorId(Integer id);

  List<OdontologoResponseDto> buscarTodos();

  void actualizarOdontologo(OdontologoUpdateDto paciente);

  void eliminarOdontologo(Integer id);
}
