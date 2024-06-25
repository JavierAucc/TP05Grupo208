package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class CarreraDTO {
	private String codigoCarrera;
	private String nombreCarrera;
	private int duracionCarrera;
	private boolean estadoCarrera;
}