package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;


//TODO hay que arreglar cuando se toca "Archivo" o "Edicion"

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private static final String NOMBRE = "Herramienta Tesis";
	private JPanel contentPane;
	private JFileChooser seleccion; 
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		super(NOMBRE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 409);
		contentPane = new JPanel(new GridBagLayout());
		getContentPane().add(contentPane,BorderLayout.PAGE_START);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

//TODO arreglar lo de la barra de menu
		JMenuBar menuBar = new JMenuBar();	
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo); 
		
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir Base de Datos");
		mntmAbrir.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/abrirBaseDeDatos2.png")));
		mntmAbrir.addActionListener(new ActionListener() {
				//Abre el dialogo para abrir una BD
				@Override
				public void actionPerformed(ActionEvent e) {
					ManejadorArchivos manejadorArchivos = new ManejadorArchivos(VentanaPrincipal.this);
					manejadorArchivos.abrirArchivo();
				}	
		});
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmAbrirArchivo = new JMenuItem("Abrir Archivo");
		mntmAbrirArchivo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/abrir.png")));
		mnArchivo.add(mntmAbrirArchivo);
		
		JMenu mnEdicion = new JMenu("Edici\u00F3n");
		menuBar.add(mnEdicion);
		
		JMenuItem mntmCortar = new JMenuItem("Cortar");
		mntmCortar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		mnEdicion.add(mntmCortar);
		
		JMenuItem mntmCopiar = new JMenuItem("Copiar");
		mntmCopiar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		mnEdicion.add(mntmCopiar);
		
		JMenuItem mntmPegar = new JMenuItem("Pegar");
		mntmPegar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		mnEdicion.add(mntmPegar);
		
		JTabbedPane pestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(pestanias,BorderLayout.PAGE_START);
		
		@SuppressWarnings("unused")
		JPanel pestaniaInicio = new PestaniaInicio(pestanias);

	
	}
	
	
}