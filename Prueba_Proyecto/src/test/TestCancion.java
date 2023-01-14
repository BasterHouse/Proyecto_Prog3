package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Cancion;
import clases.Genero;
import clases.Multimedia;

public class TestCancion {
	protected Genero genero = Genero.ACOUSTIC_POP;
	protected Cancion cancion;
	protected String nombre = "nombre";
	protected String artista = "artista";
	protected Integer duracion= 0;
	protected Integer reproducciones= 2;
	protected Integer  megusta = 3;

	@Before
	public void iniciar() {
		cancion=new Cancion();
		cancion.setNombre(nombre);
		cancion.setArtista(artista);
		cancion.setDuracion(duracion);
		cancion.setMegusta(megusta);
		cancion.setReproducciones(reproducciones);
		cancion.setGenero(genero);
	}
	@Test
	public void testToString() {
		String toString=nombre + " (" + artista + ")"; 
		assertEquals(cancion.toString(),toString);
	}

	@Test
	public void testCancionStringStringIntIntIntGenero() {
		Cancion cancion2=new Cancion();
		cancion2.setNombre(nombre);
		cancion2.setArtista(artista);
		cancion2.setDuracion(0);
		cancion2.setMegusta(0);
		cancion2.setReproducciones(0);
		cancion2.setGenero(genero);
		System.out.println(cancion2);
		assertNotNull(cancion2);
		assertEquals(cancion2.getNombre(), "nombre");
		assertEquals(cancion2.getArtista(), "artista");
		assertEquals(cancion2.getDuracion(), 0,0.0);
		assertEquals(cancion2.getMegusta(), 0,0.0);
		assertEquals(cancion2.getReproducciones(), 0,0.0);
		assertEquals(cancion2.getGenero(), genero);
	}

	@Test
	public void testCancion() {
		Cancion cancion2=new Cancion();
		assertNotNull(cancion2);
		assertEquals(cancion.getGenero(), genero);

	}

	@Test
	public void testGetGenero() {
		assertEquals(cancion.getGenero(), genero);
	}

	@Test
	public void testSetGenero() {
		Genero newgenero = Genero.ART_POP;
		assertEquals(cancion.getGenero(), genero);
		cancion.setGenero(newgenero);
		assertEquals(cancion.getGenero(), newgenero);
		
		
	}

}