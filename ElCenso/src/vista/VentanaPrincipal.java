package vista;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import algoritmo.Solucion;
import controlador.Controlador;
import grafo.Arista;
import instancias.Manzana;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 8564542891588183742L;
	private JPanel panelPrincipal, panelIzquierdo, panelDerecho, panelAlgoritmo;
	private JTextArea textAsignados;
	private MapaCenso mapa;
	private JLabel lblFondoMapa, lblFondoText;
	private JMenuBar mb;
	private JMenu menuOpciones;
	private JMenuItem itemVolver;
	
	//variables solucion
	private Solucion solucion;
	private String tipoSolucion;
	private JLabel lblInfoAsignacion;
	private JButton btnAsignados, btnNoAsignados;


	public VentanaPrincipal(Solucion solucion, String tipoSolucion) {
		this.solucion = solucion;
		this.tipoSolucion = tipoSolucion;

		initAll();
		
		btnAsignados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInfoCensistasAsignados();
			}
		});
		btnNoAsignados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarInfoCensistasNoAsignados();
			}
		});
		
	}

	private void initAll() {
		initFrame();
		iniciarPaneles();
		iniciarMapa();
		iniciarText();
		iniciarBotones();
		iniciarLabels();
		iniciarmenu();
	}
	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 637);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	private void iniciarPaneles() {
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		panelIzquierdo = new JPanel();
		panelIzquierdo.setBounds(0, -12, 764, 603);
		panelPrincipal.add(panelIzquierdo);
		panelIzquierdo.setLayout(null);

		panelDerecho = new JPanel();
		panelDerecho.setBounds(763, 0, 440, 612);
		panelPrincipal.add(panelDerecho);
		panelDerecho.setLayout(null);

		panelAlgoritmo = new JPanel();
		panelAlgoritmo.setBounds(29, 79, 373, 392);
		panelDerecho.add(panelAlgoritmo);
		panelAlgoritmo.setLayout(null);
	}
	
	private void iniciarBotones() {		
		btnAsignados = new JButton("VER ASIGNADOS");
		btnAsignados.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnAsignados.setFont(new Font("Niagara Solid", Font.PLAIN, 23));
		btnAsignados.setForeground(new Color(0, 0, 0));
		btnAsignados.setBackground(new Color(192, 192, 192));
		btnAsignados.setBounds(29, 519, 129, 40);
		panelDerecho.add(btnAsignados);
		
		btnNoAsignados = new JButton("VER NO ASIGNADOS");
		btnNoAsignados.setBorder(UIManager.getBorder("InternalFrame.border"));
		btnNoAsignados.setFont(new Font("Niagara Solid", Font.PLAIN, 23));
		btnNoAsignados.setForeground(new Color(0, 0, 0));
		btnNoAsignados.setBackground(new Color(192, 192, 192));
		btnNoAsignados.setBounds(273, 519, 129, 40);
		panelDerecho.add(btnNoAsignados);
	}

	private void iniciarText() {
		textAsignados = new JTextArea();
		textAsignados.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 17));
		textAsignados.setLineWrap(true);
		textAsignados.setRows(800);
		JScrollPane scrollPane_1 = new JScrollPane(textAsignados);
		scrollPane_1.setBounds(0, 0, 373, 418);
		panelAlgoritmo.add(scrollPane_1);
	}

	private void iniciarLabels() {
		
		lblInfoAsignacion = new JLabel("asignaciones con\n " + tipoSolucion);
		lblInfoAsignacion.setForeground(new Color(0, 0, 0));
		lblInfoAsignacion.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
		lblInfoAsignacion.setBounds(10, 0, 411, 76);
		panelDerecho.add(lblInfoAsignacion);
		
		lblFondoMapa = new JLabel();
		lblFondoMapa.setIcon(new ImageIcon(VentanaPrincipal.class
				.getResource("/imagenes/fondoMapa.jpg")));
		lblFondoMapa.setBounds(-10, 0, 773, 613);
		panelIzquierdo.add(lblFondoMapa);

		lblFondoText = new JLabel();
		lblFondoText.setIcon(
				new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/fondoTextoAlgoritmo.jpg")));
		lblFondoText.setBounds(0, 0, 443, 607);
		panelDerecho.add(lblFondoText);
	
	}
	
	private void iniciarmenu() {
		mb = new JMenuBar();
		setJMenuBar(mb);

		menuOpciones = new JMenu("Opciones");
		mb.add(menuOpciones);

		itemVolver = new JMenuItem("Volver al inicio");
		itemVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio vi = new VentanaInicio();
				vi.setVisible(true);
				setVisible(false);
			}
		});
		menuOpciones.add(itemVolver);
	}

	private void iniciarMapa() {
		mapa = new MapaCenso();
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setBounds(34, 41, 701, 512);
		mapa.setBackground(Color.BLACK);
		mapa.setDisplayPosition(new Coordinate(-41.147245, -71.304049), 17);
		panelIzquierdo.add(mapa);

		try {
			cargarManzanasEnElMapa();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	private void cargarManzanasEnElMapa() throws CloneNotSupportedException {
		List<Manzana> manzanas = Controlador.getManzanasRadioCensal();
		mapa.guardarManzanas(manzanas);
		mapa.cargarUbicacionesMapa();

		List<Arista> cuadras = Controlador.getCuadras();
		try {
			mapa.cargarCuadrasCompartidas(cuadras);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void mostrarInfoCensistasNoAsignados() {
		textAsignados.setText(Controlador.consultarCensistasNoAsignados(solucion));
	}
	
	private void mostrarInfoCensistasAsignados() {
		textAsignados.setText(Controlador.consultarCensistasAsignado(solucion));
	}

}
