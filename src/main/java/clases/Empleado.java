package clases;
import java.time.LocalDate;
import java.util.Scanner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Empleado {
	 int idEmpleado;
	 String cuitEmpleado;
	 String nombreEmpleado;
	 String apellidoEmpleado;
	 String direccionEmpleado;
	 int telefonoEmpleado;	
	 LocalDate altaEmpleado = LocalDate.now();
	 String areaEmpleado; //se puede mejorar con List o array o enum

 /*public static Empleado altaEmpleado(){
		Scanner entrada = new Scanner(System.in);
		System.out.println();
		boolean validaEntrada = true;
		String cuit=" ", nom=" ",ape="",dire="",cel="",mail="",area="";
		
		System.out.println("*****INGRESE LOS SIGUIENTES DATOS DEL EMPLEADO*****");
	    System.out.println("***********RESPETANDO LAS INDICACIONES*************");
		while (validaEntrada) {
		      System.out.println("Ingrese el CUIT/DNI del Empleado: ");
		      cuit = entrada.nextLine();
		      if (cuit.isEmpty())
		       validaEntrada=true;
		      else validaEntrada=false;
		}
		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese el NOMBRE del Empleado: ");
			nom = entrada.nextLine();
			if (nom.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}
		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese el APELLIDO del Empleado: ");
			ape = entrada.nextLine();
			if (ape.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}
		
		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese la DIRECCION del Empleado: ");
			dire = entrada.nextLine();
			if (dire.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}
		
		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese el CELULAR del Empleado: ");
			cel = entrada.nextLine();
			if (cel.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}
		
		
		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese el MAIL del Empleado: ");
			mail = entrada.nextLine();
			if (mail.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}

		validaEntrada=true;
		while (validaEntrada) {
			System.out.println("Ingrese el Area(RRHH-COMERCIAL-MATENIMIENTO-TECNICO) del Empleado: ");
			area = entrada.nextLine();
			if (area.isEmpty())
		       validaEntrada=true;
		    else validaEntrada=false;
		}
		
		
		Empleado emp1 = new Empleado(1,cuit,nom,ape,dire,cel,area);
		
		System.out.println(emp1.toString());

		entrada.close();
		return emp1;
 	
 }
*/
public Empleado(int id, String cuit, String nombre, String apellido, String direccion, int telefono,
		LocalDate altaEmpleado2) {
	  idEmpleado= id;
	  cuitEmpleado= cuit;
	  nombreEmpleado= nombre;
	  apellidoEmpleado= apellido;
	  direccionEmpleado=direccion;
	  telefonoEmpleado= telefono;	
	  altaEmpleado = altaEmpleado2;
	  

}
}
