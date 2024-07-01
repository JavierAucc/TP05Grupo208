package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Materia;

@Service
public interface AlumnoService {
	
	public void guardarAlumno(Alumno alumno);
	public List<AlumnoDTO> mostrarAlumnoDTO();//vista con DTO
	public List<Alumno> mostrarAlumno();//vista sin DTO
	public void borrarAlumno(String lu);
	public void modificarAlumno(Alumno alumno);
	public Alumno buscarAlumno(String lu);
	public int buscarPosicionAlumno(String lu);
	//Operaciones de Alumno-Materia
	public void inscribirAlumno(Alumno alumno, Materia materia);
	public List<Alumno> filtrarAlumnos(Integer codigo);//vista 
	//Operaciones de Alumno-Carrera
	public List<Alumno> filtrarAlumnosPorCarrera(String lu);
}
