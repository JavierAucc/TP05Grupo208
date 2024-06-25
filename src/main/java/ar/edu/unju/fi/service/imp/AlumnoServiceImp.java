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
	public void guardarAlumno(AlumnoDTO alumnoDTO) {
		// TODO Auto-generated method stub
		alumnoDTO.setEstado(true);
		alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(alumnoDTO));
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnoDTO() {
		// TODO Auto-generated method stub
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
	}

	@Override
	public void borrarAlumnoDTO(String lu) {
		// TODO Auto-generated method stub
		//List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		List<AlumnoDTO> todosLosAlumnos = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
		int p=buscarPosicionAlumnoDTO(lu);
		if(p!=-1) {
			todosLosAlumnos.get(p).setEstado(false);
			alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(todosLosAlumnos.get(p)));
		}
	}

	@Override
	public void modificarAlumnoDTO(AlumnoDTO m) {
		// TODO Auto-generated method stub
		List<AlumnoDTO> todosLosAlumnos = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
		int p=buscarPosicionAlumnoDTO(m.getLu());
		if (p!=-1) {
			todosLosAlumnos.set(p,m);
			
			alumnoRepository.save(alumnoMapDTO.convertirAlumnoDTOAAlumno(todosLosAlumnos.get(p)));
		}
	}
	
	@Override
	public int buscarPosicionAlumnoDTO(String lu) {
		// TODO Auto-generated method stub
		int j=-1;
		List<AlumnoDTO> todosLosAlumnos = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true)); 
		for(int i=0; i< todosLosAlumnos.size(); i++) {
			AlumnoDTO alumnoDTO = todosLosAlumnos.get(i);
			if(alumnoDTO.getLu().equals(lu)) {
				 j=i;
				break;
			}
		}
		return j;
	}
	
	@Override
	public AlumnoDTO buscarAlumnoDTO(String lu) {
		// TODO Auto-generated method stub
		List<AlumnoDTO> alumnos = alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
		int p=buscarPosicionAlumnoDTO(lu);
		return (p!=-1) ? alumnos.get(p) : null;
	}

}
