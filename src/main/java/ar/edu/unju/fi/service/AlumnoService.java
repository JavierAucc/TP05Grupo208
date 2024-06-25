package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;

@Service
public interface AlumnoService {
	
	public void guardarAlumno(AlumnoDTO alumnoDTO);
	public List<AlumnoDTO> mostrarAlumnoDTO();
	public void borrarAlumnoDTO(String lu);
	public void modificarAlumnoDTO(AlumnoDTO alumno);
	public AlumnoDTO buscarAlumnoDTO(String lu);
	public int buscarPosicionAlumnoDTO(String lu);
}
