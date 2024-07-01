package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	
	@Override
	public void guardarCarrera(Carrera carrera) {
		// TODO Auto-generated method stub
		carrera.setEstado(true);
		carreraRepository.save(carrera);	
	}

	@Override
	public List<CarreraDTO> mostrarCarrerasDTO() {
		// TODO Auto-generated method stub
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
	}
	
	@Override
	public List<Carrera> mostrarCarreras() {
		// TODO Auto-generated method stub
		return carreraRepository.findCarreraByEstado(true);
	}

	
	@Override
	public int buscarPosicionCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=-1;
		for (int i=0; i<carreras.size() && p==-1;i++) {
			if (carreras.get(i).getCodigo().equals(codigo)) {
				p=i;
			}
		}			
		return p;
	}

	@Override
	public Carrera buscarCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true); 
		int p=buscarPosicionCarrera(codigo);
		return (p!=-1) ? carreras.get(p) : null;
	}
	
	@Override
	public void modificarCarrera(Carrera c) {
		// TODO Auto-generated method stub
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=buscarPosicionCarrera(c.getCodigo());
		if (p!=-1) {
			carreras.set(p,c);
			carreraRepository.save(carreras.get(p));
		}
	}

	@Override
	public void borrarCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<Carrera> carreras = carreraRepository.findCarreraByEstado(true);
		int p=buscarPosicionCarrera(codigo);
		if (p!=-1) {
			carreras.get(p).setEstado(false);
			carreraRepository.save(carreras.get(p));
		}
	}

	

}