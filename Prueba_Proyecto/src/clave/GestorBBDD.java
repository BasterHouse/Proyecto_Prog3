package clave;
import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import clases.Cancion;
import clases.Favorito;
import clases.Genero;
import clases.Multimedia;
import clases.Podcast;
import clases.Tema;
import clases.Usuario;

public class GestorBBDD {
	private final static String PROPERTIES_FILE = "conf/app.properties";
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	protected Properties properties;
	private String driverName;
	private String databaseFile;
	private String connectionString;
	
	
	private static Logger logger = Logger.getLogger(GestorBBDD.class.getName());
	
	public GestorBBDD() {
		try (FileInputStream fis = new FileInputStream("conf/logger.properties")) {
			//Inicialización del Logger
			LogManager.getLogManager().readConfiguration(fis);
			
			//Lectura del fichero properties
			properties = new Properties();
			properties.load(new FileReader(PROPERTIES_FILE));
			
			driverName = properties.getProperty("driver");
			databaseFile = properties.getProperty("file");
			connectionString = properties.getProperty("connection");
			
			//Cargar el diver SQLite
			Class.forName(driverName);
		} catch (Exception ex) {
			logger.warning(String.format("Error al cargar el driver de BBDD: %s", ex.getMessage()));
		}
	}
	
	
	public void borrarBBDDUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS USUARIO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	logger.info("- Se ha borrado la tabla Usuario");
	        }
		} catch (Exception ex) {
			logger.warning(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(databaseFile));
			logger.info("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			logger.warning(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	
	public void insertarDatosUsuario(Usuario...usuarios) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO USUARIO (NICK, GMAIL, CONTRASEÑA) VALUES ('%s', '%s', '%s');";
			
			logger.info("- Insertando usuario...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Usuario c : usuarios) {
				
					if (1 == stmt.executeUpdate(String.format(sql, c.getNick(), c.getGmail(), c.getContraseña()))) {					
					logger.info(" - Usuario insertada: %s");
					} else {
					logger.info(" - No se ha insertado la usuario: %s");
					}
				}
			} catch (SQLException ex) {
				logger.warning(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}	
	
	public void insertarDatosCancion(Cancion...canciones) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CANCION (NOMBRE_C, ARTISTA_C, GENERO, DURACION_C, REPRODUCCIONES_C, MEGUSTAS_C) VALUES ('%s', '%s', '%s', '%s', '%d', '%d');";
			
			logger.info("- Insertando cancion...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Cancion c : canciones) {
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(), c.getGenero(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					logger.info(" - Cancion insertada: %s");
					} else {
					logger.info(" - No se ha insertado la cancion: %s");
					}
				}
			} catch (SQLException ex) {
				logger.warning(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	
	public void insertarDatosPodcast(Podcast...podcasts) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO PODCAST (NOMBRE_P, ARTISTA_P, TEMA, DURACION_P, REPRODUCCIONES_P, MEGUSTAS_P) VALUES ('%s', '%s', '%s', '%d', '%d', '%d');";	
			
			logger.info("- Insertando podcast...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Podcast c : podcasts) {
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(), c.getTema(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					logger.info(" - Podcast insertado: %s");
					} else {
					logger.info(" - No se ha insertado la podcast: %s");
					}
				}
			} catch (SQLException ex) {
				logger.warning(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	public void insertarDatosFav(Favorito... favoritos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO FAVORITO (ID_U, NOMBRE_P, NOMBRE_C, NOMBRE_U, GMAIL_U) VALUES ('%d', '%s', '%s', '%s', '%s');";	
			
			logger.info("Cancion favorita insertada");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Favorito c : favoritos) {
					if (1 == stmt.executeUpdate(String.format(sql,c.getUsuario().getId() , c.getNombrePodcast(), c.getNombreCancion(), c.getUsuario().getNick(), c.getUsuario().getGmail()))) {					
					logger.info(" - Favorita insertada: %s");
					} else {
					logger.info(" - No se ha insertado la favorita: %s");
					}
				}

			} catch (SQLException ex) {
				logger.warning(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	
	
	
	
		
		
		
	public ArrayList<Usuario> obtenerDatosUsuario() {
		
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM USUARIO WHERE ID >= 0";			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Usuario usuario;
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				usuario = new Usuario();
				
				usuario.setId(rs.getInt("ID"));
				usuario.setNick(rs.getString("NICK"));
				usuario.setGmail(rs.getString("GMAIL"));
				usuario.setContraseña(rs.getString("CONTRASEÑA"));	
				//Se inserta cada nuevo cliente en la lista de clientes
				usuarios.add(usuario);
			}	
			//Se cierra el ResultSet
			rs.close();
			logger.info("- Se han recuperado %d usuarios...");

	
			
		} catch (Exception ex) {
			logger.warning(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		return usuarios;
	}
	

	
	
	public ArrayList<Cancion> obtenerDatosCancion() {
		ArrayList<Cancion> canciones = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
			Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CANCION WHERE ID_C >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Cancion cancion;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				cancion = new Cancion();
				cancion.setNombre(rs.getString("NOMBRE_C"));
				cancion.setArtista(rs.getString("ARTISTA_C"));
				cancion.setGenero(Genero.valueOf(rs.getString("GENERO")));
				cancion.setDuracion(rs.getInt("DURACION_C"));
				cancion.setReproducciones(rs.getInt("REPRODUCCIONES_C"));
				cancion.setMegusta(rs.getInt("MEGUSTAS_C"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				canciones.add(cancion);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info("- Se han recuperado %d canciones...");			
		} catch (Exception ex) {
			logger.warning(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return canciones;
	}
	
	
	
	
	public ArrayList<Podcast> obtenerDatosPodcast() {
		ArrayList<Podcast> podcasts = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
			     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM PODCAST WHERE ID_P >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Podcast podcast;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				podcast = new Podcast();
				
				//podcast.setId(rs.getInt("ID_P"));
				podcast.setNombre(rs.getString("NOMBRE_P"));
				podcast.setArtista(rs.getString("ARTISTA_P"));
				podcast.setTema(Tema.valueOf(rs.getString("TEMA")));
				podcast.setDuracion(rs.getInt("DURACION_P"));
				podcast.setReproducciones(rs.getInt("REPRODUCCIONES_P"));
				podcast.setMegusta(rs.getInt("MEGUSTAS_P"));
				
				//Se inserta cada nuevo cliente en la lista de clientes
				podcasts.add(podcast);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info("- Se han recuperado %d podcasts...");			
		} catch (Exception ex) {
			logger.warning(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return podcasts;
	}
	
	public ArrayList<Favorito> obtenerDatosFavoritos() {
		ArrayList<Favorito> favoritos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
			     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM FAVORITO WHERE ID_U >= 0";
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);			
			Favorito favorito;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				favorito = new Favorito();
				for (Usuario u : usuarios) {
					if(rs.getInt("ID_U")==(u.getId())) {
						favorito.setUsuario(u);
					}
				}
				favorito.setNombreCancion(rs.getString("NOMBRE_C"));
				favorito.setNombrePodcast(rs.getString("NOMBRE_P"));
				
				
				//Se inserta cada nuevo cliente en la lista de clientes
				favoritos.add(favorito);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info("- Se han recuperado %d canciones favoritas...");			
		} catch (Exception ex) {
			logger.warning(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return favoritos;
	}
	
	
	
	
	
	
	public void borrarDatosUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(connectionString);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM USUARIO;";			
			int result = stmt.executeUpdate(sql);
			
			logger.info("- Se han borrado %d usuarios");
		} catch (Exception ex) {
			logger.warning(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
		
		public void borrarDatosCancion() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(connectionString);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM CANCION;";			
				int result = stmt.executeUpdate(sql);
				
				logger.info("- Se han borrado %d canciones");
			} catch (Exception ex) {
				logger.warning(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}	
		
		public void borrarDatosPodcast() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(connectionString);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM PODCAST;";			
				int result = stmt.executeUpdate(sql);
				
				logger.info("- Se han borrado %d podcasts");
			} catch (Exception ex) {
				logger.warning(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}
	
		public void borrarDatosFav() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(connectionString);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM FAVORITO;";			
				int result = stmt.executeUpdate(sql);
				
				logger.info( "- Se han borrado %d favoritos" );
			} catch (Exception ex) {
				logger.warning(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}
	
}
