package PrincipalDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import clases.Cliente;
import clases.Incidente;


	public class ConectorSQL {

	    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	       

	   
		private Connection conectar() {	
			
			
			Connection conexion = null;	

			try {
				
				conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/reporte","root","root");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return conexion;
		}

	/*
	 * Bauscar un cliente por su DNI
	 */
	public Cliente buscarClientePorCuit(String cuit) {
		final String SQL = "SELECT * FROM cliente WHERE cuit = ?";
		Connection conexion = conectar();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setString(1, cuit);
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				Cliente cliente = new Cliente(0, SQL, SQL, SQL, 0, 0);
				cliente.setCuitCliente(rst.getInt("cuitCliente"));
				cliente.setNombreCliente(rst.getString("nombreCliente"));
				cliente.setApellidoCliente(rst.getString("apellidoCliente"));
				cliente.setDireccionCliente(rst.getString("direccionCliente"));
				cliente.setTelefonoCliente(rst.getInt("telefonoCliente"));
				

				conexion.close();
				return cliente;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conexion.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Crear Cliente en la DB
	 */
	public void crearCliente(Cliente cliente) {
		final String SQL = "INSERT INTO cliente VALUES(?, ?, ?, ?, ?, ?)";

		Connection conexion = conectar();

		try {

			PreparedStatement pstmt = conexion.prepareStatement(SQL);

			pstmt.setInt(1, cliente.getCuitCliente());
			pstmt.setString(2, cliente.getNombreCliente());
			pstmt.setString(3, cliente.getApellidoCliente());			
			pstmt.setInt(6, cliente.getTelefonoCliente());
			pstmt.setString(4, cliente.getDireccionCliente());
			pstmt.setInt(5, cliente.getIdServicio());

			int i = pstmt.executeUpdate();
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "Alta correcta", "Atencion", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error", "Error: Hubo un error al hacer el pedido.", i);
			}
			
			conexion.close();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/*
	 * Actualiza un Cliente
	 */
	public boolean actualizarCliente(Cliente cliente) {

		final String SQL = "UPDATE cliente SET nombres = ?, apellidos = ?, telefono = ?, direccion = ?,  WHERE dni = ?";

		Connection conexion = conectar();

		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);

			pstmt.setString(2, cliente.getNombreCliente());
			pstmt.setString(3, cliente.getApellidoCliente());
			pstmt.setInt(5, cliente.getTelefonoCliente());
			pstmt.setString(4, cliente.getDireccionCliente());
			
			pstmt.setInt(1, cliente.getCuitCliente());
			int datosActuales = pstmt.executeUpdate();

			conexion.close();
			return datosActuales > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Buscar facturas a partir de un DNI
	 */
	public List<Incidente> buscarIncidentesCliente(int cuit) {

		List<Incidente> incidentes = new ArrayList<>();
		final String SQL = "SELECT * FROM incidente WHERE cuitCliente = ?";
		Connection conexion = conectar();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setInt(1, cuit);
			ResultSet rst = pstmt.executeQuery();
			Incidente incidente;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			while (rst.next()) {
				incidente = new Incidente();
				incidente.setIdIncidente(rst.getInt("idIncidente"));
				incidente.setIdEmpleado(rst.getInt("idEmpleado"));
				
				incidente.setIdCliente(rst.getInt("idCliente"));
				incidente.setIdServicio(rst.getInt("idServicio"));
				incidente.setHorasHasta(rst.getString("horasHasta"));
				incidente.setEstado(rst.getString("estado"));
				incidentes.add(incidente);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return incidentes;
	}

	/*
	 * Elimar cliente por el DNI
	 */
	public void eliminarClientePorCuit(int cuit) {
		final String SQL = "DELETE FROM cliente WHERE cuitCliente = ?";
		Connection conexion = conectar();
		try {
			PreparedStatement pstmt = conexion.prepareStatement(SQL);
			pstmt.setInt(1, cuit);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conexion.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * Buscar un proveedor por la Id
	 * 
	 * public Proveedor buscarProveedorPorId(Long id) { final String SQL =
	 * "SELECT * FROM proveedor WHERE id = ?"; Connection conexion = conectar(); try
	 * { PreparedStatement pstmt = conexion.prepareStatement(SQL); pstmt.setLong(1,
	 * id); ResultSet rst = pstmt.executeQuery(); if (rst.next()) { Proveedor
	 * proveedor = new Proveedor(); proveedor.setId(id);
	 * proveedor.setNombre(rst.getString("nombre"));
	 * proveedor.setDireccion(rst.getString("direccion"));
	 * proveedor.setTelefono(rst.getString("telefono")); conexion.close(); return
	 * proveedor; }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * null; }
	 * 
	 * 
	 * Crear un proveedor en al DB
	 * 
	 * public void crearProveedor(Proveedor proveedor) { final String SQL =
	 * "INSERT INTO proveedor (id, nombre, direccion, telefono) VALUES(?, ?, ?, ?)";
	 * Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(SQL); pstmt.setLong(1, proveedor.getId());
	 * pstmt.setString(2, proveedor.getNombre()); pstmt.setString(3,
	 * proveedor.getDireccion()); pstmt.setString(4, proveedor.getTelefono());
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 * Actualizar un proveedor en la DB
	 * 
	 * public void actualizarProveedor(Proveedor proveedor) { final String SQL =
	 * "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ? WHERE id = ?";
	 * Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(SQL); pstmt.setString(1, proveedor.getNombre());
	 * pstmt.setString(2, proveedor.getDireccion()); pstmt.setString(3,
	 * proveedor.getTelefono()); pstmt.setLong(4, proveedor.getId());
	 * 
	 * pstmt.executeUpdate(); } catch (Exception e) { e.printStackTrace(); } finally
	 * { try { conexion.close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 * Buscar productos por una Identificación del proveedor
	 * 
	 * public List<Producto> buscarProductosPorIdProveedor(Long id) { final String
	 * SQL = "SELECT * FROM producto WHERE proveedor_id = ?"; List<Producto>
	 * productos = new ArrayList<>(); Connection conexion = conectar(); try {
	 * PreparedStatement pstmt = conexion.prepareStatement(SQL); pstmt.setLong(1,
	 * id);
	 * 
	 * ResultSet rst = pstmt.executeQuery(); Producto producto;
	 * 
	 * while (rst.next()) { producto = new Producto();
	 * producto.setId(rst.getInt("id"));
	 * producto.setNombre(rst.getString("nombre"));
	 * producto.setDescripcion(rst.getString("descripcion"));
	 * producto.setPrecioCompra(rst.getDouble("precio_compra"));
	 * producto.setPrecioVenta(rst.getDouble("precio_venta"));
	 * producto.setCantidad(rst.getInt("cantidad"));
	 * producto.setCantidadMinimoStock(rst.getInt("cantidad_minima_stock"));
	 * producto.setIdProveedor(id); productos.add(producto); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * productos; }
	 * 
	 * 
	 * Borrar un proveedor por su identificación
	 * 
	 * public void borrarProveedorPorId(long id) {
	 * 
	 * final String SQL = "DELETE FROM proveedor WHERE id = ?"; Connection conexion
	 * = conectar(); try { PreparedStatement pstmt = conexion.prepareStatement(SQL);
	 * pstmt.setLong(1, id); pstmt.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * 
	 * Obtener un listado de proveedor
	 * 
	 * public List<Proveedor> obtenerProveedores() { List<Proveedor> proveedores =
	 * new ArrayList<>(); final String SQL = "SELECT * FROM proveedor"; Connection
	 * conexion = conectar();
	 * 
	 * try { PreparedStatement pstmt = conexion.prepareStatement(SQL);
	 * 
	 * ResultSet rst = pstmt.executeQuery();
	 * 
	 * Proveedor proveedor;
	 * 
	 * while (rst.next()) { proveedor = new Proveedor();
	 * proveedor.setId(rst.getLong("id"));
	 * proveedor.setNombre(rst.getString("nombre"));
	 * proveedor.setDireccion(rst.getString("direccion"));
	 * proveedor.setTelefono(rst.getString("telefono"));
	 * 
	 * proveedores.add(proveedor); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * proveedores;
	 * 
	 * }
	 * 
	 * 
	 * Buscar producto por su identificación
	 * 
	 * public Producto buscarProductosPorId(int id) {
	 * 
	 * final String SQL = "SELECT * FROM producto WHERE id = ?"; Connection conexion
	 * = conectar(); try { PreparedStatement pstmt = conexion.prepareStatement(SQL);
	 * pstmt.setInt(1, id);
	 * 
	 * ResultSet rst = pstmt.executeQuery();
	 * 
	 * if (rst.next()) { Producto producto = new Producto(); producto.setId(id);
	 * producto.setNombre(rst.getString("nombre"));
	 * producto.setDescripcion(rst.getString("descripcion"));
	 * producto.setPrecioCompra(rst.getDouble("precio_compra"));
	 * producto.setPrecioVenta(rst.getDouble("precio_venta"));
	 * producto.setCantidad(rst.getInt("cantidad"));
	 * producto.setCantidadMinimoStock(rst.getInt("cantidad_minima_stock"));
	 * producto.setIdProveedor(rst.getLong("proveedor_id"));
	 * 
	 * conexion.close(); return producto; }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * null;
	 * 
	 * }
	 * 
	 * 
	 * Crear un producto
	 * 
	 * public void crearProducto(Producto producto) { final String SQL =
	 * "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; Connection conexion =
	 * conectar(); try { PreparedStatement pstmt = conexion.prepareStatement(SQL);
	 * pstmt.setInt(1, producto.getId()); pstmt.setString(2, producto.getNombre());
	 * pstmt.setString(3, producto.getDescripcion()); pstmt.setDouble(4,
	 * producto.getPrecioCompra()); pstmt.setDouble(5, producto.getPrecioVenta());
	 * pstmt.setInt(6, producto.getCantidad()); pstmt.setInt(7,
	 * producto.getCantidadMinimoStock()); pstmt.setLong(8,
	 * producto.getIdProveedor());
	 * 
	 * pstmt.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * finally { try { conexion.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 * Actualizar un producto
	 * 
	 * public void actualizarProducto(Producto producto) { final String SQL =
	 * "UPDATE producto SET nombre = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, cantidad = ?, cantidad_minima_stock = ?, proveedor_id = ? WHERE id = ?"
	 * ; Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(SQL); pstmt.setString(1, producto.getNombre());
	 * pstmt.setString(2, producto.getDescripcion()); pstmt.setDouble(3,
	 * producto.getPrecioCompra()); pstmt.setDouble(4, producto.getPrecioVenta());
	 * pstmt.setInt(5, producto.getCantidad()); pstmt.setInt(6,
	 * producto.getCantidadMinimoStock()); pstmt.setLong(7,
	 * producto.getIdProveedor()); pstmt.setInt(8, producto.getId());
	 * 
	 * pstmt.executeUpdate(); conexion.close(); } catch (SQLException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * 
	 * Busca un Producto en la Factura
	 * 
	 * public boolean productoEnFactura(int idProducto) {
	 * 
	 * final String SQL = "SELECT * FROM factura_producto WHERE producto_id = ?";
	 * Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(SQL); pstmt.setInt(1, idProducto);
	 * 
	 * boolean resultado = pstmt.executeQuery().next(); conexion.close(); return
	 * resultado;
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); return false; } finally { try
	 * { conexion.close(); } catch (SQLException e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 * Borrar un producto por su identificación
	 * 
	 * public void borrarProductoPorId(int id) { final String SQL =
	 * "DELETE FROM producto WHERE id = ?"; Connection conexion = conectar(); try {
	 * PreparedStatement pstmt = conexion.prepareStatement(SQL); pstmt.setInt(1,
	 * id);
	 * 
	 * pstmt.executeUpdate(); conexion.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } finally { try { conexion.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 * 
	 * }
	 * 
	 * 
	 * Listado de productos
	 * 
	 * public List<Producto> obtenerProductos() { final String SQL =
	 * "SELECT * FROM producto"; List<Producto> productos = new ArrayList<>();
	 * Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(SQL); ResultSet rst = pstmt.executeQuery();
	 * Producto producto; while (rst.next()) { producto = new Producto();
	 * producto.setId(rst.getInt("id"));
	 * producto.setNombre(rst.getString("nombre"));
	 * producto.setDescripcion(rst.getString("descripcion"));
	 * producto.setPrecioCompra(rst.getDouble("precio_compra"));
	 * producto.setPrecioVenta(rst.getDouble("precio_venta"));
	 * producto.setCantidad(rst.getInt("cantidad"));
	 * producto.setCantidadMinimoStock(rst.getInt("cantidad_minima_stock"));
	 * producto.setIdProveedor(rst.getLong("proveedor_id"));
	 * 
	 * productos.add(producto); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * productos; }
	 * 
	 * 
	 * Crear Factura con productos
	 * 
	 * public void crearFactura(Factura nuevaFactura) {
	 * 
	 * String sql =
	 * "INSERT INTO factura (fecha, cliente_dni, impuesto, valor_total) VALUES(?, ?, ?, ?)"
	 * ; Connection conexion = conectar(); try { PreparedStatement pstmt =
	 * conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Date
	 * fechaActual = new Date(); pstmt.setString(1, sdf.format(fechaActual));
	 * pstmt.setString(2, nuevaFactura.getDniCliente()); pstmt.setDouble(3,
	 * nuevaFactura.getImpuesto()); pstmt.setDouble(4, nuevaFactura.getTotal());
	 * pstmt.executeUpdate();
	 * 
	 * ResultSet rst = pstmt.getGeneratedKeys();
	 * 
	 * if (rst.next()) { nuevaFactura.setId(rst.getInt(1));
	 * 
	 * sql = "INSERT INTO factura_producto VALUES (?, ?, ?)"; pstmt =
	 * conexion.prepareStatement(sql);
	 * 
	 * for (int idProducto : nuevaFactura.getIdsProductos()) { pstmt.setInt(1,
	 * nuevaFactura.getId()); pstmt.setInt(2, idProducto); pstmt.setInt(3, 1);
	 * 
	 * pstmt.executeUpdate(); }
	 * 
	 * }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally { try {
	 * conexion.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	 * 
	 * 
	 * Buscar una Factura por una Identificación
	 * 
	 * public Factura buscarFacturaPorId(int id) { String sql =
	 * "SELECT * FROM factura WHERE id = ?"; Connection conexion = conectar(); try {
	 * PreparedStatement pstmt = conexion.prepareStatement(sql); pstmt.setInt(1,
	 * id); ResultSet rst = pstmt.executeQuery();
	 * 
	 * if (rst.next()) { Factura factura = new Factura(); factura.setId(id);
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * factura.setFecha(sdf.parse(rst.getString("fecha")));
	 * factura.setDniCliente(rst.getString("cliente_dni"));
	 * factura.setImpuesto(rst.getDouble("impuesto"));
	 * factura.setTotal(rst.getDouble("valor_total")); sql =
	 * "SELECT * FROM factura_producto WHERE factura_id = ?"; pstmt =
	 * conexion.prepareStatement(sql); pstmt.setInt(1, id); ResultSet resultado =
	 * pstmt.executeQuery(); while (resultado.next()) {
	 * factura.agregarIdProducto(resultado.getInt("producto_id"));
	 * 
	 * }
	 * 
	 * conexion.close(); return factura; }
	 * 
	 * } catch (SQLException e) { e.printStackTrace();
	 * 
	 * } catch (ParseException e) {
	 * 
	 * e.printStackTrace(); } finally { try { conexion.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } } return null; }
	 */
}