package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.model.Carrera;

@Service
public interface CarreraService {
	public int buscarPosicionCarrera(String codigo);
	public Carrera buscarCarrera(String codigo);
	public void borrarCarrera(String codigo);
	public void modificarCarrera(Carrera c);
	public void guardarCarrera(Carrera c);
	public List<CarreraDTO> mostrarCarrerasDTO();
	public List<Carrera> mostrarCarreras();
}
