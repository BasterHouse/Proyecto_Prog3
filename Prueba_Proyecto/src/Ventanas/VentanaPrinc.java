package Ventanas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class VentanaPrinc extends JFrame{
	
	private JButton bBuscar = new JButton( "Buscar" );
	private JButton bPerfil = new JButton( "Perfil" );
	private JButton bCerrarSesion = new JButton( "Cerrar Sesión" );
	private JButton bInfo = new JButton( "Información" );


	public VentanaPrinc() {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 900, 600 );
		setTitle( "Ventana de DeustoMusic" );
		JSplitPane pOeste = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		
		JPanel pTitulo = new JPanel( new BorderLayout() );
		pTitulo.add( new JLabel( "DesutoMusic" ));
		pOeste.setTopComponent( pTitulo );
		
		JPanel pBuscar = new JPanel( new BorderLayout() );
		pOeste.add( pBuscar );
		pBuscar.add(bBuscar);
		
		JPanel pPerfil = new JPanel( new BorderLayout() );
		pOeste.add( pPerfil );
		pPerfil.add(bPerfil);
		
		JPanel pSesion = new JPanel( new BorderLayout() );
		pOeste.add( pSesion );
		pSesion.add(bCerrarSesion);
		
		JPanel pInFo = new JPanel( new BorderLayout() );
		pOeste.add( pInFo );
		pInFo.add(bInfo);
		
		getContentPane().add( pOeste, BorderLayout.WEST ); 
	}

}
