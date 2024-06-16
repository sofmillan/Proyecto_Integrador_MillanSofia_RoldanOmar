package dh.backend.demo.respository;

import dh.backend.demo.entity.Odontologo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {

    List<Odontologo> findByNombre(String nombre);

    List<Odontologo> findByNroMatricula(String matricula);
}