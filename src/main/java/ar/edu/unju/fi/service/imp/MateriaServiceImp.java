package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MateriaServiceImp implements MateriaService{
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;

	@Override
	public void guardarMateria(Materia m) {//guarda una nueva materia completamente
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> guardarMateria");
		log.info("METHOD: guardarMateria()");
		log.info("INFO: Guardando Materia con codigo {}", m.getCodigo());
		//materia activa
		m.setEstado(true);
		materiaRepository.save(m);
	}

	@Override
	public List<MateriaDTO> mostrarMateriasDTO() {//listado de materiasDTO con menos atributos
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> mostrarMateriasDTO");
		log.info("METHOD: mostrarMateriasDTO()");
		//conversión de listados
		return materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));		
	}

	@Override
	public int buscarPosicionMateria(int codigo) {//búsqueda binaria
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> buscarPosicionMateria");
		log.info("METHOD: buscarPosicionMateria()");
		//listado de materias activas
		List<Materia> materias = materiaRepository.findMateriaByEstado(true); 
		int alto=materias.size()-1,bajo=0,central,p=-1;	
		while(p==-1 && bajo<=alto) {
			central=(bajo+alto)/2;
			if (codigo==materias.get(central).getCodigo()) {
				p=central;
			}
			else {
				if (codigo<materias.get(central).getCodigo()) {
				    alto=central-1;
				} else {
				    bajo=central+1;
				}	
			}
		}
		return p;//retorna indices
	}

	@Override
	public Materia buscarMateria(int codigo) {//retorna un objeto de tipo materia
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> buscarMateria");
		log.info("METHOD: buscarMateria()");
		log.info("INFO: Buscando materia con codigo {}", codigo);
		//listado de materias activas
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(codigo);
		return (p!=-1) ? materias.get(p) : null;
	}

	@Override
	public void borrarMateria(int codigo) {//borrado lógico
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> borrarMateria");
		log.info("METHOD: borrarMateria()");
		log.info("INFO: Borrando mateatia con codigo {}", codigo);
		//listado de materias activas
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(codigo);
		if (p!=-1) {
			materias.get(p).setEstado(false);
			materiaRepository.save(materias.get(p));
		}
	}

	@Override
	public void modificarMateria(Materia m) {//modifica una materia existente reemplazándola por otra
		// TODO Auto-generated method stub
		log.info("SERVICE: MateriaServiceImp -> modificarMateria");
		log.info("METHOD: modificarMateria()");
		log.info("INFO: Modificando carrera con codigo {}", m.getCodigo());
		//listado de materias activas
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(m.getCodigo());
		if (p!=-1) {
			materias.set(p,m);
			materiaRepository.save(materias.get(p));
		}
	}
	
}
