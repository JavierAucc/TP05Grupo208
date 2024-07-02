package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocenteServiceImp implements DocenteService {

	@Autowired
	DocenteRepository docenteRepository;

	@Autowired
	DocenteMapDTO docenteMapDTO;

	@Override
	public void guardarDocente(Docente docente) {
		log.info("SERVICE: DocenteServiceImp -> guardarDocente");
		log.info("METHOD: guardarDocente()");
		log.info("INFO: Guardando Docente con legajo {}", docente.getLegajo());

		docente.setEstado(true);
		docenteRepository.save(docente);
		log.info("INFO: Docente guardado exitosamente");
	}

	@Override
	public List<DocenteDTO> mostrarDocentesDTO() {
		log.info("SERVICE: DocenteServiceImp -> mostrarDocentesDTO");
		log.info("METHOD: mostrarDocentesDTO");
		return docenteMapDTO.convertirListaDocentesAListaDocentesDTO(docenteRepository.findDocenteByEstado(true));
	}

	@Override
	public void borrarDocente(String legajo) {
		log.info("SERVICE: DocenteServiceImp -> borrarDocente");
		log.info("METHOD: borrarDocente()");
		log.info("INFO: Borrando docente con legajo {}", legajo);
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(legajo)) {
				d.setEstado(false);
				docenteRepository.save(d);
				log.info("INFO: Docente con legajo {}", legajo, "borrado existosamente");
				break;
			}
		}
	}

	@Override
	public void modificarDocente(Docente docenteMod) {
		log.info("SERVICE: DocenteServiceImp -> modficarDocente()");
		log.info("INFO: Modificando docente con legajo {}", docenteMod.getLegajo());
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(docenteMod.getLegajo())) {
				todosLosDocentes.set(i, docenteMod);
				todosLosDocentes.get(i).setEstado(true);
				docenteRepository.save(todosLosDocentes.get(i));
				log.info("INFO: Docente modificado exitosamente");
				break;
			}
		}

	}

	@Override
	public Docente buscarDocente(String legajo) {
		log.info("SERVICE: DocenteServiceImp -> buscarDocente()");
		log.info("INFO: Buscando docente con legajo {}", legajo);
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(legajo)) {
				log.info("INFO: Docente encontrado con legajo {}", legajo);
				return d;
			}
		}
		return null;
	}

}
