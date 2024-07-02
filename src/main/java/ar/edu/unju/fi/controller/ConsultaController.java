package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.map.AlumnoMapDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;


@Controller
public class ConsultaController {
	
	@Autowired
	CarreraService carreraService;
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	MateriaService materiaService;
	@Autowired
	AlumnoMapDTO alumnoMapDTO;
	
	@Autowired
	Alumno nuevoAlumno;

	@Autowired
	Materia nuevaMateria;
	
	
	@GetMapping("/consultarAlumnos/{lu}")
	public ModelAndView consultarLosAlumnos(@PathVariable(name="lu") String lu) {
		ModelAndView modelView = new ModelAndView("ListaDeAlumnosDTO");
		//return alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoRepository.findAlumnoByEstado(true));
		//modelView.addObject("listadoAlumnos", alumnoService.filtrarAlumnosPorCarrera(lu));
		modelView.addObject("listadoAlumnos",alumnoMapDTO.convertirListaAlumnosAListaAlumnosDTO(alumnoService.filtrarAlumnosPorCarrera(lu)));
		return modelView;
	}
	
	@GetMapping("/formularioInscripcion") // inscribe Alumno en Materias
	public ModelAndView getFormAlumnoInscripcion() {
		ModelAndView modelView = new ModelAndView("formAlumnoInscripcion");		
		modelView.addObject("nuevoAlumno", nuevoAlumno);
		modelView.addObject("nuevaMateria", nuevaMateria);
		modelView.addObject("listadoMaterias", materiaService.mostrarMateriasDTO());
		return modelView;
	}
	
	@PostMapping("/inscribirAlumno")
	public ModelAndView formularioInscripcion(@ModelAttribute("nuevoAlumno") Alumno alumno, @ModelAttribute("nuevaMateria") Materia materia) {
		ModelAndView modelView = new ModelAndView("ListaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumno());
		try {
			if (alumnoService.buscarAlumno(alumno.getLu())!=null){
				alumnoService.inscribirAlumno(alumnoService.buscarAlumno(alumno.getLu()), materiaService.buscarMateria(materia.getCodigo()));			
			}
		}
		catch( Exception e){
			boolean errors = true;
			modelView.addObject("errors", errors);
			modelView.addObject("cargaAlumnoErrorMessage", " Error al cargar en la BD " + e.getMessage());
			System.out.println(e.getMessage());
		}
		return modelView;	
	}
	
	@GetMapping("/filtrarAlumnos/{codigo}")
	public ModelAndView filtrarLosAlumnos(@PathVariable(name="codigo") Integer codigo) {
		ModelAndView modelView = new ModelAndView("ListaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.filtrarAlumnos(codigo));
		return modelView;
	}
	
	
	
	
	
	
}
