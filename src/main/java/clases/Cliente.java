package clases;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.parser.Parser;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)

public class Cliente {
	 
	 int cuitCliente;	 
	 String nombreCliente;
	 String apellidoCliente;
	 String direccionCliente;
	 int idServicio;
	 int telefonoCliente;	 
	 final LocalDate altaCliente = LocalDate.now();
	 

}
