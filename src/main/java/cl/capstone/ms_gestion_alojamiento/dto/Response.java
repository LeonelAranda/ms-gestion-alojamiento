package cl.capstone.ms_gestion_alojamiento.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {

    private int codigoRetorno;
    private String glosaRetorno;
    private Object resultado;
    private LocalDateTime timestamp;
}
