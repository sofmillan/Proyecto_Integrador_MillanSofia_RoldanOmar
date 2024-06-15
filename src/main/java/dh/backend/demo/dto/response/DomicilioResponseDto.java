package dh.backend.demo.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DomicilioResponseDto {
    private Integer id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
