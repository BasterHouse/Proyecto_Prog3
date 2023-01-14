package clases;

public class Favorito {
	protected int idUsuario;
	protected String nombreCancion;
	protected String nombrePodcast;
	
	public Favorito(int idUsuario, String nombreCancion, String nombrePodcast) {
		super();
		this.idUsuario = idUsuario;
		this.nombreCancion = nombreCancion;
		this.nombrePodcast = nombrePodcast;
	}
	
	public Favorito() {
		super();
		this.idUsuario = 0;
		this.nombreCancion = "";
		this.nombrePodcast = "";
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreCancion() {
		return nombreCancion;
	}

	public void setNombreCancion(String nombreCancion) {
		this.nombreCancion = nombreCancion;
	}

	public String getNombrePodcast() {
		return nombrePodcast;
	}

	public void setNombrePodcast(String nombrePodcast) {
		this.nombrePodcast = nombrePodcast;
	}

	@Override
	public String toString() {
		return "Favorito [idUsuario=" + idUsuario + ", nombreCancion=" + nombreCancion + ", nombrePodcast="
				+ nombrePodcast + "]";
	}

}
