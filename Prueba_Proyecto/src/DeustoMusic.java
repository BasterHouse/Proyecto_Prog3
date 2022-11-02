import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Stream;


//Algunas de las funciones han tenido que ser static por el momento, 
//cuando añadamos las ventanas se harán algunos cambios, por eso dejamos los warnings


public class DeustoMusic {

		protected Multimedia multimedia;
		protected static TreeMap<String, ArrayList<Multimedia>> listademedia = new TreeMap<String, ArrayList<Multimedia>>();
		protected static TreeMap<String, ArrayList<Multimedia>> playlist = new TreeMap<String, ArrayList<Multimedia>>();
		protected static TreeMap<Usuario, ArrayList<TreeMap<String, ArrayList<Multimedia>>>> listaporusuario = new TreeMap<Usuario, ArrayList<TreeMap<String, ArrayList<Multimedia>>>>();
		protected static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		protected static ArrayList<TreeMap<String, ArrayList<Multimedia>>> listaPlaylist = new ArrayList<TreeMap<String, ArrayList<Multimedia>>>();
		protected static final String DRIVER_NAME = "org.sqlite.JDBC";
		protected static final String DATABASE_FILE = "db/database.db";
		protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;

	public DeustoMusic(Multimedia multimedia, TreeMap<String, ArrayList<Multimedia>> playlist,
				TreeMap<Usuario, ArrayList<TreeMap<String, ArrayList<Multimedia>>>> listaporusuario) {
			super();
			this.multimedia = multimedia;
		}


	public DeustoMusic() {
		super();
		this.multimedia = new Multimedia();
		try {
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	

	public Multimedia getMultimedia() {
		return multimedia;
	}


	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}


	public static TreeMap<String, ArrayList<Multimedia>> getListademedia() {
		return listademedia;
	}


	public static void setListademedia(TreeMap<String, ArrayList<Multimedia>> listademedia) {
		DeustoMusic.listademedia = listademedia;
	}


	public static TreeMap<String, ArrayList<Multimedia>> getPlaylist() {
		return playlist;
	}


	public static void setPlaylist(TreeMap<String, ArrayList<Multimedia>> playlist) {
		DeustoMusic.playlist = playlist;
	}


	public static TreeMap<Usuario, ArrayList<TreeMap<String, ArrayList<Multimedia>>>> getListaporusuario() {
		return listaporusuario;
	}


	public static void setListaporusuario(
			TreeMap<Usuario, ArrayList<TreeMap<String, ArrayList<Multimedia>>>> listaporusuario) {
		DeustoMusic.listaporusuario = listaporusuario;
	}


	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}


	public static void setUsuarios(ArrayList<Usuario> usuarios) {
		DeustoMusic.usuarios = usuarios;
	}


	public static ArrayList<TreeMap<String, ArrayList<Multimedia>>> getListaPlaylist() {
		return listaPlaylist;
	}


	public static void setListaPlaylist(ArrayList<TreeMap<String, ArrayList<Multimedia>>> listaPlaylist) {
		DeustoMusic.listaPlaylist = listaPlaylist;
	}


	public void crearBBDD() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS MULTIMEDIA (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " ARTISTA TEXT NOT NULL,\n"
	                   + " DURACION TEXT NOT NULL, \n"
	                   + " REPRODUCCIONES TEXT NOT NULL, \n"
	                   + " MEGUSTAS TEXT NOT NULL \n"
	                   + ");";
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Multimedia");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDD() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS MULTIMEDIA";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Multimedia");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
		//Cargar el contenido multimedia de la base de datos en el programa
	public static ArrayList<Multimedia> inicializar() {
		ArrayList<Multimedia> multimedias = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("top10ss.csv"))){
			String linea;
			StringTokenizer tokenizer;
			Multimedia multimedia;
			Cancion cancion;
			Podcast podcast;
			String tipo;
			String clase;
			listademedia.put("Podcasts", new ArrayList<Multimedia>());
			listademedia.put("Canciones", new ArrayList<Multimedia>());
			
			in.readLine();
			while((linea = in.readLine())!= null) {
				tokenizer= new StringTokenizer(linea,";");
				podcast = new Podcast();
				cancion = new Cancion();
				multimedia = new Multimedia();
				tokenizer.nextToken();
				multimedia.setNombre(tokenizer.nextToken());
				multimedia.setArtista(tokenizer.nextToken());
				tipo= tokenizer.nextToken();
				tokenizer.nextToken();
				multimedia.setDuracion(Integer.parseInt(tokenizer.nextToken()));
				clase = tokenizer.nextToken();
				multimedia.setReproducciones(Integer.parseInt(tokenizer.nextToken()));
				multimedia.setMegusta(0);
				
				multimedias.add(multimedia);
				//Creacion de una futura treemap
				/*if (clase.equals("Cancion")){
					cancion.setNombre(multimedia.getNombre());
					cancion.setArtista(multimedia.getArtista());
					cancion.setDuracion(multimedia.getDuracion());
					cancion.setReproducciones(multimedia.getReproducciones());
					cancion.setMegusta(multimedia.getMegusta());
					for (Genero genero : Genero.values()) {
						if (tipo.equals(genero+ "")) {
							cancion.setGenero(genero);
						}
					}
					
					
					listademedia.get("Canciones").add(cancion);	
				} else {
					podcast.setNombre(multimedia.getNombre());
					podcast.setArtista(multimedia.getArtista());
					podcast.setDuracion(multimedia.getDuracion());
					podcast.setReproducciones(multimedia.getReproducciones());
					podcast.setMegusta(multimedia.getMegusta());
					for (Tema tema : Tema.values()) {
						if (tipo.equals(tema+ "")) {
							podcast.setTema(tema);
						}
					}
					listademedia.get("Podcasts").add(podcast);
				}*/
				
				
			}
			
			
			//System.out.println(listademedia);
			System.out.println(multimedias);
			
		}catch (Exception ex) {
			System.err.println("Error en el main: " +ex);
			ex.printStackTrace();
		}
		return multimedias;
	}
	
	public void insertarDatos(Multimedia...multimedias) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO MULTIMEDIA (NOMBRE, ARTISTA, DURACION, REPRODUCCIONES, MEGUSTAS) VALUES ('%s', '%s', '%d', '%d', '%d');";
			
			System.out.println("- Insertando multimedia...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Multimedia c : multimedias) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					System.out.println(String.format(" - Multimedia insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el multimedia: %s", c.toString()));
				}
				/*if (c instanceof Cancion) {
					((Cancion)c).getGenero();
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(),((Cancion) c).getGenero(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					System.out.println(String.format(" - Multimedia insertado: %s", c.toString()));
					} else {
					System.out.println(String.format(" - No se ha insertado el multimedia: %s", c.toString()));
				}
				} else{
					((Podcast)c).getTema();
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(),((Podcast) c).getTema(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
						System.out.println(String.format(" - Multimedia insertado: %s", c.toString()));
					} else {
						System.out.println(String.format(" - No se ha insertado el multimedia: %s", c.toString()));
					}

				}*/
				
			}	
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	
	public ArrayList<Multimedia> obtenerDatos() {
		ArrayList<Multimedia> multimedias = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM MULTIMEDIA WHERE ID >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Multimedia multimedia;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				multimedia = new Multimedia();
				
				multimedia.setId(rs.getInt("ID"));
				multimedia.setNombre(rs.getString("NOMBRE"));
				multimedia.setArtista(rs.getString("ARTISTA"));
				multimedia.setDuracion(rs.getInt("DURACION"));
				multimedia.setReproducciones(rs.getInt("REPRODUCCIONES"));
				multimedia.setMegusta(rs.getInt("MEGUSTAS"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				multimedias.add(multimedia);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d multimedias...", multimedias.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return multimedias;
	}
	public void borrarDatos() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM MULTIMEDIA;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d multimedias", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
	
	public void actualizarNombre(Multimedia multimedia, String newNombre) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE MULTIMEDIA SET NOMBRE = '%s' WHERE ID = %d;";
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, multimedia.getId()));
			
			System.out.println(String.format("- Se ha actulizado %d multimedias", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
	
	public static void llenarPlaylist() {
		String titulo;
		Multimedia relleno;
		//El titulo y las canciones tendremos que añadirlas desde la ventana, esto es una comprobación
		for (int i = 0; i < 3; i++) {
			titulo = i+"";
			playlist.put(titulo, new ArrayList<Multimedia>());
			for (int j = 0; j < 3; j++) {
				relleno = listademedia.get("Canciones").get( (int) (Math.random()*25+1));
			playlist.get(titulo).add(relleno);
			}
			listaPlaylist.add(playlist);
		}
		
		System.out.println(playlist);
	}
	
	
	public static void meterUsuario() {
		Usuario usuario;
		for (int i = 0; i < 3; i++) {
			usuario = new Usuario("Usuario" + i+"", "usuario"+i+""+"gmail.com", i+"");
			usuarios.add(usuario);
		}
		System.out.println(usuarios);
	}
	
	
	//Queria meter una playlist en cada usuario pero ns como
	public static void usuarioPlaylist( ) {
		for (Usuario usuario : usuarios) {
				listaporusuario.put(usuario, new ArrayList<TreeMap<String, ArrayList<Multimedia>>>());
				for (TreeMap<String, ArrayList<Multimedia>> playlist : listaPlaylist) {
					listaporusuario.get(usuario).add(playlist);
				}
		}System.out.println(listaporusuario);
		
			
	}
	
		//Sin terminar
	/*public static void guardarDatos() {
		int posicion =-1;
		String listaposicion = "Lista"+posicion;
		try {
			PrintWriter pw = new PrintWriter("usuarios.csv");
			for (Usuario usuario : usuarios) {
				for (TreeMap<String, ArrayList<Multimedia>> lista : listaporusuario.get(usuario)) {
					posicion++;
					//posicion+"";
					for (Multimedia multimedia : lista.get(listaposicion)) {
						
						pw.println(multimedia);
					}
				}
			}
			
			
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos CSV.");
		}
	}	*/
}

