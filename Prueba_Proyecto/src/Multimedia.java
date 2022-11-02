
public class Multimedia {
	protected String nombre;
	protected String artista;
	protected int duracion;
	protected int reproducciones;
	protected int megusta;
	private int id = -1;
	public Multimedia(String nombre, String artista, int duracion, int reproducciones, int megusta) {
		super();
		this.nombre = nombre;
		this.artista = artista;
		this.duracion = duracion;
		this.reproducciones = reproducciones;
		this.megusta = megusta;
	}
	public Multimedia() {
		super();
		this.nombre = "";
		this.artista = "";
		this.duracion = 0;
		this.reproducciones = 0;
		this.megusta = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getReproducciones() {
		return reproducciones;
	}
	public void setReproducciones(int reproducciones) {
		this.reproducciones = reproducciones;
	}
	public int getMegusta() {
		return megusta;
	}
	public void setMegusta(int megusta) {
		this.megusta = megusta;
	}
	@Override
	public String toString() {
		return "Multimedia [nombre=" + nombre + ", artista=" + artista + ", duracion=" + duracion + ", reproducciones="
				+ reproducciones + ", megusta=" + megusta + ", id=" + id + "]";
	}
	
	

	
	
	
	
	
	
}
