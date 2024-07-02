package ar.edu.unju.fi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Component
@Entity
@Table(name="carreras")
public class Carrera {
	
	@Id
	@NotBlank(message="Ingresar código de la Carrera")
	@Size(min=1, max=10,message="longitud de código no valida")
	private String codigo;
	
	@NotBlank(message="Ingresar Nombre de la Carrera")
	@Size(min=3, max=20,message="El nombre debe contener minimo 3 Caracteres y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Solo se debe ingresar Letras")
	private String nombre;
	
	@NotNull
	@Min(value=3, message=" Se requiere un número minimo de 3")
	@Max(value=5, message=" Se requiere un numero maximo de 5")
	private int duracion;

	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
	private List<Materia> materias;

	private boolean estado;
}