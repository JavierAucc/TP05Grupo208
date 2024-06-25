package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Component
public class MateriaDTO {
	private Integer codigoMateria;
	private String nombreMateria;
	private String cursoMateria;
	private int duracionMateria;
	private boolean modalidadMateria;
	private boolean estadoMateria;
}
