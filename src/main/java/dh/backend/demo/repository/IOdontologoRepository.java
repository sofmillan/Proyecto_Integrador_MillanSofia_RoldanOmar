package dh.backend.demo.repository;

import dh.backend.demo.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {
}
