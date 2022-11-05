package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Cancion;
import clases.Genero;

public class TestCancion {
	protected Genero genero = Genero.ACOUSTIC_POP;
	protected Cancion cancion;	

	@Before
	public void iniciar() {
		cancion=new Cancion("nombre2", "artista2", 3, 5, 6, genero);
	}
	@Test
	public void testToString() {
		String toString="Cancion [genero=" + genero + ", nombre=" + cancion.getNombre() + ", artista=" + cancion.getArtista() + ", duracion=" + cancion.getDuracion()
				+ ", reproducciones=" + cancion.getReproducciones() + ", megusta=" + cancion.getMegusta() + "]";
		assertEquals(cancion.toString(),toString);
	}

	@Test
	public void testCancionStringStringIntIntIntGenero() {
		Cancion cancion2=new Cancion("nombre", "artista", 0, 0, 0, genero);
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