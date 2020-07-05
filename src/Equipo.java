
public class Equipo {
	
	String subestacion,ubicacion,marca,modelo,serie,equipo,descripcion,orden;

	public Equipo(String subestacion, String ubicacion, String marca, String modelo, String serie, String equipo,
			String descripcion, String orden) {
		super();
		this.subestacion = subestacion;
		this.ubicacion = ubicacion;
		this.marca = marca;
		this.modelo = modelo;
		this.serie = serie;
		this.equipo = equipo;
		this.descripcion = descripcion;
		this.orden = orden;
	}

	public String getSubestacion() {
		return subestacion;
	}

	public void setSubestacion(String subestacion) {
		this.subestacion = subestacion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}
	
	
}
