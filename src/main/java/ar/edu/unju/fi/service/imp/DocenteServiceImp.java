package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.DocenteDTO;
import ar.edu.unju.fi.map.DocenteMapDTO;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.repository.DocenteRepository;
import ar.edu.unju.fi.service.DocenteService;

@Service
public class DocenteServiceImp implements DocenteService {

	@Autowired
	DocenteRepository docenteRepository;

	@Autowired
	DocenteMapDTO docenteMapDTO;

	@Override
	public void guardarDocente(Docente docente) {
		// TODO Auto-generated method stub
		docente.setEstado(true);
		docenteRepository.save(docente);

	}

	@Override
	public List<DocenteDTO> mostrarDocentesDTO() {
		// TODO Auto-generated method stub

		// return docenteRepository.findAll();
		return docenteMapDTO.convertirListaDocentesAListaDocentesDTO(docenteRepository.findDocenteByEstado(true));
	}

	@Override
	public void borrarDocente(String legajo) {
		// TODO Auto-generated method stub
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(legajo)) {
				d.setEstado(false);
				docenteRepository.save(d);
				break;
			}
		}
	}

	@Override
	public void modificarDocente(Docente docenteMod) {
		// TODO Auto-generated method stub
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(docenteMod.getLegajo())) {
				todosLosDocentes.set(i, docenteMod);
				todosLosDocentes.get(i).setEstado(true);
				docenteRepository.save(todosLosDocentes.get(i));

				break;
			}
		}

	}

	@Override
	public Docente buscarDocente(String legajo) {
		List<Docente> todosLosDocentes = docenteRepository.findAll();
		for (int i = 0; i < todosLosDocentes.size(); i++) {
			Docente d = todosLosDocentes.get(i);
			if (d.getLegajo().equals(legajo)) {
				return d;
			}
		}
		return null;
	}

}
