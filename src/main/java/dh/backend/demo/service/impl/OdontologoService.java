package dh.backend.demo.service.impl;

import dh.backend.demo.dao.IDao;
import dh.backend.demo.model.Odontologo;
import dh.backend.demo.repository.IOdontologoRepository;
import dh.backend.demo.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public Optional<Odontologo> buscarOdontologoPorId(Integer id){
        return odontologoRepository.findById(id);
    }
    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    @Override
    public void modificarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }
}
