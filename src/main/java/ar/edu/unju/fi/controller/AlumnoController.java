package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;



@Controller
public class AlumnoController {
	
	@Autowired
	AlumnoDTO nuevoAlumnoDTO;
	
	@Autowired
	AlumnoService alumnoService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		//vista formCarrera.html
		ModelAndView modelView = new ModelAndView("formAlumno");
		//agrega el objeto
		modelView.addObject("nuevoAlumno", nuevoAlumnoDTO);
		modelView.addObject("band",false);
		return modelView;
	}
	
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@ModelAttribute("nuevoAlumno") AlumnoDTO alumnoParaGuardar) {
		//guardar
		//ListadoAlumno.agregarAlumno(alumnoParaGuardar); //se agrega la alumno
		alumnoService.guardarAlumno(alumnoParaGuardar);
		//Una vez guardado se muestra la vista
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");//listaDeAlumnos es html		
		//modelView.addObject("listadoAlumno",ListadoAlumno.listarAlumnos()); //el objeto Listadoalumnos	
		modelView.addObject("listadoAlumno",alumnoService.mostrarAlumnoDTO());
		return modelView;
	}
		
	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView borrarAlumno(@PathVariable(name="lu") String lu) {
		//borrar
		//ListadoAlumno.eliminarAlumno(lu);
		alumnoService.borrarAlumnoDTO(lu);
		
		//mostrar el nuevo listado
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumno",alumnoService.mostrarAlumnoDTO());
		
		return modelView;
		}
	
	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView getModificarAlumno(@PathVariable(name="lu") String lu) {
		
		AlumnoDTO alumnoDTO = alumnoService.buscarAlumnoDTO(lu);

		ModelAndView modelView = new ModelAndView("formAlumno");
		
		modelView.addObject("nuevoAlumno", alumnoDTO);
		modelView.addObject("band",true);
		return modelView;
	}
	
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@ModelAttribute("alumno") AlumnoDTO alumnoModificado) {
		//ListadoAlumno.modificarAlumno(alumnoModificado);
		alumnoService.modificarAlumnoDTO(alumnoModificado);
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumno",alumnoService.mostrarAlumnoDTO());
		return modelView;
			
	}
	
	
	
	
	
	
}
