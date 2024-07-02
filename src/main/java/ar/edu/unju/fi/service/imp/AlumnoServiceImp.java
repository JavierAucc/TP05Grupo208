package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.AlumnoService;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AlumnoServiceImp implements AlumnoService{

	
	@Autowired
	AlumnoRepository alumnoRepository;
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	@Autowired
	MateriaRepository materiaRepository;
	@Autowired
	CarreraRepository carreraRepository;
	
	@Override
	public void guardarAlumno(Alumno alumno) {
		// TODO Auto-generated method stub
		
		log.info("SERVICE: AlumnoServiceImp -> guardarAlumno ");
		log.info("METHOD: guardarAlumno()");
		log.info("INFO: Guardando Alumno con legajo {}", alumno.getLu());
		alumno.setEstado(true);
//		alumno.getCarrera().getAlumnos().add(alumno);
//		carreraRepository.save(alumno.getCarrera());
		alumnoRepository.save(alumno);
		log.info("INFO: Alumno guardado exitosamente");
	}

	@Override
	public List<AlumnoDTO> mostrarAlumnoDTO() {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> mostrarAlumnoDTO");
		log.info("METHOD: mostrarAlumnoDTO");
		return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
	}

	@Override
	public List<Alumno> mostrarAlumno() {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> mostrarAlumno");
		log.info("METHOD: mostrarAlumno");
		return alumnoRepository.findAlumnoByEstado(true);
	}

	@Override
	public void borrarAlumno(String lu) {
		// TODO Auto-generated method stub
		// List<Alumno> todosLosAlumnos = alumnoRepository.findAll();
		log.info("SERVICE: AlumnoServiceImp -> borrarAlumno");
		log.info("METHOD: borrarAlumno()");
		log.info("INFO: Borrando Alumno con lu {}", lu);
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true);
		int p = buscarPosicionAlumno(lu);
		if (p != -1) {
			todosLosAlumnos.get(p).setEstado(false);
			alumnoRepository.save(todosLosAlumnos.get(p));
			log.info("INFO: Alumno con lu {}, ", lu, " borrado existosamente");
		}
	}

	@Override
	public void modificarAlumno(Alumno m) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> modficarAlumno()");
		log.info("METHOD: modificarAlumno()");
		log.info("INFO: Modificando Alumno con lu {}", m.getLu());
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true);
		int p = buscarPosicionAlumno(m.getLu());
		if (p != -1) {
			todosLosAlumnos.set(p, m);

			alumnoRepository.save(todosLosAlumnos.get(p));
			log.info("INFO: Alumno modificado exitosamente");
		}
	}

	@Override
	public int buscarPosicionAlumno(String lu) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> buscarPosicionAlumno()");
		log.info("METHOD: buscarPosicionAlumno()");
		log.info("INFO: Buscando posicion con lu {}", lu);
		int j = -1;
		List<Alumno> todosLosAlumnos = alumnoRepository.findAlumnoByEstado(true);
		for (int i = 0; i < todosLosAlumnos.size(); i++) {
			Alumno alumno = todosLosAlumnos.get(i);
			if (alumno.getLu().equals(lu)) {
				j = i;
				log.info("INFO: Posicion encontrada {}", lu);
				break;
			}
		}
		return j;
	}

	@Override
	public Alumno buscarAlumno(String lu) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> buscarAlumno()");
		log.info("METHOD: buscarAlumno()");
		log.info("INFO: Buscando alumno con legajo {}", lu);
		List<Alumno> alumnos = alumnoRepository.findAlumnoByEstado(true);
		int p = buscarPosicionAlumno(lu);
		log.info("INFO: Alumno encontrado con legajo {}", lu);
		return (p != -1) ? alumnos.get(p) : null;
	}

	// operaciones materia-alumno
	@Override
	public List<Alumno> filtrarAlumnos(Integer codigo) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> filtrarAlumnos()");
		log.info("METHOD: filtrarAlumnos()");
		log.info("INFO: Filtrando alumnos por codigo{}", codigo);
		log.info("INFO: Alumno filtrado {}", codigo);
		return alumnoRepository.findByMateriasCodigo(codigo);
	}

	@Override
	public void inscribirAlumno(Alumno alumno, Materia materia) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> inscribirAlumno()");
		log.info("METHOD: inscribirAlumno()");
		alumno.getMaterias().add(materia);
		materia.getAlumnos().add(alumno);
		log.info("INFO: Inscribiendo Alumno a Materia{}", alumno.getLu(), " ", materia.getCodigo());
		alumnoRepository.save(alumno);
		materiaRepository.save(materia);
		log.info("INFO: Alumno inscripto a Materia correctamente {}");
	}

	@Override
	public List<Alumno> filtrarAlumnosPorCarrera(String lu) {
		// TODO Auto-generated method stub
		log.info("SERVICE: AlumnoServiceImp -> filtrarAlumnosPorCarrera()");
		log.info("METHOD: filtrarAlumnosPorCarrera()");
		log.info("INFO: Filtrando alumnos por carrera{}");
		return alumnoRepository.findByCarreraCodigo(lu);
	}

	
	
}
