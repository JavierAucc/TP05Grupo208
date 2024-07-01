package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MateriaMapDTO {
	@Mapping(source="codigo",target="codigoMateria")
	@Mapping(source="nombre",target="nombreMateria")
	@Mapping(source="duracion",target="duracionMateria")
	@Mapping(source="modalidad",target="modalidadMateria")
	@Mapping(source="docente",target="docenteMateria")
	MateriaDTO convertirMateriaAMateriaDTO(Materia m);
	
	@Mapping(target = "alumnos", ignore = true)
	@Mapping(target = "curso", ignore = true)
	@Mapping(target = "estado", ignore = true)
	@InheritInverseConfiguration
	Materia convertirMateriaDTOAMateria(MateriaDTO m);
	
	List<MateriaDTO> convertirListaMateriasAListaMateriasDTO(List<Materia> materias);
	
	List<Materia> convertirListaMateriasDTOAListaMaterias(List<MateriaDTO> materias);
	
	
}
