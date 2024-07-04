package ar.edu.unju.fi.map;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarreraMapDTO {
	@Mapping(source="codigo",target="codigoCarrera")
	@Mapping(source="nombre",target="nombreCarrera")
	@Mapping(source="duracion",target="duracionCarrera")
	@Mapping(source="estado",target="estadoCarrera")
	CarreraDTO convertirCarreraACarreraDTO(Carrera c);

	@Mapping(target = "materias", ignore = true)
	@Mapping(target = "alumnos", ignore = true)
	@InheritInverseConfiguration
	Carrera convertirCarreraDTOACarrera(CarreraDTO c);
	
	List<CarreraDTO> convertirListaCarrerasAListaCarrerasDTO(List<Carrera> carreras);
	
	List<Carrera> convertirListaCarrerasDTOAListaCarreras(List<CarreraDTO> carreras);
	
	
}