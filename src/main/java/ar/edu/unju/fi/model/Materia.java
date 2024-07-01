package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Component
@Entity
public class Materia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=100, message="Este campo debe tener entre entre 3 y 100 caracteres.")
	private String nombre;
	@NotBlank(message="Este campo es obligatorio.")
	@Size(min=3, max=150, message="Este campo debe tener entre entre 3 y 150 caracteres.")
	private String curso;
	@NotNull(message="Este campo es obligatorio.")
	@Min(value=60, message="Este campo requiere un número mínimo de 60")
	@Max(value=240, message="Este campo requiere un número máximo de 240")
	private int duracion;
	private boolean modalidad;
	private boolean estado;
}
