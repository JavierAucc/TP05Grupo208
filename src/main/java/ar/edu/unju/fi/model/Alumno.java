package ar.edu.unju.fi.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
@Table(name="alumnos")
public class Alumno {
	
	@Id
	@NotBlank(message="Debe ingresar Libreta Universitaria del Alumno")
	@Size(min=4, max=10,message="longitud de LU no valida")
	private String lu;
	
	@NotBlank(message="Debe ingresar DNI del Alumno")
	@Size(min=8, max=10,message="longitud del DNI no valida")
	@Pattern(regexp="[0-9]*",message="Solo se debe ingresar Numeros")
	private String dni;
	@NotBlank(message="Debe ingresar Nombre del Alumno")
	@Size(min=3, max=20,message="El nombre debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Solo se debe ingresar Letras")
	private String nombre;
	@NotBlank(message="Debe ingresar Apellido del Alumno")
	@Size(min=3, max=20,message="El Apellido debe contener como minimo 3 Caracteres como minimo y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Solo se debe ingresar Letras")
	private String apellido;
	@Email
	@NotBlank(message="Debe ingresar el Correo del Alumno")
	@Size(min=8, max=60,message="E-mail no valido")
	private String email;
	@Size(min=9, max=10,message="longitud no valida")
	@Pattern(regexp="[0-9]*",message="Solo se debe ingresar Numeros")
	private String telefono;
	@Past
	@NotNull
	private LocalDate fnacimiento;
	@NotBlank(message="Debe ingresar Domicilio del Alumno")
	@Size(min=8, max=15,message="longitud del Domicilio no valida")
	private String domicilio;
	
	private Boolean estado;
}