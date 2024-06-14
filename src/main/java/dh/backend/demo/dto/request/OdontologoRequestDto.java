package dh.backend.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoRequestDto {
    private String nroMatricula;
    private String nombre;
    private String apellido;
}
