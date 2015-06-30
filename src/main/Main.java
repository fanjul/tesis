package main;

import gui.VentanaPrincipal;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = VentanaPrincipal.getInstance();
					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setMinimumSize(frame.getSize());
					frame.setVisible(true);
					
					//////
					
					
				
					
					//////
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
