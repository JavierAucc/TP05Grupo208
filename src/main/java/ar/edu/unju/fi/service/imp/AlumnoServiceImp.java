package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService{

	
	@Autowired
	AlumnoRepository alumnoRepository;
	@Autowired
	AlumnoMapDTO alumnoMapDTO;

	@Override
	public void guardarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		alumno.setEstado(true);
		alumnoRepository.save(alumno);
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnoDTO() {
		// TODO Auto-generated method stub
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
	}

	@Override
	public List<Alumno> mostrarAlumno() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAlumnoByEstado(true);
	}
	
	@Override
	public void borrarAlumno(String lu) {
		// TODO Auto-generated method stub
		//List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true);
		int p=buscarPosicionAlumno(lu);
		if(p!=-1) {
			todosLosAlumnos.get(p).setEstado(false);
			alumnoRepository.save(todosLosAlumnos.get(p));
		}
	}

	@Override
	public void modificarAlumno(Alumno m) {
		// TODO Auto-generated method stub
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true);
		int p=buscarPosicionAlumno(m.getLu());
		if (p!=-1) {
			todosLosAlumnos.set(p,m);
			
			alumnoRepository.save(todosLosAlumnos.get(p));
		}
	}
	
	@Override
	public int buscarPosicionAlumno(String lu) {
		// TODO Auto-generated method stub
		int j=-1;
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true); 
		for(int i=0; i< todosLosAlumnos.size(); i++) {
			Alumno alumno = todosLosAlumnos.get(i);
			if(alumno.getLu().equals(lu)) {
				 j=i;
				break;
			}
		}
		return j;
	}
	
	@Override
	public Alumno buscarAlumno(String lu) {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = alumnoRepository.findAlumnoByEstado(true);
		int p=buscarPosicionAlumno(lu);
		return (p!=-1) ? alumnos.get(p) : null;
	}

	

}
