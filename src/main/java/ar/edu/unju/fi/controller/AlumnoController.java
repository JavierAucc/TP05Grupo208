package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.DTO.AlumnoDTO;
import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.repository.AlumnoRepository;
import ar.edu.unju.fi.service.AlumnoService;
import ar.edu.unju.fi.service.CarreraService;
import ar.edu.unju.fi.service.MateriaService;
import jakarta.validation.Valid;



@Controller
public class AlumnoController {
	
	@Autowired
	Alumno nuevoAlumno;
	
	@Autowired
	AlumnoService alumnoService;
	@Autowired
	Materia nuevaMateria;
	@Autowired
	MateriaService materiaService;
	@Autowired
	CarreraService carreraService;
	
	@GetMapping("/formularioAlumno")
	public ModelAndView getFormAlumno() {
		ModelAndView modelView = new ModelAndView("formAlumno");
		//agrega el objeto
		modelView.addObject("nuevoAlumno", nuevoAlumno);
		modelView.addObject("band",false);
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras()); // para que muestra las carreras
		return modelView;
	}
	@GetMapping("/mostrarAlumnos")
	public ModelAndView listarAlumnos() {
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumno());

		return modelView;
	}
	@PostMapping("/guardarAlumno")
	public ModelAndView saveAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno nuevoAlumno, BindingResult resultado ) {
		//BindingResult es una interfaz que nos muestra q ocurrio cuando se produce el filtrado "Valid", captura el error
		//Una vez guardado se muestra la vista

		ModelAndView modelView = new ModelAndView("listaDeAlumnos");//listaDeAlumnos es html		
			try {
				if(resultado.hasErrors()) {
					modelView.setViewName("formAlumno");
					modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
				}
				else {
					nuevoAlumno.setCarrera(carreraService.buscarCarrera(nuevoAlumno.getCarrera().getCodigo()));
					alumnoService.guardarAlumno(nuevoAlumno);
					System.out.println("Alumno guardado correctamente  "+nuevoAlumno.getNombre());
					modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumno());
					
					
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
		
	@GetMapping("/borrarAlumno/{lu}")
	public ModelAndView borrarAlumno(@PathVariable(name="lu") String lu) {
		//borrar
		//ListadoAlumno.eliminarAlumno(lu);
		alumnoService.borrarAlumno(lu);
		
		//mostrar el nuevo listado
		ModelAndView modelView = new ModelAndView("listaDeAlumnos");
		modelView.addObject("listadoAlumnos",alumnoService.mostrarAlumno());
		
		return modelView;
		}
	
	@GetMapping("/modificarAlumno/{lu}")
	public ModelAndView getModificarAlumno(@PathVariable(name="lu") String lu) {
		
		Alumno alumno = alumnoService.buscarAlumno(lu);

		ModelAndView modelView = new ModelAndView("formAlumno");
		
		modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
		
		System.out.println("Alumno a modificar "+alumno.getNombre());
		modelView.addObject("nuevoAlumno", alumno);
		modelView.addObject("band",true);
		return modelView;
	}
	
	
	@PostMapping("/modificarAlumno")
	public ModelAndView modificarAlumno(@Valid @ModelAttribute("nuevoAlumno") Alumno alumnoModificado, BindingResult resultado) {
		
			ModelAndView modelView = new ModelAndView("listaDeAlumnos");
			
			try {
				if(resultado.hasErrors()) {
					modelView.setViewName("formAlumno");
					modelView.addObject("listadoCarreras",carreraService.mostrarCarreras());
					System.out.println("ERRRRRRRROOOOOOR");
				}
				else {
					//System.out.println("Alumno modificado correctamente  "+nuevoAlumno.getNombre());
					nuevoAlumno.setCarrera(carreraService.buscarCarrera(nuevoAlumno.getCarrera().getCodigo()));
					alumnoService.modificarAlumno(alumnoModificado);
					System.out.println("Alumno modificado correctamente  "+alumnoModificado.getNombre());
					modelView.addObject("listadoAlumnos", alumnoService.mostrarAlumno());
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
	//--------

	@GetMapping("/formularioInscripcion") // inscribe Alumno en Materias
		public ModelAndView getFormAlumnoInscripcion() {
			ModelAndView modelView = new ModelAndView("formAlumnoInscripcion");		
			modelView.addObject("nuevoAlumno", nuevoAlumno);
			modelView.addObject("nuevaMateria", nuevaMateria);
			modelView.addObject("listadoMaterias",materiaService.mostrarMateriasDTO());
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
