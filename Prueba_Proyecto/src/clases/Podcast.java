package clases;

public class Podcast extends Multimedia{

	protected Tema tema;
		



	public Podcast(String nombre, String artista, int duracion, int reproducciones, int megusta, Tema tema) {
		super(nombre, artista, duracion, reproducciones, megusta);
		this.tema = tema;
	}

	public Podcast() {
		super();
		this.tema = Tema.ACTUALIDAD;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	@Override
	public String toString() {
		return nombre + " (" + artista + ")";
	}

}
