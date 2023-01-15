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
import clases.Favorito;
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
		protected TreeMap<Usuario, ArrayList<Multimedia>> favoritos = new TreeMap<Usuario, ArrayList<Multimedia>>();
		protected List<Favorito> listaFav = new ArrayList<Favorito>();
		protected Usuario usuario;



	

	public DeustoMusic(Multimedia multimedia, TreeMap<Usuario, ArrayList<Multimedia>> favoritos,
				List<Favorito> listaFav, Usuario usuario) {
			super();
			this.multimedia = multimedia;
			this.favoritos = favoritos;
			this.listaFav = listaFav;
			this.usuario = usuario;
		}
	
	public DeustoMusic() {
		super();
		this.multimedia = multimedia;
		this.favoritos = favoritos;
		this.listaFav = listaFav;
		this.usuario = usuario;
	}


	public List<Favorito> getListaFav() {
		return listaFav;
	}

	public void setListaFav(List<Favorito> listaFav) {
		this.listaFav = listaFav;
	}

	public TreeMap<Usuario, ArrayList<Multimedia>> getFavoritos() {
			return favoritos;
		}


		public void setFavoritos(TreeMap<Usuario, ArrayList<Multimedia>> favoritos) {
			this.favoritos = favoritos;
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
	
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

			
		}catch (Exception ex) {
			System.err.println("Error en el main: " +ex);
			ex.printStackTrace();
		}
		return listademedia;
	}

	
}

