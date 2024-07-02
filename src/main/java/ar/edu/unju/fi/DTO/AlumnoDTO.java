package ar.edu.unju.fi.DTO;



import java.time.LocalDate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class AlumnoDTO {
	private String lu;
	private String dni;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private LocalDate fnacimiento;
	private String domicilio;
	private Boolean estado;
	private Carrera carrera;
}
