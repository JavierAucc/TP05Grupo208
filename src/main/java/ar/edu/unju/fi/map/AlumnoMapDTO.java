package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlumnoMapDTO {

	@Mapping(source="lu", target="lu")
	@Mapping(source="nombre", target="nombre")
	@Mapping(source="apellido", target="apellido")
	@Mapping(source = "carrera", target = "carrera")
	AlumnoDTO convertirAlumnoAAlumnoDTO(Alumno a);
	
	@Mapping(target = "dni", ignore = true)
	@Mapping(target = "domicilio", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "estado", ignore = true)
	@Mapping(target = "fnacimiento", ignore = true)
	@Mapping(target = "telefono", ignore = true)
	@Mapping(target = "materias", ignore = true)
	@InheritInverseConfiguration
	Alumno convertirAlumnoDTOAAlumno (AlumnoDTO adto);
	
	List<AlumnoDTO> convertirListaAlumnosAListaAlumnosDTO(List<Alumno> listaA);
	
	List<Alumno> convertirListaAlumnosDTOAListaAlumnos(List<AlumnoDTO> listaAdto);

	
	
	
	
	
	
	
	
	
	
	
}
