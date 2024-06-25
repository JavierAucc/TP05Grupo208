package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.CarreraDTO;
import ar.edu.unju.fi.service.CarreraService;

@Controller
public class CarreraController {

	@Autowired
	CarreraDTO nuevaCarreraDTO;
	
	@Autowired
	CarreraService carreraService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelView=new ModelAndView("formCarrera");
		//agregar el objeto
		modelView.addObject("nuevaCarrera",nuevaCarreraDTO);
		modelView.addObject("flag",false);
		return modelView;
	}

	@GetMapping("/mostrarCarreras")
	public ModelAndView listarLasCarreras() {
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		return modelView;
	}

	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView editarCarrera(@PathVariable(name="codigo") String codigo) {
		//buscar
		CarreraDTO c=carreraService.buscarCarreraDTO(codigo);
		//mostrar el nuevo formulario
		ModelAndView modelView=new ModelAndView("formCarrera");	
		modelView.addObject("nuevaCarrera",c);
		modelView.addObject("flag",true);
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView modificarCarreraListado(@ModelAttribute("nuevaCarrera") CarreraDTO c) {
		//guardar
		carreraService.modificarCarreraDTO(c);
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		return modelView;
	}

	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@ModelAttribute("nuevaCarrera") CarreraDTO c) {
		//carrera a guardar
		carreraService.guardarCarrera(c);

		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		return modelView;
	}

	@GetMapping("/eliminarCarrera/{codigo}")
	public ModelAndView borrarCarreraListado(@PathVariable(name="codigo") String codigo) {
		//borrar
		carreraService.borrarCarreraDTO(codigo);

		//mostrar el nuevo listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());		
		return modelView;
	}

}