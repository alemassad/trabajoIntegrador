package PrincipalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


import javax.swing.JOptionPane;

import clases.Cliente;
import clases.Empleado;
import clases.Servicio;
import clases.Incidente;


public class Administracion {
	private static final String INCIDENTE = "incidente";
	private static final String EMPLEADO = "empleado";
	private static final String SERVICIO = "servicio";
	private static final String CLIENTES = "clientes";
	private static final int SALIR = 0;
	private static final int GESTION_CLIENTES = 1;
	private static final int GESTION_SERVICIOS = 2;
	private static final int GESTION_EMPLEADOS = 3;
	private static final int GESTION_INCIDENTES = 4;

	private static final int CREAR = 1;
	private static final int BUSCAR = 2;
	private static final int ACTUALIZAR = 3;
	private static final int ELIMINAR = 4;

	private static final int SI = 1;
	private static final int NO = 2;
	
	public static Scanner teclado;

	public static void main(String[] args) {
		
		
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Servicio> servicios = new ArrayList<Servicio>();
		List<Empleado> empleados = new ArrayList<Empleado>();
		List<Incidente> incidentes = new ArrayList<Incidente>();
		Connection conexion = null;
		Statement consulta = null;
		int opcion;
		int opcionSubmenu;
		teclado = new Scanner(System.in);
		
		Cliente cliente;
		Empleado empleado;
		Incidente incidente;
		Servicio servicio;
		
		
		do {
			do {
				mostrarMenuPrincipal();
				opcion = capturaNumeroEntero("Digite la operacion a realizar");
				if (opcion < SALIR || opcion > GESTION_INCIDENTES) {
					mostrarMensaje("Mensaje: ponga un valor entre el 0 y 4 ");
				}
			} while (opcion < SALIR || opcion > GESTION_INCIDENTES);

			if (opcion == SALIR) {
				break;
			}
			System.out.println();

			switch (opcion) {
			case GESTION_CLIENTES:
				do {

					do {
						mostrarSubmenu("Clientes");
						opcionSubmenu = capturaNumeroEntero("Digite la operacion a realizar");
						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("Ingresar un valor entre el 0 y 4 ");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);
					if (opcionSubmenu == SALIR) {
						break;
					}
					switch (opcionSubmenu) {
					case CREAR:
						cliente = crearCliente(clientes);
						clientes.add(cliente);
						ConectorSQL conector = new ConectorSQL(); 
						conector.crearCliente(cliente);						
						continuar();						
						
						break;
						
					case BUSCAR:
						
						if (!clientes.isEmpty()) {
							cliente = buscarCliente(clientes);
							if (cliente != null) {
								mostrarDatosCliente(cliente);
							} else {
								mostrarMensaje("No hay cliente con el numero especificado ");
							}
						} else {
							mostrarMensaje("No hay clientes para mostrar");
						}
						
						break;
					case ACTUALIZAR:
						
						if (!clientes.isEmpty()) {
							cliente = buscarCliente(clientes);
							if (cliente != null) {
								actualizarCliente(cliente);
								mostrarDatosCliente(cliente);
							} else {
								mostrarMensaje("No hay cliente con el número especificado ");
							}
						} else {
							mostrarMensaje("No hay cliente para poder actualizar ");
						}
						
						break;
					case ELIMINAR:
						
						if (!clientes.isEmpty()) {
							eliminarCliente(clientes, incidentes);

						} else {
							mostrarMensaje("No hay cliente para poder actualizar ");
						}
						

						break;
					}
					continuar();
				} while (opcionSubmenu != SALIR);

				break;
			case GESTION_SERVICIOS:
				do {
					do {

						mostrarSubmenu("Servicios");
						opcionSubmenu = capturaNumeroEntero("Digite la operacion a realizar");
						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("Ingresar un valor entre el 0 y 4 ");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);
					if (opcionSubmenu == SALIR) {
						break;
					}

					switch (opcionSubmenu) {

					case CREAR:
						
						servicio = crearServicio(servicios);
						servicios.add(servicio);
						
						break;
					case BUSCAR:
						
						if (!servicios.isEmpty()) {
							servicio = buscarServicio(servicios);
							if (servicio != null) {
								mostrarDatosServicio(servicio);
							} else {
								mostrarMensaje("No se encontró el servicio ");
							}
						} else {
							mostrarMensaje("No se existe servicio, no se puede buscar ");
						}
						
						break;
					case ACTUALIZAR:
						
						
						if (!servicios.isEmpty()) {
							servicio = buscarServicio(servicios);
							if (servicio != null) {
								actualizarServicio(servicio);
								mostrarDatosServicio(servicio);
							} else {
								mostrarMensaje("No exixte servicio con ese ID ");
							}
						} else {
							mostrarMensaje("No se existe servicio, no se puede actualizar ");
						}
						
						break;

					case ELIMINAR:
						
						if (!servicios.isEmpty()) {
							eliminarServicio(servicios, incidentes);
						} else {
							mostrarMensaje("No se existe servicio, no se puede eliminar");
						}
						
						break;
					}
					continuar();
				} while (opcionSubmenu != SALIR);

				break;

			case GESTION_EMPLEADOS:
				do {
					do {
						mostrarSubmenu("Productos");
						opcionSubmenu = capturaNumeroEntero("Digite la operacion a realizar");
						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("Ingresar un valor entre el 0 y 4 ");
							continuar();
						}
					} while (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR);
					if (opcionSubmenu == SALIR) {
						break;
					}

					switch (opcionSubmenu) {
					case CREAR:
						
						if (!empleados.isEmpty()) {
							empleado = crearEmpleado(empleados, incidentes);
							empleados.add(empleado);
							mostrarMensaje("empleado agregado");
						} else {
							mostrarMensaje("crear empleado, para reportar incidente");
						}
						

						break;
					case BUSCAR:
						
						if (!empleados.isEmpty()) {
							empleado = buscarEmpleado(empleados);
							if (empleado != null) {
								mostrarDatosEmpleado(empleado);
							} else {
								mostrarMensaje("No se encontr� el empleado ");
							}
						} else {
							mostrarMensaje("No se existe empleado, no se puede buscar");
						}
						

						break;

					case ACTUALIZAR:
						
						if (!empleados.isEmpty()) {
							empleado = buscarEmpleado(empleados);
							if (empleado != null) {
								actualizarEmpleado(empleado, incidentes);
								mostrarDatosEmpleado(empleado);
							} else {
								mostrarMensaje("No exixte empleado con esa ID ");
							}
						} else {
							mostrarMensaje("No se existe empleado, no se puede actualizar");
						}
						
						break;

					case ELIMINAR:
						
						if (!empleados.isEmpty()) {
							eliminarEmpleado(empleados, incidentes);
						} else {
							mostrarMensaje("No se existe producto, no se puede eliminar");
						}
						

						break;
					}
					continuar();
				} while (opcionSubmenu != SALIR);

				break;
			case GESTION_INCIDENTES:
				do {
					do {
						// mostrarSubmenuFacturacion();
						opcionSubmenu = capturaNumeroEntero("Digite la operacion a realizar");
						if (opcionSubmenu < SALIR || opcionSubmenu > ELIMINAR) {
							mostrarMensaje("Ingrese un valor entre el 0 y 2 ");
							continuar();
						}

					} while (opcionSubmenu < SALIR || opcionSubmenu > BUSCAR);

					if (opcionSubmenu == SALIR) {
						break;
					}
					switch (opcionSubmenu) {

					case CREAR:
						/*
						if (!clientes.isEmpty()) {
							if (!productos.isEmpty()) {
								factura = crearFactura(clientes, productos, facturas);
								facturas.add(factura);
								mostrarDatosFactura(factura, clientes, productos);
							} else {
								mostrarMensaje("No se puede crear factura sin productos");

							}
						} else {
							mostrarMensaje("No se puede crear factura sin clientes");
						}
						*/
						break;

					case BUSCAR:
						/*
						if (!facturas.isEmpty()) {

							factura = buscarFactura(facturas);
							if (factura != null) {
								mostrarDatosFactura(factura, clientes, productos);
							} else {
								mostrarMensaje("No se encontró la factura con la Id ingresada ");
							}
						} else {
							mostrarMensaje("No se han creado facturas");
						}
						*/

						break;
					}
					continuar();
				} while (opcionSubmenu != SALIR);
				break;
			}
			
			continuar();
		} while (opcion != SALIR);
		System.out.println();
		mostrarMensaje("Fin del programa");
		continuar();
		/*
		if (!clientes.isEmpty()) {
			do {
				System.out.println("Guardar datos");
				System.out.println("1 -- Si ");
				System.out.println("2 -- No");
				opcion = capturaNumeroEntero("Quiere guardar los Datos?");
				if (opcion == SI || opcion == NO) {
					break;
				} else {
					mostrarMensaje("Elija ona opcion de guardar dato 1 o 2.");
					continuar();
				}
			} while (true);

			if (opcion == SI) {

				guardarDatosInventario(clientes, proveedores, productos, facturas);
				System.out.println();
				System.out.println("Datos guardados");
				System.out.println();

			}
		}
		*/
		
	
	
	
}
		
		private static void eliminarServicio(List<Servicio> servicios, List<Incidente> incidente2) {
			int id;
			Servicio servicio;
			do {
				id = capturaNumeroEntero("Ponga el numero ID del Servicio");
				if (id <= 0) {
					mostrarMensaje("Ponga un numero valido ");
					continuar();
					continue;
				}
			} while (id <= 0);

			servicio = buscarServicioPorId(servicios, id);
			if (servicio != null) {
				List<Incidente> incidenteServicio = buscarServicioPorIdIncidente(incidente2, id);

				if (incidenteServicio.isEmpty()) {
					servicios.remove(servicio);
					mostrarMensaje(String.format(" Servicio Eliminado %d.\n", id));
				} else {
					mostrarMensaje("No se puede eliminar el Servicio. Tiene incidentes asociados");
					continuar();
				}
			} else {
				mostrarMensaje("No existe Servicio con esa Id ");
				continuar();
			}
		
	}

	

		static void mostrarMensaje(String mensaje) {
			System.out.println();
			System.out.printf(mensaje);
			System.out.println();

		}
		public static void mostrarMenuPrincipal() {
			System.out.println("==== MENU PRINCIPAL ====");
			System.out.println("1- Gestion Clientes");
			System.out.println("2- Gestion Proveedores");
			System.out.println("3- Gestion Productos");
			System.out.println("4- Gestion Facturacion");
			System.out.println("0- Salir");

		}

		public static void mostrarSubmenu(String tipoMenu) {
			System.out.printf("*** MENU Gestion %s***\n", tipoMenu);
			System.out.println("1- Crear");
			System.out.println("2- Buscar");
			System.out.println("3- Actualizar");
			System.out.println("4- Eliminar");
			System.out.println("0- Salir");
		}
		public static String capturarCadenaCaracteres(String mensaje) {
			String resultado;
			while (true) {
				System.out.printf("%s: ", mensaje);
				resultado = teclado.nextLine();
				if (!resultado.isEmpty()) {
					return resultado;
				}
				System.out.println("\nHas escrito una cadena vacia. Ponga un valor correcto");
				continuar();
			}
		}
		
		public static int capturaNumeroEntero(String mensaje) {

			while (true) {
				try {
					System.out.printf("%s: ", mensaje);
					return Integer.parseInt(teclado.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("\nIngrese un valor que corresponda con un numero entero");

				}
				continuar();
			}

		}
		
		public static double capturaNumeroReal(String mensaje) {

			while (true) {
				try {
					System.out.printf("%s: ", mensaje);
					return Double.parseDouble(teclado.nextLine());
				} catch (NumberFormatException e) {
					System.out.println("Ingrese un valor que corresponda con un numero REAL");

				}
				continuar();
			}

		}

		static boolean correoElectronicoValido(String correo) {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]$";
			return correo.matches(regex);
		}

		static void continuar() {
			System.out.println();
			System.out.print("Enter para continuar");
			teclado.nextLine();
			System.out.println();

		}

		private static Cliente crearCliente(List<Cliente> clientes) {
			System.out.println();
			System.out.println("--- 1. Crear Cliente ---");
			int cuit;
			String dni = "";
			Cliente cliente;
			int telefono;
			int idServi;
			do {
				cuit = capturaNumeroEntero("Poner el DNI del cliente ");
				if (cuit <= 0) {
					mostrarMensaje("El DNI debe ser un numero valido");
					cuit = 0;
					continuar();
					continue;
				}
				dni = String.valueOf(cuit);
				cliente = buscarClientePorCuit(clientes, cuit);
				if (cliente != null) {
					mostrarMensaje(String.format("Ya existe otro Cliente con cuit: %s.\n", cuit));

					cuit = 0;
				}

			} while (cuit <= 0);

			String nombre = capturarCadenaCaracteres("Ingrese los nombres del nuevo cliente: ");
			String apellido = capturarCadenaCaracteres("Ingrese los apellidos del nuevo cliente: ");
			String direccion = capturarCadenaCaracteres("Ingrese la direccion: ");
			do {
				telefono = capturaNumeroEntero("Ingrese numero telefono del nuevo cliente: ");

				if (telefono <= 0) {
					mostrarMensaje("El telefono tiene que ser un numero valido ");
					continuar();
				}

			} while (telefono <= 0);
			
			
			do {
				idServi = capturaNumeroEntero("Ingrese numero de servicio del nuevo cliente: ");

				if (idServi <= 0) {
					mostrarMensaje("El codigo del servicio tiene que ser un numero valido ");
					continuar();
				}

			} while (idServi <= 0);

			return new Cliente(cuit, nombre, apellido, direccion, telefono,  idServi);
		}
		private static Cliente buscarCliente(List<Cliente> clientes) {
			System.out.println();
			System.out.println("--- 2. Buscar Cliente ---");
			int numeroDni;
			int cuit;

			do {
				numeroDni = capturaNumeroEntero("Poner el DNI del cliente ");
				if (numeroDni <= 0) {
					mostrarMensaje("El DNI debe ser un numero valido");
					continuar();
					numeroDni = 0;
					continue;
				}

			} while (numeroDni <= 0);
			cuit = numeroDni;

			return buscarClientePorCuit(clientes, cuit);
		}
		private static Cliente buscarClientePorCuit(List<Cliente> clientes, int cuit) {
			for (Cliente cliente : clientes) {
				if (cliente.getCuitCliente()== cuit) {
					return cliente;
				}
			}
			return null;
		}
		private static void actualizarCliente(Cliente cliente) {
			System.out.println("--- 3. Actualizar Cliente ---");

			String nombres = capturarCadenaCaracteres("Actualice los nombres del cliente: ");
			String apellidos = capturarCadenaCaracteres("Actualice los apellidos del cliente: ");
			int telefono;
			do {
				telefono = capturaNumeroEntero("Actualizar el telefono del cliente: ");

				if (telefono <= 0) {
					mostrarMensaje("El telefono tiene que ser un numero valido ");
					continuar();
				}

			} while (telefono <= 0);

			String direccion = capturarCadenaCaracteres("Actualizar la direccion: ");
			String correo;
			while (true) {
				correo = capturarCadenaCaracteres("Actualizar el Email del cliente: ");
				if (!correoElectronicoValido(correo)) {
					mostrarMensaje("Ingreso incorrecto del valor Email");
					continuar();
					
				}
				break;
			}
			cliente.setNombreCliente(nombres);
			cliente.setApellidoCliente(apellidos);
			cliente.setTelefonoCliente(telefono);
			cliente.setDireccionCliente(direccion);
		
		}

		private static void mostrarDatosCliente(Cliente cliente) {
			System.out.println("Datos del cliente");
			System.out.println("Cuit: " + cliente.getCuitCliente());
			System.out.println("Nombre: " + cliente.getNombreCliente());
			System.out.println("Apellido: " + cliente.getApellidoCliente());
			System.out.println("Telefono: " + cliente.getTelefonoCliente());
			System.out.println("Direccion: " + cliente.getDireccionCliente());
			
		}
		private static Incidente buscarIncidentePorCuit(List<Incidente> incidentes, int cuit) {
			for (Incidente incidente : incidentes) {
				if (incidente.getIdCliente()==cuit) {
					return incidente;
				}
			}
			return null;
		}
		private static void eliminarCliente(List<Cliente> clientes, List<Incidente> incidentes) {
			int numerocuit;
			int cedula;

			do {
				numerocuit = capturaNumeroEntero("Poner el Cuit del cliente ");
				if (numerocuit <= 0) {
					mostrarMensaje("El Cuit debe ser un numero valido");
					continuar();
					numerocuit = 0;
					continue;
				}

			} while (numerocuit <= 0);
			cedula = numerocuit;

			Cliente cliente = buscarClientePorCuit(clientes, cedula);
			if (cliente != null) {
				Incidente incidente = buscarIncidentePorCuit(incidentes, cedula);

				if (incidente == null) {
					clientes.remove(cliente);
					mostrarMensaje(String.format("Se elimin� el Cliente: %s\n", cedula));

				} else {
					mostrarMensaje("No se puede eliminar el cliente, por que tiene Incidente en Proceso indexados");
					continuar();
				}
			} else {
				mostrarMensaje("Cliente con Cuit no encontrado ");
				continuar();
			}

		}
		private static Servicio crearServicio(List<Servicio> servicios) {
			System.out.println();
			System.out.println("--- 1. Crear Servicio ---");
			int id;
			String servicio=null;
			String descripcion;			
			String complejidad;
			int horasResulucion;
			String estado;
			
			do {
				id = capturaNumeroEntero("Ponga el numero ID del nuevo Servicio");
				if (id <= 0) {
					mostrarMensaje("Ponga un numero id valido ");
					continuar();
					continue;
				}
				servicio =capturarCadenaCaracteres("Ponga el tipo de servicio para el nuevo servicio: ");
				if (servicio != null) {
					mostrarMensaje("Servicio con esa id ya existente ");
					continuar();
					id = 0;
				}
			} while (id <= 0);
			descripcion = capturarCadenaCaracteres("Ponga el descripcion para el nuevo servicio: ");
			complejidad = capturarCadenaCaracteres("Ingrese la direccion del servicio: ");
			do {
				horasResulucion = capturaNumeroEntero("Ponga el numero de hora para el servicio: ");

				if (horasResulucion <= 0) {
					mostrarMensaje("El horario tiene que ser un numero valido ");
					continuar();
				}

			} while (horasResulucion <= 0);
			estado = capturarCadenaCaracteres("Ingrese la direccion del servicio: ");

			return new Servicio(id, servicio, descripcion, complejidad, horasResulucion, estado);
		}

		private static Servicio buscarServicioPorId(List<Servicio> servicios, int id) {

			return servicios.stream().filter(p -> p.getIdServicio() == id).findFirst().orElse(null);
		}
		private static void mostrarDatosServicio(Servicio servicio) {
			System.out.println("--- Datos del Servicio ---");
			System.out.println("ID: " + servicio.getIdServicio());
			System.out.println("Nombre: " + servicio.getDescripcion());
			System.out.println("Telefono: " + servicio.getComplejidadServicio());
			System.out.println("Horas para terminar: " + servicio.getHorasResolucion());
			System.out.println("Estado del servicio: " + servicio.getEstadoServicio());
		}

		private static Servicio buscarServicio(List<Servicio> servicios) {
			System.out.println("--- 2. Buscar Servicio ---");
			int id;

			do {
				id = capturaNumeroEntero("Ponga el numero ID del Servicio");
				if (id <= 0) {
					mostrarMensaje("Ponga un n�mero valido ");
					continuar();
					continue;
				}

			} while (id <= 0);

			return buscarServicioPorId(servicios, id);
		}
		

		

		private static List<Incidente> buscarServicioPorIdIncidente(List<Incidente> incidentes, int idServicio) {
			return incidentes.stream().filter(p -> p.getIdServicio() == idServicio).collect(Collectors.toList());
		}

		private static void actualizarServicio(Servicio servicio) {
			System.out.println("----3. Actualizar Servicio ----");
			int idServicio;
			String tipo;
			String descripcion;
			String complejidad;
			int horasResolucion;
			String estado;
			do {
				idServicio = capturaNumeroEntero("Actualizar el número de idServicio para el Servicio: ");

				if (idServicio <= 0) {
					mostrarMensaje("El idServicio tiene que ser un numero valido ");
					continuar();
				}

			} while (idServicio <= 0);
			tipo = capturarCadenaCaracteres("Actualizar el tipo del Servicio: ");
			
			descripcion = capturarCadenaCaracteres("Actualizar la descripcion del Servicio: ");
			complejidad = capturarCadenaCaracteres("complejidad la descripcion del Servicio: ");
			do {
				horasResolucion = capturaNumeroEntero("Actualizar las horas Resolucion para el Servicio: ");

				if (horasResolucion <= 0) {
					mostrarMensaje("El horasResolucion tiene que ser un numero valido ");
					continuar();
				}

			} while (horasResolucion <= 0);
			estado = capturarCadenaCaracteres("Actualizar el estado del Servicio: ");
			
			servicio.setIdServicio(idServicio);
			servicio.setTipoServicio(tipo);
			servicio.setDescripcion(descripcion);
			servicio.setComplejidadServicio(complejidad);
			servicio.setHorasResolucion(horasResolucion);
			servicio.setEstadoServicio(estado);

		}
	
		private static void eliminarEmpleado(List<Empleado> empleados, List<Incidente> incidentes) {
			System.out.println("---4. Eliminar empleado --- ");
			int id;
			Empleado empleado;
			do {

				id = capturaNumeroEntero("Ponga el numero ID del empleado");
				if (id <= 0) {
					mostrarMensaje("Ponga un número válido ");
					continuar();
					continue;
				}
			} while (id <= 0);

			empleado = buscarEmpleadoPorId(empleados, id);

			if (empleado != null) {
				if (!empleadoExisteIncidente(id, incidentes)) {
					empleados.remove(empleado);
					mostrarMensaje(String.format("empleado eliminado con la id-%d.\n", id));

				} else {
					mostrarMensaje("No se puede eliminar el empleado indexada a Incidente");
					continuar();
				}
			} else {
				mostrarMensaje("No existe empleado con esa Id");
				continuar();
			}

		}

		private static boolean empleadoExisteIncidente(int idEmpleado, List<Incidente> incidentes) {

			Integer[] idsEmpleados;
			for (Incidente incidente : incidentes) {
				idsEmpleados = incidente.getIdEmpleado();
				Arrays.sort(idsEmpleados);

				if (Arrays.binarySearch(idsEmpleados, idEmpleado) != -1) {
					return true;
				}

			}
			return false;
		}

		private static void actualizarEmpleado(Empleado empleado, List<Incidente> incidentes) {
			System.out.println("---3. Actualizar empleado --- ");
			int id;
			
			String cuitEmpleado;
			String nombreEmpleado;
			String apellidoEmpleado;
			String direccionEmpleado;
			int telefono;
			LocalDate altaEmpleado = LocalDate.now();
			String areaEmpleado;
			
			do {
				id = capturaNumeroEntero("Ingrese el nuevo ID del empleado");
				if (id <= 0) {
					mostrarMensaje("El ID mayor o igual a 0 ");
					continuar();
				}
			} while (id <= 0);
			cuitEmpleado = capturarCadenaCaracteres("Ponga el nuevo cuitEmpleado para el empleado: ");
			nombreEmpleado = capturarCadenaCaracteres("Ponga el nuevo nombre para el empleado: ");
			apellidoEmpleado = capturarCadenaCaracteres("Ingrese la nuevo apellidoEmpleado del empleado: ");
			direccionEmpleado = capturarCadenaCaracteres("Ingrese la nueva direccionEmpleado del empleado: ");
			telefono = capturaNumeroEntero("Ponga el numero telefono del nuevo empleado");
			if (telefono <= 0) {
				mostrarMensaje("Ponga un numero telefono valido ");
				continuar();
			}
			areaEmpleado = capturarCadenaCaracteres("Ingrese la nueva Area de trabajo del empleado: ");
			

			
			empleado.setIdEmpleado(id);
			empleado.setCuitEmpleado(cuitEmpleado);
			empleado.setNombreEmpleado(nombreEmpleado);
			empleado.setApellidoEmpleado(apellidoEmpleado);
			empleado.setDireccionEmpleado(direccionEmpleado);
			empleado.setTelefonoEmpleado(telefono);
			empleado.setAreaEmpleado(areaEmpleado);
		}

		private static void mostrarDatosEmpleado(Empleado empleado) {
			System.out.println();
			System.out.println("Datos del Empleado");
			System.out.println("Id: " + empleado.getIdEmpleado());
			System.out.println("Cuit: " + empleado.getCuitEmpleado());
			System.out.println("Nombre: " + empleado.getNombreEmpleado());
			System.out.println("Apellido: " + empleado.getApellidoEmpleado());
			System.out.println("Direccion: " + empleado.getDireccionEmpleado());
			System.out.println("Telefono: " + empleado.getTelefonoEmpleado());
			System.out.println("Alta del empleado: " + empleado.getAltaEmpleado());
			System.out.println("Area de trabajo: " + empleado.getAreaEmpleado());
						

		}

		private static Empleado buscarEmpleado(List<Empleado> empleados) {
			System.out.println("---2. Buscar Empleado --- ");
			int id;
			do {

				id = capturaNumeroEntero("Ponga el numero ID del nuevo Empleado");
				if (id <= 0) {
					mostrarMensaje("Ponga un numero id valido ");
					continuar();
					continue;
				}
			} while (id <= 0);

			return buscarEmpleadoPorId(empleados, id);
		}

		private static Empleado crearEmpleado(List<Empleado> empleados, List<Incidente> incidentes) {
			System.out.println("---1. Crear Empleado --- ");
			int id;
			Empleado empleado;
			String cuit = null;
			String nombre;
			String apellido;
			String direccion;
			int telefono;
			LocalDate altaEmpleado;

			do {

				id = capturaNumeroEntero("Ponga el numero ID del nuevo empleado");
				
				if (id <= 0) {
					mostrarMensaje("Ponga un numero id valido ");
					continuar();
					continue;
				}
				cuit = capturarCadenaCaracteres("Ponga el Cuit para el nuevo Empleado: ");
				empleado = buscarEmpleadoPorId(empleados, id);

				if (empleado != null) {
					mostrarMensaje("empleado con id ya existente ");
					continuar();
					id = 0;
				}
			} while (id <= 0);

			nombre = capturarCadenaCaracteres("Ponga el nombre para el nuevo Empleado: ");
			apellido = capturarCadenaCaracteres("Ingrese la apellido del nuevo Empleado: ");
			direccion = capturarCadenaCaracteres("Ingrese la direccion del nuevo Empleado: ");
			telefono = capturaNumeroEntero("Ponga el numero telefono del nuevo empleado");
			if (telefono <= 0) {
				mostrarMensaje("Ponga un numero telefono valido ");
				continuar();
				
			}
			altaEmpleado = LocalDate.now();					

			
			return new Empleado(id,cuit, nombre, apellido, direccion, telefono, altaEmpleado);

		}

		private static Empleado buscarEmpleadoPorId(List<Empleado> empleados, int id) {
			// return productos.stream().filter(p -> p.getId() == id).findFirst().get();
			return empleados.stream().filter(p -> p.getIdEmpleado() == id).findFirst().orElse(null);

		}
}

