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
@FieldDefaults(level=AccessLevel.PRIVATE)

public class Cliente {
	 
	 int cuitCliente;	 
	 String nombreCliente;
	 String apellidoCliente;
	 String direccionCliente;
	 int idServicio;
	 int telefonoCliente;	 
	 final LocalDate altaCliente = LocalDate.now();
	 
	 
public static Cliente altaCliente() {
	System.out.println("\n*****INGRESE LOS SIGUIENTES DATOS DEL CLIENTE*****");
    System.out.println("***********RESPETANDO LAS INDICACIONES*************");
		Scanner entrada = new Scanner(System.in);
		System.out.println("CUIT: ");
		int cuit = Integer.parseInt(entrada.nextLine());	
		System.out.println("NOMBRE: ");
		String nom = entrada.nextLine();
		System.out.println("APELLIDO: ");
		String ape = entrada.nextLine();
		System.out.println("DIRECCION: ");
		String dire = entrada.nextLine();
		System.out.println("CELULAR: ");
		int tel = Integer.parseInt(entrada.nextLine());
		
		System.out.println();
		System.out.println("Servicio (CODIGO DEL SOPORTE): ");
		int idServi = Integer.parseInt(entrada.nextLine());
			
		Cliente cliente1 = new Cliente(cuit,nom,ape,dire,tel,idServi);
				
		entrada.close();
		return cliente1;
		}

}
