package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.MateriaDTO;

import ar.edu.unju.fi.service.MateriaService;

@Controller
public class MateriaController {
	
	@Autowired
	MateriaDTO nuevaMateriaDTO;
	
	@Autowired
	MateriaService materiaService;
	
	@GetMapping("/formularioMateria")
	public ModelAndView getFormMateria() {
		ModelAndView modelView=new ModelAndView("formMateria");
		//agregar el objeto
		modelView.addObject("nuevaMateria",nuevaMateriaDTO);
		modelView.addObject("flag",false);
		return modelView;
	}
	
	@PostMapping("/guardarMateria")
	public ModelAndView saveMateria(@ModelAttribute("nuevaCarrera") MateriaDTO m) {
		//guardar
		materiaService.guardarMateria(m);
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		return modelView;
	}
	
	@GetMapping("/mostrarMaterias")
	public ModelAndView listarLasMaterias() {
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		return modelView;
	}
	
	@GetMapping("/modificarMateria/{codigo}")
	public ModelAndView editarMateria(@PathVariable(name="codigo") Integer codigo) {
		//buscar
		MateriaDTO m = materiaService.buscarMateriaDTO(codigo);
		//mostrar el nuevo formulario
		ModelAndView modelView=new ModelAndView("formMateria");	
		modelView.addObject("nuevaMateria",m);
		modelView.addObject("flag",true);
		return modelView;
	}
	
	@PostMapping("/modificarMateria")
	public ModelAndView modificarMateriaListado(@ModelAttribute("nuevaMateria") MateriaDTO m) {
		//entidades a guardar		
		materiaService.modificarMateriaDTO(m);
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());
		return modelView;
	}
	
	@GetMapping("/eliminarMateria/{codigo}")
	public ModelAndView borrarMateriaListado(@PathVariable(name="codigo") Integer codigo) {
		//borrar
		materiaService.borrarMateriaDTO(codigo);
		//mostrar el nuevo listado
		ModelAndView modelView=new ModelAndView("listaDeMaterias");
		modelView.addObject("listadoMaterias",materiaService.mostrarMaterias());	
		return modelView;
	}
	
}