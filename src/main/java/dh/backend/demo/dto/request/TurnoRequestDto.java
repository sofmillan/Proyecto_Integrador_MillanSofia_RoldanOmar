package dh.backend.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoRequestDto {
    private Integer pacienteId;
    private Integer odontologoId;
    private String fecha;

}
