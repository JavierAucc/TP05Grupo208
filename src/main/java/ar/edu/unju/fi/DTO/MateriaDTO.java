package ar.edu.unju.fi.DTO;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
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
	private int duracionMateria;
	private boolean modalidadMateria;
	private Docente docenteMateria;
	private Carrera carreraMateria;
}
