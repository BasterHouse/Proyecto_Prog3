package clases;

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
		return nombre + " (" + artista + ")"; 
	}


	
	

}
