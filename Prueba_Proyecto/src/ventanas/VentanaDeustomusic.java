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
import javax.swing.JList;
import clave.DeustoMusic;


import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class VentanaDeustomusic extends JFrame{
	protected DefaultListModel<Cancion> modeloCanciones;
	protected JList<Cancion> listaCanciones;
	protected DefaultListModel<Podcast> modeloPodcast;
	protected JList<Podcast> listaPodcast;
	protected DefaultListModel<Multimedia> modeloPlaylist;
	protected JList<Multimedia> listaPlaylist;
	
	
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
		scrollCanciones.setBounds(200, 38, 336, 97);
		getContentPane().add(scrollCanciones);
		JLabel titulomultimedias = new JLabel("Todas las canciones🎵");
        scrollCanciones.setColumnHeaderView(titulomultimedias);
        
        modeloPodcast = new DefaultListModel<Podcast>();
		
		for (Multimedia podcast : deustomusic.getListademedia().get("Podcasts")) {
			if (podcast instanceof Podcast) {
				modeloPodcast.addElement((Podcast)podcast);
			}
		}
	
		listaPodcast = new JList(modeloPodcast);
		listaPodcast.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPodcast = new JScrollPane(listaPodcast);
		scrollPodcast.setBounds(200, 163, 334, 86);
		getContentPane().add(scrollPodcast);
		JLabel titulopodcast = new JLabel("Todas los Podcast🎵");
        scrollPodcast.setColumnHeaderView(titulopodcast);
        
        
        /*modeloPlaylist = new DefaultListModel<Multimedia>();
		for (Multimedia m : v.getPlaylist().get()) {
				modeloPlaylist.addElement(m);
		}
	
		listaPlaylist = new JList(modeloPlaylist);
		listaPlaylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPlaylist = new JScrollPane();
		scrollPlaylist.setBounds(199, 271, 337, 79);
		getContentPane().add(scrollPlaylist);*/
        
        
		JLabel titulogeneral = new JLabel("DeustoMusic");
		titulogeneral.setFont(new Font("Stencil", Font.PLAIN, 16));
		titulogeneral.setBounds(10, 10, 147, 38);
		getContentPane().add(titulogeneral);
		
		JButton buscar = new JButton("Buscar");
		buscar.setBounds(10, 77, 117, 38);
		getContentPane().add(buscar);
		
		JButton perfil = new JButton("Perfil");
		perfil.setBounds(10, 137, 117, 35);
		getContentPane().add(perfil);
		
		JButton cerrarsesion = new JButton("Cerrar Sesión");
		cerrarsesion.setBounds(10, 203, 117, 35);
		getContentPane().add(cerrarsesion);
		
		JButton informacion = new JButton("Información");
		informacion.setBounds(10, 271, 117, 35);
		getContentPane().add(informacion);
		
		
		
		
		
		
		
	}
}
