import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;



public class Main {
	public static void main(String[] args) {
		DeustoMusic deustomusic = new DeustoMusic();
		
		ArrayList<Multimedia> multimedias =deustomusic.inicializar();
		//deustomusic.llenarPlaylist();
		//deustomusic.meterUsuario();
		//deustomusic.usuarioPlaylist();
		deustomusic.crearBBDD();
		deustomusic.insertarDatos(multimedias.toArray(new Multimedia[multimedias.size()]));
		multimedias = deustomusic.obtenerDatos();
		System.out.println(multimedias);
		deustomusic.actualizarNombre(multimedias.get(0), "BorjaTeQueremos");
		multimedias = deustomusic.obtenerDatos();
		deustomusic.borrarDatos();
		deustomusic.borrarBBDD();
	}
	
	
}
