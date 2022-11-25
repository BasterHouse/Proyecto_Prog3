package Tests;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Clases.Podcast;
import Clases.Tema;

public class TestPodcast {
    protected Tema tema = Tema.HUMOR;
    protected Podcast podcast;

    @Before
    public void iniciar() {
        podcast=new Podcast("nombre2", "artista2", 3, 5, 6, tema);
    }

    @Test
    public void testToString() {
        String toString= "Podcast [tema=" + tema + ", nombre=" + podcast.getNombre() + ", artista=" + podcast.getArtista() + ", duracion=" + podcast.getDuracion()
                + ", reproducciones=" + podcast.getReproducciones() + ", megusta=" + podcast.getMegusta() + "]";
        assertEquals(podcast.toString(),toString);
    }

    @Test
    public void testPodcastStringStringIntIntIntTema() {
        Podcast podcast2=new Podcast("nombre", "artista", 0, 0, 0, tema);
        assertNotNull(podcast2);
        assertEquals(podcast2.getNombre(), "nombre");
        assertEquals(podcast2.getArtista(), "artista");
        assertEquals(podcast2.getDuracion(), 0,0.0);
        assertEquals(podcast2.getMegusta(), 0,0.0);
        assertEquals(podcast2.getReproducciones(), 0,0.0);
        assertEquals(podcast2.getTema(), tema);
    }

    @Test
    public void testPodcast() {
        Podcast podcast2=new Podcast();
        assertNotNull(podcast2);
        assertEquals(podcast.getTema(), tema);
    }

    @Test
    public void testGetTema() {
        assertEquals(podcast.getTema(), tema);
    }

    @Test
    public void testSetTema() {
        Tema newtema = Tema.HUMOR;
        assertEquals(podcast.getTema(), tema);
        podcast.setTema(newtema);
        assertEquals(podcast.getTema(), newtema);
    }

}