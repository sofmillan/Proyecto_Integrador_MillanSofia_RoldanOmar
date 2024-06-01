package dh.backend.demo.controller;

import dh.backend.demo.model.Odontologo;
import dh.backend.demo.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private final IService<Odontologo> service;

    public OdontologoController(IService<Odontologo> service) {
        this.service = service;
    }


}
