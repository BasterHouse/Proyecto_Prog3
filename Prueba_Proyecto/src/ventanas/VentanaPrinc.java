package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaPrinc extends JFrame{
	public VentanaPrinc() {
		getContentPane().setLayout(null);
		
		JLabel titulo = new JLabel("DeustoMusic");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Stencil", Font.PLAIN, 20));
		titulo.setBounds(0, 27, 434, 61);
		getContentPane().add(titulo);
		
		JButton iniciarsesion = new JButton("Iniciar Sesion");
		iniciarsesion.setBounds(76, 125, 105, 23);
		getContentPane().add(iniciarsesion);
		
		JButton crearcuenta = new JButton("Crear Cuenta");
		crearcuenta.setBounds(268, 125, 105, 23);
		getContentPane().add(crearcuenta);

	}

}
