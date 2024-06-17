package dh.backend.demo.service;

import dh.backend.demo.dto.request.TurnoRequestDto;
import dh.backend.demo.dto.response.TurnoResponseDto;
import dh.backend.demo.entity.Turno;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ITurnoService {
  TurnoResponseDto registrar(TurnoRequestDto turno);

  TurnoResponseDto buscarPorId(Integer id);

  List<TurnoResponseDto> buscarTodos();

  void actualizarTurno(TurnoRequestDto turno);

  void eliminarTurno(Integer id);

  // HQL
  List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);

  List<TurnoResponseDto> buscarTurnoPorPaciente(String Nombre);
}
