package dh.backend.demo.dto.response;

import dh.backend.demo.dto.request.PacienteRequestDto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoResponseDto {
    private Integer id;
    private OdontologoResponseDto odontologo;
    private PacienteResponseDto paciente;
    private LocalDate fecha;
}
