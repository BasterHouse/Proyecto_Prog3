package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import clases.Multimedia;

public class TestMultimedia {
	protected Multimedia multimedia;
	protected String nombre = "nombre";
	protected String artista = "artista";
	protected Integer duracion= 0;
	protected Integer reproducciones= 2;
	protected Integer  megusta = 3;
	
	@Before
	public void iniciar() {
		multimedia=new Multimedia();
		multimedia.setArtista(artista);
		multimedia.setDuracion(duracion);
		multimedia.setMegusta(megusta);
		multimedia.setNombre(nombre);
		multimedia.setReproducciones(reproducciones);

	}
	
	@Test
	public void testMultimediaStringStringIntIntInt() {
		Multimedia multimedia2=new Multimedia("nombre", "artista", 0, 0, 0);
		assertNotNull(multimedia2);
		assertEquals(multimedia2.getNombre(), "nombre");
		assertEquals(multimedia2.getArtista(), "artista");
		assertEquals(multimedia2.getDuracion(), 0,0.0);
		assertEquals(multimedia2.getMegusta(), 0,0.0);
		assertEquals(multimedia2.getReproducciones(), 0,0.0);

	}

	@Test
	public void testMultimedia() {
		Multimedia multimedia2 =new Multimedia();
		assertNotNull(multimedia2);	
		assertEquals(multimedia2.getArtista(), "");
		assertEquals(multimedia2.getDuracion(), 0,0);
		assertEquals(multimedia2.getMegusta(), 0,0);
		assertEquals(multimedia2.getNombre(), "");
		assertEquals(multimedia2.getReproducciones(), 0,0);
	

	}

	@Test
	public void testGetNombre() {
		assertEquals(multimedia.getNombre(), nombre);
	}

	@Test
	public void testSetNombre() {
		String newnombre = "nombre2";
		assertEquals(multimedia.getNombre(), nombre);
		multimedia.setNombre(newnombre);
		assertEquals(multimedia.getNombre(), newnombre);	}

	@Test
	public void testGetArtista() {
		assertEquals(multimedia.getArtista(), artista);
	}

	@Test
	public void testSetArtista() {
		String newartista = "artista2";
		assertEquals(multimedia.getArtista(), artista);
		multimedia.setArtista(newartista);
		assertEquals(multimedia.getArtista(), newartista);	
		}

	@Test
	public void testGetDuracion() {
		assertEquals(multimedia.getDuracion(), duracion, 0.0);

	}

	@Test
	public void testSetDuracion() {
		Integer  newduracion = 2;
		assertEquals(multimedia.getDuracion(), duracion, 0.0);
		multimedia.setDuracion(newduracion);
		assertEquals(multimedia.getDuracion(), newduracion, 0.0);		}

	@Test
	public void testGetReproducciones() {
		assertEquals(multimedia.getReproducciones(), reproducciones, 0.0);
		}

	@Test
	public void testSetReproducciones() {
		Integer  newrepro = 5;
		assertEquals(multimedia.getReproducciones(), reproducciones, 0.0);
		multimedia.setReproducciones(newrepro);
		assertEquals(multimedia.getReproducciones(), newrepro, 0.0);			}

	@Test
	public void testGetMegusta() {
		assertEquals(multimedia.getMegusta(), megusta, 0.0);
	}

	@Test
	public void testSetMegusta() {
		Integer  newmegusta = 4;
		assertEquals(multimedia.getMegusta(), megusta, 0.0);
		multimedia.setMegusta(newmegusta);
		assertEquals(multimedia.getMegusta(), newmegusta, 0.0);	
		}

	@Test
	public void testToString() {
		String toString="[Titulo= " + nombre + ", artista=" + artista + ", duracion=" + duracion + ", reproducciones="
					+ reproducciones + ", megusta=" + megusta + "]";	
		assertEquals(multimedia.toString(),toString);
		}
}