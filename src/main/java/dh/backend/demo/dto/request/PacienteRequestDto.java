package dh.backend.demo.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PacienteRequestDto {
    private String apellido;
    private String nombre;
    private String email;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioRequestDto domicilio;
}
