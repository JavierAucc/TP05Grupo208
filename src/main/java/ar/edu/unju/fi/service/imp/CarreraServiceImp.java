package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.map.CarreraMapDTO;
import ar.edu.unju.fi.repository.CarreraRepository;
import ar.edu.unju.fi.service.CarreraService;

@Service
public class CarreraServiceImp implements CarreraService{
	
	@Autowired
	CarreraRepository carreraRepository;
	
	@Autowired
	CarreraMapDTO carreraMapDTO;
	@Override
	public void guardarCarrera(CarreraDTO carrera) {
		// TODO Auto-generated method stub
		carrera.setEstadoCarrera(true);
		carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carrera));		
	}

	@Override
	public List<CarreraDTO> mostrarCarreras() {
		// TODO Auto-generated method stub
		return carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
	}

	@Override
	public int buscarPosicionCarrera(String codigo) {
		// TODO Auto-generated method stub
		List<CarreraDTO> carreras = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
		int p=-1;
		for (int i=0; i<carreras.size() && p==-1;i++) {
			if (carreras.get(i).getCodigoCarrera().equals(codigo)) {
				p=i;
			}
		}			
		return p;
	}

	@Override
	public CarreraDTO buscarCarreraDTO(String codigo) {
		// TODO Auto-generated method stub
		List<CarreraDTO> carreras = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true)); 
		int p=buscarPosicionCarrera(codigo);
		return (p!=-1) ? carreras.get(p) : null;
	}
	
	@Override
	public void modificarCarreraDTO(CarreraDTO c) {
		// TODO Auto-generated method stub
		List<CarreraDTO> carreras = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
		int p=buscarPosicionCarrera(c.getCodigoCarrera());
		if (p!=-1) {
			carreras.set(p,c);
			carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreras.get(p)));
		}
	}

	@Override
	public void borrarCarreraDTO(String codigo) {
		// TODO Auto-generated method stub
		List<CarreraDTO> carreras = carreraMapDTO.convertirListaCarrerasAListaCarrerasDTO(carreraRepository.findCarreraByEstado(true));
		int p=buscarPosicionCarrera(codigo);
		if (p!=-1) {
			carreras.get(p).setEstadoCarrera(false);
			carreraRepository.save(carreraMapDTO.convertirCarreraDTOACarrera(carreras.get(p)));
		}
	}

}