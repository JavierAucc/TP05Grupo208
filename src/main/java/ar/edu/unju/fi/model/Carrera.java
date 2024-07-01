package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Carrera {
	
	@Id
	@NotBlank(message="Ingresar código de la Carrera")
	@Size(min=1, max=10,message="longitud de código no valida")
	private String codigo;
	
	@NotBlank(message="Ingresar Nombre de la Carrera")
	@Size(min=3, max=20,message="El nombre debe contener minimo 3 Caracteres y 20 como maximo")
	@Pattern(regexp="[a-z A-Z]*",message="Solo se debe ingresar Letras")
	private String nombre;
	
	@NotNull(message="Debe Ingresar duración de la Carrera")
	@Min(value=3, message="Se requiere un número mínimo de 3")
	@Max(value=5, message="Se requiere un número máximo de 5")
	private int duracion;
	
	private boolean estado;

}