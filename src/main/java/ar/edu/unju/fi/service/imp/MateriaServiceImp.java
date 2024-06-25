package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;
import ar.edu.unju.fi.map.MateriaMapDTO;
import ar.edu.unju.fi.repository.MateriaRepository;
import ar.edu.unju.fi.service.MateriaService;

@Service
public class MateriaServiceImp implements MateriaService{
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	MateriaMapDTO materiaMapDTO;

	@Override
	public void guardarMateria(MateriaDTO m) {
		// TODO Auto-generated method stub
		m.setEstadoMateria(true);
		materiaRepository.save(materiaMapDTO.convertirMateriaDTOAMateria(m));
	}

	@Override
	public List<MateriaDTO> mostrarMaterias() {
		// TODO Auto-generated method stub
		return materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));		
	}

	@Override
	public int buscarPosicionMateriaDTO(int codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> materias = materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true)); 
		int alto=materias.size()-1,bajo=0,central,p=-1;	
		while(p==-1 && bajo<=alto) {
			central=(bajo+alto)/2;
			if (codigo==materias.get(central).getCodigoMateria()) {
				p=central;
			}
			else {
				if (codigo<materias.get(central).getCodigoMateria()) {
				    alto=central-1;
				} else {
				    bajo=central+1;
				}	
			}
		}
		return p;
	}

	@Override
	public MateriaDTO buscarMateriaDTO(int codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> materias = materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));  
		int p=buscarPosicionMateriaDTO(codigo);
		return (p!=-1) ? materias.get(p) : null;
	}

	@Override
	public void borrarMateriaDTO(int codigo) {
		// TODO Auto-generated method stub
		List<MateriaDTO> materias = materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));  
		int p=buscarPosicionMateriaDTO(codigo);
		if (p!=-1) {
			materias.get(p).setEstadoMateria(false);
			materiaRepository.save(materiaMapDTO.convertirMateriaDTOAMateria(materias.get(p)));
		}
	}

	@Override
	public void modificarMateriaDTO(MateriaDTO m) {
		// TODO Auto-generated method stub
		List<MateriaDTO> materias = materiaMapDTO.convertirListaMateriasAListaMateriasDTO(materiaRepository.findMateriaByEstado(true));  
		int p=buscarPosicionMateriaDTO(m.getCodigoMateria());
		if (p!=-1) {
			materias.set(p,m);
			materiaRepository.save(materiaMapDTO.convertirMateriaDTOAMateria(materias.get(p)));
		}
	}
	
}
