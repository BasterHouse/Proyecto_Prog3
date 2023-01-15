package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.swing.SwingConstants;

import clases.Cancion;
import clases.Favorito;
import clases.Multimedia;
import clases.Podcast;
import clases.Usuario;
import clave.DeustoMusic;
import clave.GestorBBDD;
import test.TestUsuario;

import javax.swing.JTextField;

public class VentanaPrinc extends JFrame{
	private JTextField textNick;
	private JTextField textGmail;
	private JTextField textContraseña;
	private JButton registrar = new JButton("Registrar");
	private JButton acceder = new JButton("Acceder");
	private JLabel uno = new JLabel("Nick");
	private JLabel dos = new JLabel("Gmail");
	private JLabel tres = new JLabel("Contraseña");
	private String nick;
	private String gmail;
	private String contraseña;
	
	
	
	DeustoMusic deustomusic = new DeustoMusic();
	GestorBBDD gestor = new GestorBBDD();
	List<Cancion> canciones = new ArrayList<Cancion>();
	List<Podcast> podcasts = new ArrayList<Podcast>();
	List<Usuario> usuarios = new ArrayList<Usuario>();
	List<Favorito> favoritos = new ArrayList<Favorito>();
	TreeMap<Usuario, ArrayList<Multimedia>> mapaFav = new TreeMap<>();
	
	
	TreeMap<String, ArrayList<Multimedia>> listademedia = deustomusic.inicializar();
	
	

	public VentanaPrinc() {
		
		
		
		/*for (Multimedia cancion : listademedia.get("Canciones")) {
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
		deustomusic.usuarioPlaylist();*/
		
		
		//BASE DE DATOS DE LOS USUARIOS
		//gestor.borrarBBDDUsuario();
		//gestor.crearBBDDUsuario();
		//gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
		//gestor.actualizarNombreUsuario(usuarios.get(0), "BorjaTeQueremos");
		usuarios = gestor.obtenerDatosUsuario();
		canciones = gestor.obtenerDatosCancion();
		if (canciones.size()==0) {
			System.out.println("Obteniendo canciones desde el csv...");
			for (Multimedia cancion : listademedia.get("Canciones")) {
				if (cancion instanceof Cancion) {
					canciones.add((Cancion)cancion);
				}
			}
			gestor.insertarDatosCancion(canciones.toArray(new Cancion[canciones.size()]));
		}
		
		podcasts = gestor.obtenerDatosPodcast();
		if(podcasts.size()==0) {
			System.out.println("Obteniendo podcasts desde el csv...");
			for (Multimedia podcast : listademedia.get("Podcasts")) {
				if (podcast instanceof Podcast) {
					podcasts.add((Podcast)podcast);
				}
			}
			gestor.insertarDatosPodcast(podcasts.toArray(new Podcast[podcasts.size()]));
		}
		

		//gestor.borrarDatosCancion();
		//gestor.borrarDatosUsuario();
		//gestor.borrarDatosPodcast();
		//gestor.borrarDatosFav();
		//gestor.borrarBBDDUsuario();
		
		
		
		setTitle("DeustoMusic");
		setSize(600, 400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("DeustoMusic");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Stencil", Font.PLAIN, 20));
		titulo.setBounds(76, 6, 434, 61);
		getContentPane().add(titulo);
		
		JButton iniciarsesion = new JButton("Iniciar Sesion");
		iniciarsesion.setBounds(86, 79, 134, 23);
		getContentPane().add(iniciarsesion);
		
		JButton crearusuario = new JButton("Crear Usuario");
		crearusuario.setBounds(331, 79, 134, 23);
		getContentPane().add(crearusuario);
		
		textNick = new JTextField();
		textNick.setBounds(209, 144, 120, 39);
		getContentPane().add(textNick);
		textGmail= new JTextField();
		textGmail.setBounds(209, 211, 120, 39);
		getContentPane().add(textGmail);
		textContraseña = new JTextField();
		textContraseña.setBounds(209, 279, 120, 39);
		getContentPane().add(textContraseña);
		textContraseña.setColumns(10);
		
		registrar.setBounds(421, 216, 117, 29);
		getContentPane().add(registrar);
		acceder.setBounds(45, 216, 117, 29);
		getContentPane().add(acceder);
		
		uno.setBounds(209, 128, 61, 16);
		getContentPane().add(uno);
		
		
		dos.setBounds(209, 194, 61, 16);
		getContentPane().add(dos);
		
		
		tres.setBounds(209, 261, 84, 16);
		getContentPane().add(tres);
		

		textGmail.setVisible(false);
		textNick.setVisible(false);
		textContraseña.setVisible(false);
		registrar.setVisible(false);
		acceder.setVisible(false);
		uno.setVisible(false);
		dos.setVisible(false);
		tres.setVisible(false);
		
		
		
		iniciarsesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				crearUsu();
				acceder.setVisible(true);
				registrar.setVisible(false);
			}
		});
		
		
		
		crearusuario.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			crearUsu();
			registrar.setVisible(true);
			acceder.setVisible(false);
			
		}
	});
		
		acceder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean existe = false;
				nick = textNick.getText();
				gmail = textGmail.getText();
				contraseña = textContraseña.getText();
				if (nick.equals("")|| gmail.equals("") || contraseña.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(),
						    "Rellene todos los huecos",
						    "Error",
						    JOptionPane.ERROR_MESSAGE); 
				}else {
					Usuario nuevo = new Usuario(nick, gmail, contraseña, 0);
					for (Usuario u : usuarios) {
						if (u.getNick().equals(nuevo.getNick()) && u.getGmail().equals(nuevo.getGmail()) && u.getContraseña().equals(nuevo.getContraseña())) {
							existe = true;	
							break;
						} else  {
							existe = false;
						}
					}
					if (existe) {
						usuarios= gestor.obtenerDatosUsuario();
						for (Usuario u : usuarios) {
							if(u.getNick().equals(nuevo.getNick()) && u.getGmail().equals(nuevo.getGmail())) {
								deustomusic.setUsuario(u);
							}
						}
						favoritos.clear();
						mapaFav.clear();
						insertaFav();						
						VentanaDeustomusic ventanamusic = new VentanaDeustomusic(deustomusic);
						ventanamusic.setVisible(true);
					
					} else {
						JOptionPane.showMessageDialog(getContentPane(),
							    "Este usuario no existe.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
		registrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			String nick = textNick.getText();
			String gmail = textGmail.getText();
			contraseña = textContraseña.getText();
			boolean existe = false;
				if (nick.equals("")|| gmail.equals("")|| contraseña.equals("")) {
					existe = true;
					JOptionPane.showMessageDialog(getContentPane(),
						    "Rellene todos los huecos",
						    "Error",
						    JOptionPane.ERROR_MESSAGE); 
				}else {
					for (Usuario usuario : usuarios) {
						if (usuario.getNick().equals(nick) && usuario.getGmail().equals(gmail)){
							existe = true;
							break;
						}
						}
					}
					if(existe) {
						JOptionPane.showMessageDialog(getContentPane(),
								    "Este usuario ya existe",
								    "Error",
								    JOptionPane.ERROR_MESSAGE); 
					} else {
						Usuario nuevo = new Usuario(nick, gmail, contraseña, 0);
						usuarios.add(nuevo);
						gestor.insertarDatosUsuario(usuarios.toArray(new Usuario[usuarios.size()]));
						usuarios= gestor.obtenerDatosUsuario();
						for (Usuario u : usuarios) {
							if(u.getNick().equals(nuevo.getNick()) && u.getGmail().equals(nuevo.getGmail())) {
								deustomusic.setUsuario(u);
							}
						}
						VentanaDeustomusic ventanamusic = new VentanaDeustomusic(deustomusic);
						ventanamusic.setVisible(true);
					}
				}

			
		});
		
		
		

	}
	
		

	
	public TreeMap<String, ArrayList<Multimedia>> getListademedia() {
		return listademedia;
	}




	public void setListademedia(TreeMap<String, ArrayList<Multimedia>> listademedia) {
		this.listademedia = listademedia;
	}




	public void regis() {
		nick = textNick.getText();
		gmail = textGmail.getText();
		contraseña = textContraseña.getText();
	
		
	}
	
	public void crearUsu(){
		textGmail.setVisible(true);
		textNick.setVisible(true);
		textContraseña.setVisible(true);
		uno.setVisible(true);
		dos.setVisible(true);
		tres.setVisible(true);

	}
	
	public static void main(String[] args) {
		VentanaPrinc v = new VentanaPrinc();
		v.setVisible(true);
	}
	
	public void insertaFav() {
		favoritos = gestor.obtenerDatosFavoritos();
		for (Favorito f : favoritos) {
			mapaFav.putIfAbsent(f.getUsuario(), new ArrayList<Multimedia>());
			for (Cancion cancion : canciones) {
				if(cancion.getNombre().equals(f.getNombreCancion())) {
					mapaFav.get(f.getUsuario()).add(cancion);
				}
			}
		}
		System.out.println(favoritos);
		deustomusic.setListaFav(favoritos);
		deustomusic.setFavoritos(mapaFav);
	}
}