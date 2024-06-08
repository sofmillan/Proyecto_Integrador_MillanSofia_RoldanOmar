package dh.backend.demo.respository;

import dh.backend.demo.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {}
