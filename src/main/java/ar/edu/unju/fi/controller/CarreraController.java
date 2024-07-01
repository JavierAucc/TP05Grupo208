package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.service.CarreraService;
import jakarta.validation.Valid;

@Controller
public class CarreraController {

	@Autowired
	Carrera nuevaCarrera;
	
	
	@Autowired
	Carrera nuevaCarrera;
	
	@Autowired
	CarreraService carreraService;

	@GetMapping("/formularioCarrera")
	public ModelAndView getFormCarrera() {
		ModelAndView modelView=new ModelAndView("formCarrera");
		//agregar el objeto
		modelView.addObject("nuevaCarrera",nuevaCarrera);
		modelView.addObject("flag",false);
		return modelView;
	}

	@GetMapping("/mostrarCarreras")
	public ModelAndView listarLasCarreras() {
		//mostrar el listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarrerasDTO());
		return modelView;
	}

	@GetMapping("/modificarCarrera/{codigo}")
	public ModelAndView editarCarrera(@PathVariable(name="codigo") String codigo) {
		//buscar
		Carrera c=carreraService.buscarCarrera(codigo);
		//mostrar el nuevo formulario
		ModelAndView modelView=new ModelAndView("formCarrera");	
		modelView.addObject("nuevaCarrera",c);
		modelView.addObject("flag",true);
		return modelView;
	}

	@PostMapping("/modificarCarrera")
	public ModelAndView modificarCarreraListado(@Valid @ModelAttribute("nuevaCarrera") Carrera c, BindingResult resultado)  {
		ModelAndView modelView=new ModelAndView();
		try {
			if (resultado.hasErrors()) {
		    	 modelView.setViewName("formCarrera");
			}
			else {
		    	 carreraService.modificarCarrera(c);
		    	 System.out.println("Carrera modificada");
			}
		}
		catch(Exception e) {
			boolean error = true;
			modelView.addObject("error",error);
			modelView.addObject("cargarCarreraErrorMessage", "Error de carga en la BD" + e.getMessage());
			 System.out.println(e.getMessage());
		}
		if(resultado.hasErrors()==false) {
			  modelView.setViewName("listaDeCarreras");
			  modelView.addObject("listadoCarreras",carreraService.mostrarCarrerasDTO());
		  }
		return modelView;
	}

	@PostMapping("/guardarCarrera")
	public ModelAndView saveCarrera(@Valid @ModelAttribute("nuevaCarrera") Carrera c, BindingResult resultado) {
		ModelAndView modelView=new ModelAndView();
		try {
		     if (resultado.hasErrors()) {
		    	 modelView.setViewName("formCarrera");
		     }
		     else {
		    	 carreraService.guardarCarrera(c);
		    	 System.out.println("Carrera guardada");
		     }
		 }
		 catch(Exception e) {
			 boolean error = true;
			 modelView.addObject("error",error);
			 modelView.addObject("cargarCarreraErrorMessage", "Error de carga en la BD" + e.getMessage());
			 System.out.println(e.getMessage());
		 }
		  if(resultado.hasErrors()==false) {
			  modelView.setViewName("listaDeCarreras");
			  modelView.addObject("listadoCarreras",carreraService.mostrarCarrerasDTO());
		  }

		return modelView;
	}

	@GetMapping("/eliminarCarrera/{codigo}")
	public ModelAndView borrarCarreraListado(@PathVariable(name="codigo") String codigo) {
		//borrar
		carreraService.borrarCarrera(codigo);

		//mostrar el nuevo listado
		ModelAndView modelView=new ModelAndView("listaDeCarreras");
		modelView.addObject("listadoCarreras",carreraService.mostrarCarrerasDTO());		
		return modelView;
	}
}