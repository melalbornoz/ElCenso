package vista;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {

		try {
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {

					VentanaInicio ventanaInicio = new VentanaInicio();
					ventanaInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		});
	}
}
