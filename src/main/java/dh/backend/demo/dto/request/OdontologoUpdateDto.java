package dh.backend.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoUpdateDto {
    private Integer id;
    private String nroMatricula;
    private String nombre;
    private String apellido;
}
