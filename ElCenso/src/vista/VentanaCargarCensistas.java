package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import controlador.Controlador;
import instancias.Censista;
import instancias.Manzana;

public class VentanaCargarCensistas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelCenter;
	private JButton btnAgregarCensista, btnSiguienteCensista, btnAnteriorCensista, btnCrearCenso;
	private JLabel lblFotoCensita, lblNombreCensista, lblFotoFondo;
	private ArrayList<Censista> censistasSeleccionados;
	private Censista censista;
	private Integer recorrido, cantManzanas;

	public VentanaCargarCensistas(Integer cantManzanas) {
		initAll(cantManzanas);
		btnAgregarCensista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCensista();
			}
		});
		btnSiguienteCensista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recorrido++;
				actualizarBotones();
			}
		});
		btnAnteriorCensista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recorrido--;
				actualizarBotones();
			}
		});
		btnCrearCenso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearCenso();
			}
		});
	}

	private void crearCenso() {
		if (censistasSeleccionados.isEmpty())
			JOptionPane.showMessageDialog(null, "Debes Ingresar al menos un censista");
		else {
			Controlador.crearCenso(censistasSeleccionados, Controlador.manzanasDisponiblesPorCantidad(cantManzanas));
			VentanaInicio vcc = new VentanaInicio();
			vcc.setVisible(true);
			setVisible(false);
		}
	}

	private void agregarCensista() {
		censistasSeleccionados.add(censista);
	}

	private void actualizarBotones() {
		btnAnteriorCensista.setEnabled((recorrido == 0) ? false : true);
		btnSiguienteCensista.setEnabled((recorrido == Controlador.cantCensistas() - 1) ? false : true);
		cambiarDatos();
	}

	private void cambiarDatos() {
		censista = Controlador.getCensista(recorrido);
		lblNombreCensista.setText(censista.getNombre().toUpperCase());
		ImageIcon image = new ImageIcon(VentanaCargarCensistas.class.getResource(censista.getRutaImagen()));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblFotoCensita.getWidth(),
				lblFotoCensita.getHeight(), Image.SCALE_DEFAULT));
		lblFotoCensita.setIcon(icon);
	}

	private void initAll(Integer cantManzanas) {
		Controlador.leerLosJSON();
		this.censistasSeleccionados = new ArrayList<>();
		recorrido = 0;
		this.cantManzanas = cantManzanas;
		initFrame();
		initPanelCenter();
		initBtnAgregarCensista();
		initBtnSiguienteCensista();
		initBtnAnteriorCensista();
		initBtnCrearCenso();
		initLabelFotoCensista();
		initLabelNombreCensista();
		initLabelFotoFondo();
		cambiarDatos();
	}

	private void initFrame() {
		setTitle("Censistas");
		setBounds(100, 100, 860, 616);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private void initPanelCenter() {
		panelCenter = new JPanel();
		panelCenter.setForeground(Color.BLACK);
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
	}

	private void initBtnAgregarCensista() {
		btnAgregarCensista = new JButton("AGREGAR CENSISTA");
		btnAgregarCensista.setFont(new Font("Niagara Engraved", Font.PLAIN, 48));
		btnAgregarCensista.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnAgregarCensista.setForeground(Color.BLACK);
		btnAgregarCensista.setBackground(Color.BLACK);
		btnAgregarCensista.setBounds(155, 485, 530, 67);
		panelCenter.add(btnAgregarCensista);
	}

	private void initBtnSiguienteCensista() {
		btnSiguienteCensista = new JButton(">");
		btnSiguienteCensista.setFont(new Font("Niagara Engraved", Font.BOLD, 48));
		btnSiguienteCensista.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnSiguienteCensista.setForeground(Color.BLACK);
		btnSiguienteCensista.setBackground(new Color(0, 0, 0));
		btnSiguienteCensista.setBounds(723, 485, 94, 67);
		panelCenter.add(btnSiguienteCensista);
	}

	private void initBtnAnteriorCensista() {
		btnAnteriorCensista = new JButton("<");
		btnAnteriorCensista.setFont(new Font("Niagara Engraved", Font.BOLD, 48));
		btnAnteriorCensista.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnAnteriorCensista.setForeground(Color.BLACK);
		btnAnteriorCensista.setBackground(new Color(0, 0, 0));
		btnAnteriorCensista.setBounds(25, 485, 94, 67);
		btnAnteriorCensista.setEnabled(false);
		panelCenter.add(btnAnteriorCensista);
	}

	private void initBtnCrearCenso() {
		btnCrearCenso = new JButton("CREAR");
		btnCrearCenso.setForeground(Color.BLACK);
		btnCrearCenso.setFont(new Font("Niagara Engraved", Font.PLAIN, 28));
		btnCrearCenso.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnCrearCenso.setBackground(Color.BLACK);
		btnCrearCenso.setBounds(712, 39, 94, 67);
		panelCenter.add(btnCrearCenso);
	}

	private void initLabelFotoCensista() {
		lblFotoCensita = new JLabel();
		lblFotoCensita.setBounds(166, 51, 519, 416);
		panelCenter.add(lblFotoCensita);
	}

	private void initLabelFotoFondo() {
		lblFotoFondo = new JLabel();
		lblFotoFondo.setBounds(0, 0, 844, 577);
		ImageIcon image = new ImageIcon(VentanaCargarCensistas.class.getResource("/imagenes/fondoCensistas.jpg"));
		Icon icon = new ImageIcon(image.getImage().getScaledInstance(lblFotoFondo.getWidth(), lblFotoFondo.getHeight(),
				Image.SCALE_DEFAULT));
		lblFotoFondo.setIcon(icon);
		panelCenter.add(lblFotoFondo);
	}

	private void initLabelNombreCensista() {
		lblNombreCensista = new JLabel();
		lblNombreCensista.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNombreCensista.setBounds(166, 11, 519, 35);
		lblNombreCensista.setHorizontalAlignment(SwingConstants.CENTER);
		panelCenter.add(lblNombreCensista);
	}

}
