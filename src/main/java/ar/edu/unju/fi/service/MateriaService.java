package ar.edu.unju.fi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;

@Service
public interface MateriaService {
	public void guardarMateria(MateriaDTO m);
	public List<MateriaDTO> mostrarMaterias();
	public int buscarPosicionMateriaDTO(int codigo);
	public MateriaDTO buscarMateriaDTO(int codigo);
	public void borrarMateriaDTO(int codigo);
	public void modificarMateriaDTO(MateriaDTO m);
}