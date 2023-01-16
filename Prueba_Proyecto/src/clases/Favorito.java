package clases;


public class Favorito  {
	protected Usuario usuario;
	protected String nombreCancion;
	protected String nombrePodcast;
	

	
	
	public Favorito(Usuario usuario, String nombreCancion, String nombrePodcast) {
		super();
		this.usuario = usuario;
		this.nombreCancion = nombreCancion;
		this.nombrePodcast = nombrePodcast;
	}
	public Favorito() {
		super();
		this.usuario = usuario;
		this.nombreCancion = nombreCancion;
		this.nombrePodcast = nombrePodcast;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		return "Favorito [usuario=" + usuario + ", nombreCancion=" + nombreCancion + ", nombrePodcast=" + nombrePodcast
				+ "]";
	}
//	@Override
//	public int compareTo(Favorito o) {
//		// TODO Auto-generated method stub
//		if(this.getUsuario().getId()== o.getUsuario().getId()){
//			return 0;
//		}else {
//		return 1;
//		}
//	}
}
