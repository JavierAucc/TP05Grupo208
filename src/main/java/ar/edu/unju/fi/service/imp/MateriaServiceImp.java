package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService{
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;

	@Override
	public void guardarMateria(Materia m) {
		// TODO Auto-generated method stub
		m.setEstado(true);
		materiaRepository.save(m);
	}

	@Override
	public List<MateriaDTO> mostrarMateriasDTO() {
		// TODO Auto-generated method stub
		return materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));		
	}

	@Override
	public int buscarPosicionMateria(int codigo) {
		// TODO Auto-generated method stub
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
		return p;
	}

	@Override
	public Materia buscarMateria(int codigo) {
		// TODO Auto-generated method stub
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(codigo);
		return (p!=-1) ? materias.get(p) : null;
	}

	@Override
	public void borrarMateria(int codigo) {
		// TODO Auto-generated method stub
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(codigo);
		if (p!=-1) {
			materias.get(p).setEstado(false);
			materiaRepository.save(materias.get(p));
		}
	}

	@Override
	public void modificarMateria(Materia m) {
		// TODO Auto-generated method stub
		List<Materia> materias = materiaRepository.findMateriaByEstado(true);  
		int p=buscarPosicionMateria(m.getCodigo());
		if (p!=-1) {
			materias.set(p,m);
			materiaRepository.save(materias.get(p));
		}
	}
	
}
