package dh.backend.demo.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PacienteResponseDto {
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    private String dni;
    private LocalDate fechaIngreso;
    private DomicilioResponseDto domicilio;
}
