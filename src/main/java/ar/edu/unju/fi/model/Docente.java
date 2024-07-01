package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Component
@Entity
public class Docente {
	@Id
	private String legajo;

	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 3, max = 30, message = "Este campo debe tener entre 3 y 30 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
	private String nombre;

	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 3, max = 30, message = "Este campo debe tener entre 3 y 30 caracteres.")
	@Pattern(regexp = "[a-z A-Z]*", message = "Este campo solo debe contener Letras.")
	private String apellido;

	@Email
	@NotBlank(message = "Este campo es Obligatorio.")
	@Size(min = 3, max = 60, message = "Este campo debe tener entre 3 y 60 caracteres.")
	private String email;

	@Size(min = 3, max = 15, message = "Este campo debe tener entre 3 y 15 caracteres.")
	@Pattern(regexp = "[0-9]*", message = "Este campo solo debe contener numeros")
	private String telefono;

	private Boolean estado;

}
