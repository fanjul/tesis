package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static VentanaPrincipal instance = null;
	private static final String NOMBRE = "Herramienta Tesis";

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmAbrirBaseDeDatos;
	private JMenuItem mntmAbrirArchivo;
	private JMenu mnArchivo;
	private JTabbedPane pestanias;

	public static VentanaPrincipal getInstance() {
		if (instance == null) {
			instance = new VentanaPrincipal();
		}
		return instance;
	}

	private VentanaPrincipal() {
		super(NOMBRE);
		inicializarVariables();
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				VentanaPrincipal.class
						.getResource("/Imagenes/IconoHerramienta.jpg")));
		setAlwaysOnTop(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 409);

		getContentPane().add(contentPane, BorderLayout.PAGE_START);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(pestanias, BorderLayout.PAGE_START);

		{// Menu Archivo
			setJMenuBar(menuBar);
			menuBar.add(mnArchivo);

			// abrir BD
			mntmAbrirBaseDeDatos.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/Imagenes/abrirBaseDeDatos2.png")));
			mnArchivo.add(mntmAbrirBaseDeDatos);

			// abrir archivo txt/java
			mntmAbrirArchivo.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/Imagenes/abrirArchivo.png")));
			mnArchivo.add(mntmAbrirArchivo);

		}

	}

	private void inicializarVariables() {
		mntmAbrirBaseDeDatos = new JMenuItem("Abrir Base de Datos");
		mntmAbrirArchivo = new JMenuItem("Abrir Archivo");
		mnArchivo = new JMenu("Archivo");
		menuBar = new JMenuBar();
		contentPane = new JPanel(new GridBagLayout());
		pestanias = new JTabbedPane(JTabbedPane.TOP);
	}

	public JMenuItem getMntmAbrirBaseDeDatos() {
		return mntmAbrirBaseDeDatos;
	}

	public JMenuItem getMntmAbrirArchivo() {
		return mntmAbrirArchivo;
	}


	public JTabbedPane getPestanias() {
		return pestanias;
	}

	public void setEnableAbrirArchivo(boolean disponible) {
		this.mntmAbrirArchivo.setEnabled(disponible);
	}

	public void setEnableAbrirBaseDeDatos(boolean disponible) {
		this.mntmAbrirBaseDeDatos.setEnabled(disponible);
	}
	
	public void addPestania(Pestania p) {
		this.pestanias.addTab(p.getNombre(), null, p, p.getTooltip());
	}
}
