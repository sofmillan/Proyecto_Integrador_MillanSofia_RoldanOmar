package dh.backend.demo.respository;

import dh.backend.demo.entity.Turno;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {

  // Buscar turnos entre dos fechas
  @Query("Select t from Turno t where t.fecha BETWEEN :startDate and :endDate")
  List<Turno> buscarTurnoEntreFechas(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  List<Turno> findByPacienteNombre(String Nombre);
}
