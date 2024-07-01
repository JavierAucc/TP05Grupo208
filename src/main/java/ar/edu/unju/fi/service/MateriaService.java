package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.model.Materia;

@Service
public interface MateriaService {
	public void guardarMateria(Materia m);
	public List<MateriaDTO> mostrarMateriasDTO();
	public int buscarPosicionMateria(int codigo);
	public Materia buscarMateria(int codigo);
	public void borrarMateria(int codigo);
	public void modificarMateria(Materia m);
}