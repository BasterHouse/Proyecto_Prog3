package Clases;

public class Cancion extends Multimedia{
	protected Genero genero;

	public Cancion(String nombre, String artista, int duracion, int reproducciones, int megusta, Genero genero) {
		super(nombre, artista, duracion, reproducciones, megusta);
		this.genero = genero;
	}

	public Cancion() {
		super();
		this.genero = Genero.POP;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Cancion [genero=" + genero + ", nombre=" + nombre + ", artista=" + artista + ", duracion=" + duracion
				+ ", reproducciones=" + reproducciones + ", megusta=" + megusta + "]" + "id" + this.getId();
	}


	
	

}
