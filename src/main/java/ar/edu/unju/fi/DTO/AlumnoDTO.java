package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class AlumnoDTO {
	private String lu;
	private String nombre;
	private String apellido;
	private Carrera carrera;

}
