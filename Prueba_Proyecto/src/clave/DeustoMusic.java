package clave;
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

import clases.Cancion;
import clases.Genero;
import clases.Multimedia;
import clases.Podcast;
import clases.Tema;
import clases.Usuario;


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



		//Cargar el contenido multimedia de la base de datos en el programa
	public static TreeMap<String, ArrayList<Multimedia>> inicializar() {
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
				//Creacion de una treemap de canciones y podcasts
				if (clase.equals("Cancion")){
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
				}
				
			}
			
			
			//System.out.println(listademedia);
			//System.out.println(multimedias);
			
		}catch (Exception ex) {
			System.err.println("Error en el main: " +ex);
			ex.printStackTrace();
		}
		return listademedia;
	}
	
	public TreeMap<String, ArrayList<Multimedia>> llenarPlaylist() {
		String titulo;
		Multimedia relleno;
		//El titulo y las canciones tendremos que añadirlas desde la ventana, esto es una comprobación
		for (int i = 0; i < 3; i++) {
			titulo = "Lista" + i;
			playlist.put(titulo, new ArrayList<Multimedia>());
			for (int j = 0; j < 3; j++) {
				relleno = listademedia.get("Canciones").get( (int) (Math.random()*25+1));
			playlist.get(titulo).add(relleno);
			}
			listaPlaylist.add(playlist);
		}
		
		
		return playlist;
	}
		
	
	
	
	public void usuarioPlaylist( ) {
		for (Usuario usuario : usuarios) {
				listaporusuario.put(usuario, new ArrayList<TreeMap<String, ArrayList<Multimedia>>>());
				for (TreeMap<String, ArrayList<Multimedia>> playlist : listaPlaylist) {
					listaporusuario.get(usuario).add(playlist);
				}
		};
		
			
	}
	
}

