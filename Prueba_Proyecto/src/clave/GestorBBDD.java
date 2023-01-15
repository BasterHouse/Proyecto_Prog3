package clave;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Cancion;
import clases.Favorito;
import clases.Genero;
import clases.Multimedia;
import clases.Podcast;
import clases.Tema;
import clases.Usuario;

public class GestorBBDD {
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	ArrayList<Usuario> usuarios = new ArrayList<>();
	
	
	
	public void borrarBBDDUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS USUARIO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Usuario");
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
	
	
	public void insertarDatosUsuario(Usuario...usuarios) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO USUARIO (NICK, GMAIL, CONTRASEÑA) VALUES ('%s', '%s', '%s');";
			
			System.out.println("- Insertando usuario...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Usuario c : usuarios) {
				
					if (1 == stmt.executeUpdate(String.format(sql, c.getNick(), c.getGmail(), c.getContraseña()))) {					
					System.out.println(String.format(" - Usuario insertada: %s", c.toString()));
					} else {
					System.out.println(String.format(" - No se ha insertado la usuario: %s", c.toString()));
					}
				}
			} catch (SQLException ex) {
				System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}	
	
	public void insertarDatosCancion(Cancion...canciones) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CANCION (NOMBRE_C, ARTISTA_C, GENERO, DURACION_C, REPRODUCCIONES_C, MEGUSTAS_C) VALUES ('%s', '%s', '%s', '%s', '%d', '%d');";
			
			System.out.println("- Insertando cancion...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Cancion c : canciones) {
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(), c.getGenero(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					System.out.println(String.format(" - Cancion insertada: %s", c.toString()));
					} else {
					System.out.println(String.format(" - No se ha insertado la cancion: %s", c.toString()));
					}
				}
			} catch (SQLException ex) {
				System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	
	public void insertarDatosPodcast(Podcast...podcasts) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO PODCAST (NOMBRE_P, ARTISTA_P, TEMA, DURACION_P, REPRODUCCIONES_P, MEGUSTAS_P) VALUES ('%s', '%s', '%s', '%d', '%d', '%d');";	
			
			System.out.println("- Insertando podcast...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Podcast c : podcasts) {
					if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getArtista(), c.getTema(), c.getDuracion(), c.getReproducciones(), c.getMegusta()))) {					
					System.out.println(String.format(" - Podcast insertado: %s", c.toString()));
					} else {
					System.out.println(String.format(" - No se ha insertado la podcast: %s", c.toString()));
					}
				}
			} catch (SQLException ex) {
				System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	public void insertarDatosFav(Favorito... favoritos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO FAVORITO (ID_U, NOMBRE_P, NOMBRE_C, NOMBRE_U, GMAIL_U) VALUES ('%d', '%s', '%s', '%s', '%s');";	
			
			System.out.println("- Insertando favorito...");
			
			//Se recorren los multimedias y se insertan uno a uno
			for (Favorito c : favoritos) {
					if (1 == stmt.executeUpdate(String.format(sql,c.getUsuario().getId() , c.getNombrePodcast(), c.getNombreCancion(), c.getUsuario().getNick(), c.getUsuario().getGmail()))) {					
					System.out.println(String.format(" - Favorita insertada: %s", c.toString()));
					} else {
					System.out.println(String.format(" - No se ha insertado la favorita: %s", c.toString()));
					}
				}

			} catch (SQLException ex) {
				System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();	
			}	
			
		}
	
	
	
	
	
		
		
		
	public ArrayList<Usuario> obtenerDatosUsuario() {
		
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
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
			System.out.println(String.format("- Se han recuperado %d usuarios...", usuarios.size()));	
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		return usuarios;
	}
	

	
	
	public ArrayList<Cancion> obtenerDatosCancion() {
		ArrayList<Cancion> canciones = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
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
			
			System.out.println(String.format("- Se han recuperado %d canciones...", canciones.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return canciones;
	}
	
	
	
	
	public ArrayList<Podcast> obtenerDatosPodcast() {
		ArrayList<Podcast> podcasts = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
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
			
			System.out.println(String.format("- Se han recuperado %d podcasts...", podcasts.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return podcasts;
	}
	
	public ArrayList<Favorito> obtenerDatosFavoritos() {
		ArrayList<Favorito> favoritos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
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
			
			System.out.println(String.format("- Se han recuperado %d canciones favoritas...", favoritos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return favoritos;
	}
	
	
	
	
	
	
	public void borrarDatosUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM USUARIO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d usuarios", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
		
		public void borrarDatosCancion() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM CANCION;";			
				int result = stmt.executeUpdate(sql);
				
				System.out.println(String.format("- Se han borrado %d canciones", result));
			} catch (Exception ex) {
				System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}	
		
		public void borrarDatosPodcast() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM PODCAST;";			
				int result = stmt.executeUpdate(sql);
				
				System.out.println(String.format("- Se han borrado %d podcasts", result));
			} catch (Exception ex) {
				System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}
	
		public void borrarDatosFav() {
			//Se abre la conexión y se obtiene el Statement
			try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
			     Statement stmt = con.createStatement()) {
				//Se ejecuta la sentencia de borrado de datos
				String sql = "DELETE FROM FAVORITO;";			
				int result = stmt.executeUpdate(sql);
				
				System.out.println(String.format("- Se han borrado %d favoritos", result));
			} catch (Exception ex) {
				System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
				ex.printStackTrace();						
			}		
		}
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	
	
	/*public void crearBBDDCancion() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CANCION);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS CANCION (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " ARTISTA TEXT NOT NULL,\n"
	                   + " GENERO ENUM NOT NULL,\n"
	                   + " DURACION TEXT NOT NULL, \n"
	                   + " REPRODUCCIONES TEXT NOT NULL, \n"
	                   + " MEGUSTAS TEXT NOT NULL \n"
	                   + ");";
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Cancion");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDDCancion() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CANCION);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS CANCION";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Cancion");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_CANCION));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	
		*/	
	
	
	
	
	
	
	/*
	
	public void actualizarNombreCancion(Cancion cancion, String newNombre) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CANCION);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE CANCION SET NOMBRE = '%s' WHERE ID = %d;";
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, cancion.getId()));
			
			System.out.println(String.format("- Se ha actulizado %d canciones", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
	
//---------------------------------------------------------------------------------------------------------------//
	public void crearBBDDPodcast() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PODCAST);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "CREATE TABLE IF NOT EXISTS PODCAST (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " ARTISTA TEXT NOT NULL,\n"
	                   + " TEMA ENUM NOT NULL,\n"
	                   + " DURACION TEXT NOT NULL, \n"
	                   + " REPRODUCCIONES TEXT NOT NULL, \n"
	                   + " MEGUSTAS TEXT NOT NULL \n"
	                   + ");";
	        	        
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Podcast");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDDPodcast() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PODCAST);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS PODCAST";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Podcast");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_PODCAST));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	
		*/	
	
	
	
	
	/*
	
	
	public void actualizarNombrePodcast(Podcast podcast, String newNombre) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PODCAST);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE PODCAST SET NOMBRE = '%s' WHERE ID = %d;";
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, podcast.getId()));
			
			System.out.println(String.format("- Se ha actulizado %d podcasts", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}*/
}
