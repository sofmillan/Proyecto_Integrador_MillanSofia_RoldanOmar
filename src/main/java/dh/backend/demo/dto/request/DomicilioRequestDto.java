package dh.backend.demo.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DomicilioRequestDto {
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;
}
