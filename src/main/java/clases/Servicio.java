package clases;
import java.time.LocalDate;
import java.util.Scanner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE) //define private a todos los atributos
public class Servicio {
	int idServicio;
	String tipoServicio;//hard soft etc
	String descripcion;
	int horasResolucion;
	String complejidadServicio;
	String estadoServicio;//activo / inactivo
		
/*public static Servicio altaSoporte(){
	
	///AGREGAR VALIDACION DE INGRESO DE DATOS COMO EN EMPLEADOS
	
	Scanner entrada1 = new Scanner(System.in);
	System.out.println("*****INGRESE LOS SIGUIENTES DATOS DEL SOPORTE/SERVICIO*****");
    System.out.println("**************RESPETANDO LAS INDICACIONES**************");
	System.out.println("CODIGO (10 caract max): ");
	int idServicio = Integer.parseInt(entrada1.nextLine());
	System.out.println("TIPO SOFT/HARD: ");
	String tipo =entrada1.nextLine();
	System.out.println("DESCRIPCION: ");
	String descripcion = entrada1.nextLine();
	System.out.println("TIEMPO DE RESOLUCION en horas: ");
	int horaResu = Integer.parseInt(entrada1.nextLine());
	System.out.println("COMPLEJIDAD ALTA/MEDIA/BAJA: ");
	String complejidad = entrada1.nextLine();
	
	System.out.println("ESTADO DISPONIBLE/NO DISPONIBLE: ");
	String estado = entrada1.nextLine();
	
	//-----------ESTA LINEA HAY QUE ELIMINAR, SOLO ESTÃ? PARA CONTROL EN DESARROLLO
	//Servicio sop1 = new Servicio(idServicio,tipo,descripcion,horaResu,complejidad,estado);
		
	//System.out.println(sop1.toString());

	entrada1.close();
	//return sop1;
}
*/
public Servicio(int id, String servicio, String descripcion2, String complejidad, int horasResulucion, String estado) {
	 idServicio= id;
	 tipoServicio= servicio;//hard soft etc
	 descripcion= descripcion2;
	 horasResolucion= horasResulucion;
	 complejidadServicio= complejidad;
	 estadoServicio=estado;//activo / inactivo
}




}