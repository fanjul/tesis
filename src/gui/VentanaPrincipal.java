package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 615, 21);
		contentPane.add(menuBar);
		
		JMenu mnPrueba = new JMenu("Archivo");
		menuBar.add(mnPrueba); 
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir Base de Datos");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO logica de abrir y cargar la base de datos
			}
		}); 
		mnPrueba.add(mntmAbrir);
		
		JTabbedPane pestanias = new JTabbedPane(JTabbedPane.TOP);
		pestanias.setBounds(0, 22, 652, 349);
		contentPane.add(pestanias);
		
		@SuppressWarnings("unused")
		JPanel pestaniaInicio = new PestaniaInicio(pestanias);
	
		
	}
	
	
}