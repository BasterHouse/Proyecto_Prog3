package ventanas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import clases.Cancion;
import clases.Multimedia;
import clases.Podcast;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JList;
import javax.swing.JOptionPane;

import clave.DeustoMusic;


import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.Color;

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
	private Thread hilo;
	
	protected DefaultListModel<String> modeloArtistas;
	protected JList<String> listaArtistas;

	protected boolean cerrar = false;
	
	
	public VentanaDeustomusic(DeustoMusic deustomusic) {
		setTitle("DeustoMusic");
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		modeloCanciones = new DefaultListModel<Cancion>();
		
		for (Multimedia cancion : deustomusic.getListademedia().get("Canciones")) {
			if (cancion instanceof Cancion) {
				modeloCanciones.addElement((Cancion)cancion);
			}
		}
	
		listaCanciones = new JList(modeloCanciones);
		listaCanciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollCanciones = new JScrollPane(listaCanciones);
		scrollCanciones.setBounds(220, 30, 336, 113);
		getContentPane().add(scrollCanciones);
		JLabel titulomultimedias = new JLabel("Todas las canciones🎵");
        scrollCanciones.setColumnHeaderView(titulomultimedias);
        
       listaCanciones.addKeyListener(new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			deustomusic.getFavoritos().get(deustomusic.getUsuario().getId());

			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
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
		scrollPodcast.setBounds(220, 154, 334, 97);
		getContentPane().add(scrollPodcast);
		JLabel titulopodcast = new JLabel("Todos los Podcast🎤");
        scrollPodcast.setColumnHeaderView(titulopodcast);
        
        
		JLabel titulogeneral = new JLabel("DeustoMusic");
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
									} else if (colorG<0) {
										colorG = 0;
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
		buscar.setBounds(10, 77, 117, 38);
		getContentPane().add(buscar);

		
		JButton cerrarsesion = new JButton("Cerrar Sesión");
		cerrarsesion.setBounds(9, 215, 117, 35);
		getContentPane().add(cerrarsesion);
		
		JButton informacion = new JButton("Información");
		informacion.setBounds(9, 292, 117, 35);
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
		scrollArtistas.setBounds(10, 116, 180, 88);
		getContentPane().add(scrollArtistas);
		JLabel tituloArtistas = new JLabel("Busca el artista (doble click)");
        scrollArtistas.setColumnHeaderView(tituloArtistas);
        scrollArtistas.setVisible(false);
		
		buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scrollArtistas.setVisible(true);

				
			}
		});
		modeloBuscado = new DefaultListModel<Cancion>();
		listaBuscado = new JList(modeloBuscado);
		listaBuscado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollBuscado = new JScrollPane(listaBuscado);
		scrollBuscado.setBounds(220, 30, 336, 113);
		getContentPane().add(scrollBuscado);
		JLabel titulobuscado = new JLabel("Canciones de tu artista");
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
				      scrollCanciones.setVisible(false);
				      listaBuscado.setVisible(true);
				    }
				
			}
		});
		
		
		modeloFav = new DefaultListModel<Cancion>();
		
		for (Multimedia cancion : deustomusic.getFavoritos().get(deustomusic.getUsuario().getId())) {
			if (cancion instanceof Cancion) {
				modeloFav.addElement((Cancion)cancion);
			}
		}
		System.out.println(deustomusic.getFavoritos());
		System.out.println(deustomusic.getUsuario());
		System.out.println(deustomusic.getUsuario().getId());
		listaFav = new JList(modeloFav);
		listaFav.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollFav = new JScrollPane(listaFav);
        scrollFav.setBounds(220, 262, 336, 88);
        getContentPane().add(scrollFav);
        JLabel titulofav = new JLabel("Tus canciones favoritas❤");
        scrollFav.setColumnHeaderView(titulofav);
		

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
	
	public void cierra() {
		this.setVisible(false);
	}
}
