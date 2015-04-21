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

	
		JMenuBar menuBar = new JMenuBar();	
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo); 
		
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir Base de Datos");
		mntmAbrir.addActionListener(new ActionListener() {
				//Abre el dialogo para abrir una BD
				@Override
				public void actionPerformed(ActionEvent e) {
					ManejadorArchivos manejadorArchivos = new ManejadorArchivos(VentanaPrincipal.this);
					manejadorArchivos.abrirArchivo();
				}	
		});
		mnArchivo.add(mntmAbrir);
		
		JTabbedPane pestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(pestanias,BorderLayout.PAGE_START);
		
		@SuppressWarnings("unused")
		JPanel pestaniaInicio = new PestaniaInicio(pestanias);

	
	}
	
	
}