package main;

import gui.PestaniaInicio;
import gui.VentanaPrincipal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = VentanaPrincipal.getInstance();

					VentanaPrincipal.getInstance().getContentPane().add(VentanaPrincipal.getInstance().getPestanias(), BorderLayout.PAGE_START);
					VentanaPrincipal.getInstance().addPestania(new PestaniaInicio());
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setMinimumSize(frame.getSize());
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
