package ventanas;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import clases.Cancion;
import clases.Multimedia;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import clave.DeustoMusic;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class VentanaDeustomusic extends JFrame{
	public VentanaDeustomusic(DeustoMusic deustomusic) {
		setTitle("DeustoMusic");
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		JScrollPane multimedias = new JScrollPane();
		multimedias.setBounds(167, 18, 269, 86);
		getContentPane().add(multimedias);
		
		JLabel titulomultimedias = new JLabel("Todo el contenido🎵");
		multimedias.setColumnHeaderView(titulomultimedias);
		
		JScrollPane favoritas = new JScrollPane();
		favoritas.setBounds(167, 134, 269, 86);
		getContentPane().add(favoritas);
		
		JLabel titulofavoritas = new JLabel("Favoritas❤");
		favoritas.setColumnHeaderView(titulofavoritas);
		
		JScrollPane playlists = new JScrollPane();
		playlists.setBounds(167, 251, 269, 86);
		getContentPane().add(playlists);
		
		JLabel tituloplaylists = new JLabel("Tus Playlists📃");
		playlists.setColumnHeaderView(tituloplaylists);
		
		JLabel titulogeneral = new JLabel("DeustoMusic");
		titulogeneral.setFont(new Font("Stencil", Font.PLAIN, 16));
		titulogeneral.setBounds(10, 11, 147, 38);
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

		JList list = new JList();
		list.setBounds(167, 270, 229, 41);
		DefaultListModel<Multimedia> modelo = new DefaultListModel<>();
		for (Multimedia c: deustomusic.getListademedia().get("Canciones")) {
			modelo.addElement(c);
		}
		list.setModel(modelo);
		getContentPane().add(list);
		
		
	}
}
