package vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCargarManzanas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnSiguiente;
	private JPanel panelCentral;
	private JLabel lblFondo, lblTitulo;
	private JTextField txtCantidad;

	public VentanaCargarManzanas() {
		initAll();
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarInputCantidad()) {
					cargarVentanaCensistas();
				}
			}
		});
	}

	public boolean validarInputCantidad() {
		String cadena = txtCantidad.getText();
		boolean esNumero = ((cadena.matches("[+-]?\\d*(\\.\\d+)?")) && !cadena.isEmpty());
		if (!esNumero)
			JOptionPane.showMessageDialog(null, "No ha ingresado una cantidad de manzanas valida");
		return esNumero;
	}

	private void cargarVentanaCensistas() {
		Integer cantidadManzanas = Integer.valueOf(txtCantidad.getText());
		VentanaCargarCensistas vcc = new VentanaCargarCensistas(cantidadManzanas);
		vcc.setVisible(true);
		setVisible(false);
		setEnabled(false);
	}

	private void initAll() {
		initFrame();
		initPanelCentral();
		initTxtCantidad();
		initBtnSiguiente();
		initLblTitulo();
		iniciarLblFondo();
	}

	private void initFrame() {
		setBounds(0, 0, 472, 228);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("El Censo");
		getContentPane().setLayout(new BorderLayout(0, 0));
	}

	private void initPanelCentral() {
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelCentral);
		panelCentral.setLayout(null);
	}

	private void initTxtCantidad() {
		txtCantidad = new JTextField();
		txtCantidad.setBounds(139, 48, 151, 43);
		panelCentral.add(txtCantidad);
		txtCantidad.setColumns(10);
	}

	private void initBtnSiguiente() {
		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setBounds(139, 102, 151, 43);
		panelCentral.add(btnSiguiente);
	}

	private void initLblTitulo() {
		lblTitulo = new JLabel("Escriba la cantidad de manzanas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(54, 23, 317, 14);
		panelCentral.add(lblTitulo);
	}

	private void iniciarLblFondo() {
		lblFondo = new JLabel();
		lblFondo.setIcon(new ImageIcon(VentanaCargarManzanas.class.getResource("/imagenes/fondoCargarManzana.jpg")));
		lblFondo.setBounds(0, 0, 456, 189);
		panelCentral.add(lblFondo);
	}
}
