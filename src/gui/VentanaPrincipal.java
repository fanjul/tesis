package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private static final String NOMBRE = "Herramienta Tesis";
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		super(NOMBRE);
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
			public void actionPerformed(ActionEvent arg0) {
				//TODO logica de abrir y cargar la base de datos
			}
		}); 
		mnArchivo.add(mntmAbrir);
		
		JTabbedPane pestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(pestanias,BorderLayout.PAGE_START);
		
		@SuppressWarnings("unused")
		JPanel pestaniaInicio = new PestaniaInicio(pestanias);

	
	}
	
	
}