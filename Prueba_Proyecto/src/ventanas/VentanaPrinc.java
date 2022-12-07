package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class VentanaPrinc extends JFrame{
	private JTextField textNick;
	private JTextField textGmail;
	private JTextField textContraseña;
	private JButton registrar = new JButton("Registrar");
	private JLabel uno = new JLabel("Nick");
	private JLabel dos = new JLabel("Gmail");
	private JLabel tres = new JLabel("Contraseña");
	private String nick;
	private String gmail;
	private String contraseña;
	
	

	public VentanaPrinc() {
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
		iniciarsesion.setBounds(86, 79, 105, 23);
		getContentPane().add(iniciarsesion);
		
		JButton crearusuario = new JButton("Crear Usuario");
		crearusuario.setBounds(331, 79, 105, 23);
		getContentPane().add(crearusuario);
		
		textNick = new JTextField();
		textNick.setBounds(296, 143, 120, 39);
		getContentPane().add(textNick);
		
	

		textGmail= new JTextField();
		textGmail.setBounds(296, 210, 120, 39);
		getContentPane().add(textGmail);
		
		
		textContraseña = new JTextField();
		textContraseña.setBounds(296, 278, 120, 39);
		getContentPane().add(textContraseña);
		textContraseña.setColumns(10);
		
		registrar.setBounds(460, 284, 117, 29);
		getContentPane().add(registrar);
		
		uno.setBounds(296, 131, 61, 16);
		getContentPane().add(uno);
		
		
		dos.setBounds(296, 194, 61, 16);
		getContentPane().add(dos);
		
		
		tres.setBounds(296, 261, 61, 16);
		getContentPane().add(tres);
		

		textGmail.setVisible(false);
		textNick.setVisible(false);
		textContraseña.setVisible(false);
		registrar.setVisible(false);
		uno.setVisible(false);
		dos.setVisible(false);
		tres.setVisible(false);
		
		
		crearusuario.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			crearUsu();
			
		}
	});
		
		registrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
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
		registrar.setVisible(true);
		uno.setVisible(true);
		dos.setVisible(true);
		tres.setVisible(true);

	}
}
