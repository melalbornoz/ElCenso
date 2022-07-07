package vista;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import algoritmo.Solucion;
import controlador.Controlador;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class VentanaInicio extends JFrame {
	private static final long serialVersionUID = 8564542891588183742L;
	private JPanel panelPrincipal;
	private JButton btnCrearCenso, btnFuerzaBruta, btnGoloso;
	private JLabel lblFondoInicio;

	public VentanaInicio() {
		initAll();
		
		btnCrearCenso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaCargarManzanas vcc = new VentanaCargarManzanas();
				vcc.setVisible(true);
				setVisible(false);
			}
		});
		
		btnFuerzaBruta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.censoCreado())
					conFuerzaBruta();
				else
					JOptionPane.showMessageDialog(null, "Primero debe crear el Censo");
			}
		});
		
		btnGoloso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controlador.censoCreado())
					seleccionCriterioGoloso();
				else
					JOptionPane.showMessageDialog(null, "Primero debe crear el Censo");

			}
		});
		
	}

	private void initAll() {
		initFrame();
		iniciarPanel();
		iniciarBotones();
		iniciarLabel();
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 689);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void iniciarPanel() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
	}

	private void iniciarBotones() {
		btnCrearCenso = new JButton("CREAR CENSO");
		btnCrearCenso.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnCrearCenso.setFont(new Font("Niagara Solid", Font.PLAIN, 48));
		btnCrearCenso.setForeground(new Color(0, 0, 0));
		btnCrearCenso.setBackground(new Color(192, 192, 192));
		btnCrearCenso.setBounds(148, 124, 407, 88);
		panelPrincipal.add(btnCrearCenso);

		btnFuerzaBruta = new JButton("ASIGNAR CON FUERZA BRUTA");
		btnFuerzaBruta.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnFuerzaBruta.setFont(new Font("Niagara Solid", Font.PLAIN, 48));
		btnFuerzaBruta.setForeground(new Color(0, 0, 0));
		btnFuerzaBruta.setBackground(new Color(192, 192, 192));
		btnFuerzaBruta.setBounds(148, 272, 407, 88);
		panelPrincipal.add(btnFuerzaBruta);

		btnGoloso = new JButton("ASIGNAR CON HEURISTICA GOLOSA");
		btnGoloso.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnGoloso.setFont(new Font("Niagara Solid", Font.PLAIN, 48));
		btnGoloso.setForeground(new Color(0, 0, 0));
		btnGoloso.setBackground(new Color(192, 192, 192));
		btnGoloso.setBounds(148, 432, 407, 88);
		panelPrincipal.add(btnGoloso);
	}

	private void iniciarLabel() {
		lblFondoInicio = new JLabel();
		lblFondoInicio.setIcon(new ImageIcon(VentanaInicio.class.getResource("/imagenes/fondoInicio.jpg")));
		lblFondoInicio.setBounds(0, 0, 671, 652);
		panelPrincipal.add(lblFondoInicio);
	}

	private void conFuerzaBruta() {
		try {
			Solucion solucion = Controlador.asignarCensistasManzanasFB();
			setVisible(false);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(solucion, "\nFuerza Bruta");
			ventanaPrincipal.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Los censistas no fueron suficientes para "
					+ "asignar a todas las manzanas\n" + "Por favor vuelve a crear el censo");
		}
	}

	private void seleccionCriterioGoloso() {
		int seleccion = JOptionPane.showOptionDialog(null, "Seleccione opcion", "Selector de opciones",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "orden manzanas con mas vecinas", "orden manzanas con menos vecinas" }, "opcion 1");

		if (seleccion == 0)
			criterioGolosoConMasManzanas();
		if (seleccion == 1)
			criterioGolosoConMenosManzanas();
	}

	private void criterioGolosoConMasManzanas() {
		try {
			Solucion solucion = Controlador.asignarConCriterioMasManzanas();
			setVisible(false);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(solucion, "\ncriterio mas manzanas");
			ventanaPrincipal.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Los censistas no fueron suficientes para "
					+ "asignar a todas las manzanas\n" + "Por favor vuelve a crear el censo");
		}
	}

	private void criterioGolosoConMenosManzanas() {
		try {
			Solucion solucion = Controlador.asignarConCriterioMenosManzanas();
			setVisible(false);
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(solucion, "\ncriterio menos manzanas");
			ventanaPrincipal.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Los censistas no fueron suficientes para "
					+ "asignar a todas las manzanas\n" + "Por favor vuelve a crear el censo");
		}
	}
}
