package clases;
import java.time.LocalDate;
import java.util.Scanner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Incidente {
	
	private int idIncidente;
	private Integer[] idEmpleado;
	private int idCliente;
	private int idServicio;
	private int idTecnico;
	private final LocalDate FechaIncidente = LocalDate.now();
	private String horasHasta;
	private String estado;
	public void setIdEmpleado(Integer[] int1) {
		idEmpleado=int1;
		
	}
	public void setIdEmpleado(int int1) {
		// TODO Esbozo de método generado automáticamente
		
	}
	
	
	/*public static Incidente altaIncidente(){
		
		System.out.println("*****INGRESE LOS SIGUIENTES DATOS DEL INCIDENTE*****");
	    System.out.println("***********RESPETANDO LAS INDICACIONES*************");
		Scanner entrada = new Scanner(System.in);
		System.out.println();
		System.out.println("ID Empleado: ");
		Integer empleado = Integer.parseInt(entrada.nextLine());
		System.out.println("ID Cliente: ");
		int cliente = Integer.parseInt(entrada.nextLine());
		System.out.println("ID Soporte: ");
		int soporte = Integer.parseInt(entrada.nextLine());
		System.out.println("ID tecnico: ");
		int tecnico = Integer.parseInt(entrada.nextLine());
		entrada.nextLine();
		System.out.println("Fecha de resolución:dd/mm/aaaa ");
		String fechaReso = entrada.nextLine();
		System.out.println("Horas Hasta resolver, en (hs): ");
		String colchon = entrada.nextLine();
		
		//en alta de incidente el estado por defecto sería REPORTADO
		String estado = "REPORTADO";
		
		
		Incidente inc1 = new Incidente(1,empleado,cliente,soporte,tecnico,fechaReso,colchon);
			
		//System.out.println(inc1.toString());

		entrada.close();
		return inc1;
	}*/
	
	
	
	
}
