package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.CarreraDTO;

@Service
public interface CarreraService {
	public void guardarCarrera(CarreraDTO c);
	public List<CarreraDTO> mostrarCarreras();
	public int buscarPosicionCarrera(String codigo);
	public CarreraDTO buscarCarreraDTO(String codigo);
	public void borrarCarreraDTO(String codigo);
	public void modificarCarreraDTO(CarreraDTO c);
}
