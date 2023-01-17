package ventanas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import clases.Cancion;
import clases.Favorito;
import clases.Genero;
import clases.Multimedia;
import clases.Podcast;

import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import clave.DeustoMusic;
import clave.GestorBBDD;


import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;

import static org.junit.Assert.assertFalse;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;

public class VentanaDeustomusic extends JFrame{
	protected DefaultListModel<Cancion> modeloCanciones;
	protected JList<Cancion> listaCanciones;
	protected DefaultListModel<Cancion> modeloBuscado;
	protected JList<Cancion> listaBuscado;
	protected DefaultListModel<Podcast> modeloPodcast;
	protected JList<Podcast> listaPodcast;
	protected DefaultListModel<Multimedia> modeloPlaylist;
	protected JList<Multimedia> listaPlaylist;
	protected DefaultListModel<Cancion> modeloFav;
	protected JList<Cancion> listaFav;
	protected DefaultListModel<List<Cancion>> modeloRec;
	protected JList<List<Cancion>> listaRec;
	GestorBBDD gestor = new GestorBBDD();
	
	protected DefaultListModel<String> modeloArtistas;
	protected JList<String> listaArtistas;

	protected boolean cerrar = false;
	private JTextField minutos;
	private JTextField segundos;
	private JTextField horas;

	
	
	public VentanaDeustomusic(DeustoMusic deustomusic) {
		setBackground(new Color(46, 46, 46));
		setTitle("DeustoMusic");
		setSize(753, 469);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(317, 30, 346, 370);
        getContentPane().add(panel);
        panel.setLayout(null);
        panel.setVisible(false);
        
        modeloCanciones = new DefaultListModel<Cancion>();
	
       List<Cancion> canciones = new ArrayList<Cancion>();
        
		for (Multimedia cancion : deustomusic.getListademedia().get("Canciones")) {
			if (cancion instanceof Cancion) {
				canciones.add((Cancion) cancion);
				modeloCanciones.addElement((Cancion)cancion);
			}
		}
	
		listaCanciones = new JList(modeloCanciones);
		listaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollCanciones = new JScrollPane(listaCanciones);
		scrollCanciones.setBounds(317, 30, 346, 122);
		getContentPane().add(scrollCanciones);
		JLabel titulomultimedias = new JLabel("Todas las canciones🎵 (enter para indicar favorita)");
        scrollCanciones.setColumnHeaderView(titulomultimedias);

		
		
       listaCanciones.addKeyListener(new KeyAdapter() {	
		@Override
		public void keyReleased(KeyEvent e) {
			boolean repetido = true;

			if(e.getKeyCode()==10) {
				Favorito nuevo = new Favorito(deustomusic.getUsuario(), listaCanciones.getSelectedValue().getNombre(), null);
				
				for (Favorito fav : deustomusic.getListaFav()) {
					if(fav.getNombreCancion().equals(nuevo.getNombreCancion()) && fav.getUsuario().getId() == nuevo.getUsuario().getId()) {
						repetido = true;
						System.err.println("Esta cancion ya esta en favoritas");
						break;
					}else {
						repetido = false;
					}
				}
				if(deustomusic.getListaFav().isEmpty()) {
					deustomusic.getListaFav().add(new Favorito(deustomusic.getUsuario(), listaCanciones.getSelectedValue().getNombre(), null));
					gestor.insertarDatosFav(deustomusic.getListaFav().toArray(new Favorito[deustomusic.getListaFav().size()]));
					modeloFav.addElement(listaCanciones.getSelectedValue());
				}
				if (repetido == false) {
				deustomusic.getListaFav().clear();
				deustomusic.getListaFav().add(new Favorito(deustomusic.getUsuario(), listaCanciones.getSelectedValue().getNombre(), null));
				gestor.insertarDatosFav(deustomusic.getListaFav().toArray(new Favorito[deustomusic.getListaFav().size()]));
				modeloFav.addElement(listaCanciones.getSelectedValue());
				}
			}
		}

	});
        
        
        
        modeloPodcast = new DefaultListModel<Podcast>();
		
		for (Multimedia podcast : deustomusic.getListademedia().get("Podcasts")) {
			if (podcast instanceof Podcast) {
				modeloPodcast.addElement((Podcast)podcast);
			}
		}
	
		listaPodcast = new JList(modeloPodcast);
		listaPodcast.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPodcast = new JScrollPane(listaPodcast);
		scrollPodcast.setBounds(317, 163, 346, 113);
		getContentPane().add(scrollPodcast);
		JLabel titulopodcast = new JLabel("Todos los Podcast🎤");
        scrollPodcast.setColumnHeaderView(titulopodcast);
        
        
		JLabel titulogeneral = new JLabel("DeustoMusic");
		titulogeneral.setHorizontalAlignment(SwingConstants.CENTER);
		titulogeneral.setFont(new Font("Stencil", Font.PLAIN, 16));
		titulogeneral.setBounds(10, 10, 194, 56);
		getContentPane().add(titulogeneral);
		
		addWindowListener(new WindowAdapter() {
			Thread hiloC = null;
			Thread hiloT = null;
			boolean pausa = false;
			int colorG = 0;
			int incColor = +5;
			int tamaño = 16;
			int incTam = +1;
			@Override
			public void windowOpened(WindowEvent e) {
				hiloC = new Thread() {
					@Override
					public void run() {
						while (hiloC!=null) {
							if (!pausa) {
									colorG += incColor;
									if (colorG>255) {
										colorG = 255;
										incColor = -5;
									} else if (colorG<75) {
										colorG = 75;
										incColor = +5;
									}
									titulogeneral.setForeground( new Color( 0, colorG, 0) );
								
							}
							try {
								Thread.sleep( 20 );
							} catch (InterruptedException e) {}
						}
					}
				};
				hiloC.start();
				
				
				hiloT = new Thread() {
					@Override
					public void run() {
						while (hiloT!=null) {
							if (!pausa) {
									tamaño += incTam;
									if (tamaño > 27) {
										tamaño = 27;
										incTam = -1;
									} else if (tamaño < 10) {
										tamaño = 10;
										incTam = +1;
									}
									titulogeneral.setFont(new Font("Stencil", Font.PLAIN, tamaño));
								
							}
							try {
								Thread.sleep( 100 );
							} catch (InterruptedException e) {}
						}
					}
				};
				hiloT.start();
				
			}
			@Override
			public void windowActivated(WindowEvent e) {
				pausa = false;
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				pausa = true;
			}
			@Override
			public void windowClosed(WindowEvent e) {
				hiloC = null;
				hiloT = null;
			}
		
		});
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(10, 70, 143, 49);
		getContentPane().add(buscar);

		
		JButton cerrarsesion = new JButton("Cerrar Sesión");
		cerrarsesion.setBounds(10, 294, 143, 61);
		getContentPane().add(cerrarsesion);
		
		JButton informacion = new JButton("Información");
		informacion.setBounds(10, 366, 143, 56);
		getContentPane().add(informacion);


		modeloArtistas = new DefaultListModel<String>();
		
		for (Multimedia cancion : deustomusic.getListademedia().get("Canciones")) {
			if(modeloArtistas.contains(cancion.getArtista())) {
			} else {
				modeloArtistas.addElement(cancion.getArtista());
			}
				
				
		}
	
		listaArtistas = new JList(modeloArtistas);
		listaArtistas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollArtistas = new JScrollPane(listaArtistas);
		scrollArtistas.setBounds(10, 119, 180, 97);
		getContentPane().add(scrollArtistas);
		JLabel tituloArtistas = new JLabel("Busca el artista (doble click)");
        scrollArtistas.setColumnHeaderView(tituloArtistas);
        scrollArtistas.setVisible(false);
		
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(scrollArtistas.isVisible()) {
					scrollArtistas.setVisible(false);
				} else {
					scrollArtistas.setVisible(true);
				}
				if(listaBuscado.isVisible()) {
					listaBuscado.setVisible(false);
					scrollCanciones.setVisible(true);
				}
				
			}
		});
		modeloBuscado = new DefaultListModel<Cancion>();
		listaBuscado = new JList(modeloBuscado);
		listaBuscado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollBuscado = new JScrollPane(listaBuscado);
		scrollBuscado.setBounds(317, 30, 346, 113);
		getContentPane().add(scrollBuscado);
		JLabel titulobuscado = new JLabel("Canciones de tu artista (enter para indicar favorita)");
        scrollBuscado.setColumnHeaderView(titulobuscado);		
			
		listaArtistas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String artista;
				if (e.getClickCount() == 2) {
						modeloBuscado.removeAllElements();
				      artista = listaArtistas.getSelectedValue();
				      for (Multimedia cancion : deustomusic.getListademedia().get("Canciones")) {
							if (cancion instanceof Cancion) {
								if (cancion.getArtista().equals(artista)) {
									modeloBuscado.addElement((Cancion)cancion);
																
								}
							}
						}
				      
				      if(panel.isVisible()) {
							panel.setVisible(false);
						}
				      scrollCanciones.setVisible(false);
				      listaBuscado.setVisible(true);
				    }
				
			}
		});
		
		listaBuscado.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyReleased(KeyEvent e) {
				boolean repetido = true;

				if(e.getKeyCode()==10) {
					Favorito nuevo = new Favorito(deustomusic.getUsuario(), listaBuscado.getSelectedValue().getNombre(), null);
					
					for (Favorito fav : deustomusic.getListaFav()) {
						if(fav.getNombreCancion().equals(nuevo.getNombreCancion()) && fav.getUsuario().getId() == nuevo.getUsuario().getId()) {
							repetido = true;
							System.err.println("Esta cancion ya esta en favoritas");
							break;
						}else {
							repetido = false;
						}
					}
					if(deustomusic.getListaFav().isEmpty()) {
						deustomusic.getListaFav().add(new Favorito(deustomusic.getUsuario(), listaBuscado.getSelectedValue().getNombre(), null));
						gestor.insertarDatosFav(deustomusic.getListaFav().toArray(new Favorito[deustomusic.getListaFav().size()]));
						modeloFav.addElement(listaBuscado.getSelectedValue());
					}
					if (repetido == false) {
					deustomusic.getListaFav().clear();
					deustomusic.getListaFav().add(new Favorito(deustomusic.getUsuario(), listaBuscado.getSelectedValue().getNombre(), null));
					gestor.insertarDatosFav(deustomusic.getListaFav().toArray(new Favorito[deustomusic.getListaFav().size()]));
					modeloFav.addElement(listaBuscado.getSelectedValue());
					}
				}
			}

		});
		
		modeloFav = new DefaultListModel<Cancion>();
		if(deustomusic.getFavoritos().get(deustomusic.getUsuario()) != null) {
			for (Multimedia cancion : deustomusic.getFavoritos().get(deustomusic.getUsuario())) {
				if (cancion instanceof Cancion) {
					modeloFav.addElement((Cancion)cancion);
				}
			}
		} else {
			System.err.println("Este usuario no tiene canciones favoritas");
		}
		listaFav = new JList(modeloFav);
		listaFav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollFav = new JScrollPane(listaFav);
        scrollFav.setBounds(317, 287, 346, 113);
        getContentPane().add(scrollFav);
        JLabel titulofav = new JLabel("Tus canciones favoritas❤");
        scrollFav.setColumnHeaderView(titulofav);
        
        
        modeloRec = new DefaultListModel<List<Cancion>>();
        listaRec = new JList(modeloRec);
        JScrollPane scrollRec = new JScrollPane(listaRec);
        scrollRec.setBounds(0, 132, 346, 238);
        panel.add(scrollRec);
        
        minutos = new JTextField();
        minutos.setBounds(116, 66, 73, 20);
        panel.add(minutos);
        minutos.setColumns(10);
        
        segundos = new JTextField();
        segundos.setBounds(226, 66, 64, 20);
        panel.add(segundos);
        segundos.setColumns(10);
        
        horas = new JTextField();
        horas.setBounds(10, 66, 73, 20);
        panel.add(horas);
        horas.setColumns(10);
        
        JLabel titRecursivo = new JLabel("<html>Indica el tiempo que tienes en tu viaje y ve las posibles <br/> combinaciones  de tus canciones favoritas que puedes escuchar</html>");
        titRecursivo.setHorizontalAlignment(SwingConstants.CENTER);
        titRecursivo.setBackground(new Color(192, 192, 192));
        titRecursivo.setBounds(0, 0, 346, 55);
        panel.add(titRecursivo);
        
        JLabel lblH = new JLabel("h");
        lblH.setBounds(93, 70, 31, 14);
        panel.add(lblH);
        
        JLabel lblM = new JLabel("m");
        lblM.setBounds(199, 70, 46, 14);
        panel.add(lblM);
        
        JLabel lblS = new JLabel("s");
        lblS.setBounds(300, 70, 46, 14);
        panel.add(lblS);
        
        JButton combinar = new JButton("Ver Combinaciones");
        combinar.setBounds(92, 98, 168, 23);
        panel.add(combinar);
        
        
        JButton recursivo = new JButton("Planea tu viaje!");
        recursivo.setBounds(10, 227, 143, 56);
        getContentPane().add(recursivo);
        
        recursivo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(panel.isVisible()) {
					scrollCanciones.setVisible(true);
					panel.setVisible(false);
				} else {
					scrollCanciones.setVisible(false);
					panel.setVisible(true);
				}
				
			}
		});

		combinar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Cancion> listaRec = new ArrayList<>();
				int tiempo = 0;
				if (horas.getText().equals("")) {
					horas.setText("0");
				}
				if (minutos.getText().equals("")) {
					minutos.setText("0");
				}
				if (segundos.getText().equals("")) {
					segundos.setText("0");
				}
				
				tiempo = Integer.parseInt(horas.getText())*3600 + Integer.parseInt(minutos.getText())*60 + Integer.parseInt(segundos.getText());
				
				for (Multimedia c : deustomusic.getFavoritos().get(deustomusic.getUsuario())) {
					if (c instanceof Cancion) {
						listaRec.add((Cancion)c);
					}
				}
				if(tiempo == 0) {
					System.out.println("Inserta valores adecuados en el tiempo");
				} else {
					recursividad(listaRec, tiempo);
				}
				
			}
		});

		cerrarsesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cierra();
			}
		});

		informacion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(), "DeustoMusic es un programa de gestión de multimedia de audio, \n en la que puedes seleccionar tanto canciones como podcast. \n Con el botón de 'Buscar' puedes hacer doble click encima del \n nombre de un artista parq que se muestren sus canciones.\n Además con el botón de cerrar sesión podrás volver a la ventana \n de inicio de sesión para crear o cambiar de cuenta. \n \n Creado por: Unai Basterretxea |\t Aimar Etxaniz \n Jon Ander Olivera |\t Alejandro Elvira", "Informacion",  JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		
	}

		 private static void combinacionesR(List<List<Cancion>> result, List<Cancion> elementos, int duracion, int sobrantemax,  List<Cancion> temp) {
			 Comparator<Cancion> compDuracion = (s1, s2) -> {
					return Integer.compare(s1.getDuracion(), s2.getDuracion());
				};
			 if (duracion < 0) {
		        	return ;
		        }else if (duracion < sobrantemax && duracion >= 0){
					Collections.sort(temp, compDuracion);
		        	if (!result.contains(temp)){
		        		result.add(new ArrayList<>(temp));
		        	}
		        	
		        	
		        } else {
		            for(Cancion f : elementos) {
		            	if (!temp.contains(f)) {
		            		temp.add(f);
			        		combinacionesR(result, elementos, duracion-f.getDuracion(),sobrantemax, temp);
			        		temp.remove(temp.size()-1);
			        	
			        		
							
							}
							
						}
		        	}
		        }
		    
		    


	
	public static List<List<Cancion>> combinaciones(List<Cancion> elementos, int duracion, int sobrantemax) {

		
    	
		List<List<Cancion>> result = new ArrayList<>();
		combinacionesR(result, elementos, duracion,sobrantemax, new ArrayList<>());
    	return result;
    }
	
	
	public void recursividad(List<Cancion> elementos, int duracion) {
		int sobrantemax;
    	sobrantemax = (int)(duracion * 0.3);
		
    	List<List<Cancion>> result = combinaciones(elementos, duracion , sobrantemax);
    	System.out.println(String.format("Combinaciones de aproximadamente "+  duracion +" segundos"));
    	result.forEach(r -> modeloRec.addElement(r));
  
	}
	
	public void cierra() {
		dispose();
	}
}


