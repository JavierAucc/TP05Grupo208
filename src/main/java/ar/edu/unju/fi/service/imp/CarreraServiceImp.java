package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> guardarCarrera");
		log.info("METHOD: guardarCarrera()");
		log.info("INFO: Guardando Carrera con codigo {}", carrera.getCodigo());
		carrera.setEstado(true);
		carreraRepository.save(carrera);	
	}

	@Override
	public List<CarreraDTO> mostrarCarrerasDTO() {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> mostrarCarreraDTO");
		log.info("METHOD: mostrarCarreraDTO()");
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
	}
	
	@Override
	public List<Carrera> mostrarCarreras() {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> mostrarCarrera");
		log.info("METHOD: mostrarCarrera()");
		return carreraRepository.findCarreraByEstado(true);
	}

	
	@Override
	public int buscarPosicionCarrera(String codigo) {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> buscarPosicionCarrera");
		log.info("METHOD: buscarPosicionCarrera()");
		log.info("INFO: Buscando posicion con codigo {}", codigo);
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=-1;
		for (int i=0; i<carreras.size() && p==-1;i++) {
			if (carreras.get(i).getCodigo().equals(codigo)) {
				p=i;
				log.info("INFO: Posicion encontrada {}", codigo);
			}
		}			
		return p;
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> buscarCarrera");
		log.info("METHOD: buscarCarrera()");
		log.info("INFO: Buscando carrera con codigo {}", codigo);
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true); 
		int p=buscarPosicionCarrera(codigo);
		log.info("INFO: Carrera encontrada con codigo {}", codigo);
		return (p!=-1) ? carreras.get(p) : null;
	}
	
	@Override
	public void modificarCarrera(Carrera c) {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> modificarCarrera");
		log.info("METHOD: modificarCarrera()");
		log.info("INFO: Modificando carrera con codigo {}", c.getCodigo());
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=buscarPosicionCarrera(c.getCodigo());
		if (p!=-1) {
			carreras.set(p,c);
			carreraRepository.save(carreras.get(p));
			log.info("INFO: Carrera modificada con exito");
		}
	}

	@Override
	public void borrarCarrera(String codigo) {
		// TODO Auto-generated method stub
		log.info("SERVICE: CarreraServiceImp -> borrarCarrera");
		log.info("METHOD: borrarCarrera()");
		log.info("INFO: Borrando carrera con codigo {}", codigo);
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=buscarPosicionCarrera(codigo);
		if (p!=-1) {
			carreras.get(p).setEstado(false);
			carreraRepository.save(carreras.get(p));
			log.info("INFO: Carrera con codigo {}, ", codigo, " borrado existosamente");
		}
	}

	

}