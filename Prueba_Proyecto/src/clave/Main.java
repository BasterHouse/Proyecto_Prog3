package clave;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import clases.Cancion;
import clases.Multimedia;
import clases.Podcast;
import ventanas.VentanaDeustomusic;

//ALGUNAS DE LAS FUNCIONES ESTÁN COMENTADAS PORQUE SE USARÁN MÁS ADELANTE JUNTO CON LAS VENTANAS
public class Main {
	public static void main(String[] args) {
		DeustoMusic deustomusic = new DeustoMusic();
		GestorBBDD gestor = new GestorBBDD();
		List<Cancion> canciones = new ArrayList<Cancion>();
		List<Podcast> podcasts = new ArrayList<Podcast>();
		
		
		
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
		deustomusic.meterUsuario();
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
	}
	
	//public void ventanaMusic (DeustoMusic deustomusic) {
	//	VentanaDeustomusic ventanamusic = new VentanaDeustomusic(deustomusic);
	//	ventanamusic.setVisible(true);	
	//}
	
	
}
