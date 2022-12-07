package clave;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import clases.Cancion;
import clases.Multimedia;
import clases.Podcast;
import clases.Usuario;
import ventanas.VentanaDeustomusic;
import ventanas.VentanaPrinc;

//ALGUNAS DE LAS FUNCIONES ESTÁN COMENTADAS PORQUE SE USARÁN MÁS ADELANTE JUNTO CON LAS VENTANAS
public class Main {
	public static void main(String[] args) {
		DeustoMusic deustomusic = new DeustoMusic();
		GestorBBDD gestor = new GestorBBDD();
		List<Cancion> canciones = new ArrayList<Cancion>();
		List<Podcast> podcasts = new ArrayList<Podcast>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		
		TreeMap<String, ArrayList<Multimedia>> listademedia =deustomusic.inicializar();
		for (Multimedia cancion : listademedia.get("Canciones")) {
			if (cancion instanceof Cancion) {
				canciones.add((Cancion)cancion);
			}
		}
		
		for (Multimedia podcast : listademedia.get("Podcasts")) {
			if (podcast instanceof Podcast) {
				podcasts.add((Podcast)podcast);
			}
		}
		
		deustomusic.llenarPlaylist();
		deustomusic.usuarioPlaylist();
		
		//BASE DE DATOS DE LAS CANCIONES
		gestor.crearBBDDCancion();
		gestor.insertarDatosCancion(canciones.toArray(new Cancion[canciones.size()]));
		canciones = gestor.obtenerDatosCancion();
		gestor.actualizarNombreCancion(canciones.get(0), "BorjaTeQueremos");
		canciones = gestor.obtenerDatosCancion();
		gestor.borrarDatosCancion();
		gestor.borrarBBDDCancion();
		
		//BASE DE DATOS DE LOS PODCASTS
		gestor.crearBBDDPodcast();
		gestor.insertarDatosPodcast(podcasts.toArray(new Podcast[podcasts.size()]));
		podcasts = gestor.obtenerDatosPodcast();
		gestor.actualizarNombrePodcast(podcasts.get(0), "BorjaTeQueremos");
		podcasts = gestor.obtenerDatosPodcast();
		gestor.borrarDatosPodcast();
		gestor.borrarBBDDPodcast();
		
		VentanaDeustomusic ventanamusic = new VentanaDeustomusic(deustomusic);
		ventanamusic.setVisible(true);	
		VentanaPrinc princ = new VentanaPrinc();
		princ.setVisible(true);	
	}
	
	public void meterUsuario(VentanaPrinc princ){
		if (princ.nick == null || princ.gmail == null || princ.contraseña == null) {
			System.err.println("Rellene todos los huecos"); 
		}else {
			
		}
		
	}

	
	
}
