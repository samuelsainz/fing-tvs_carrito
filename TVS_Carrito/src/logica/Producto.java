package logica;



public class Producto {

	private double precio;
	private String nombre;
	
	public Producto(double precio, String nombre) {
		super();
		this.precio = precio;
		this.nombre = nombre;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return (arg0 instanceof Producto &&
				((Producto)arg0).getNombre().equals(this.getNombre()));
	}
}
