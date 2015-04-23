package gui;
import java.io.FileReader; 
import java.io.IOException;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.io.File;
import java.io.BufferedReader;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ManejadorArchivos extends JFrame{
	VentanaPrincipal ventana;
	
	public ManejadorArchivos(VentanaPrincipal ventana){
		this.ventana = ventana;
	}
		
	public void abrirBaseDeDatos(){
		JFileChooser seleccion = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mdb , *.accdb", "mdb", "accdb");
		seleccion.setFileFilter(filter);
		seleccion.setCurrentDirectory(new File("./"));
		if (seleccion.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {}
		//	comboBox.setModel(new DefaultComboBoxModel<>(
		//			new String[] { "Cargando datos..." }));
			cargarDatos(seleccion.getSelectedFile());

		
	}
	
	public void abrirArchivo(){
		JFileChooser seleccion = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "java", "txt");
		seleccion.setFileFilter(filter);
		seleccion.setCurrentDirectory(new File("./"));
		if (seleccion.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {}
		//	comboBox.setModel(new DefaultComboBoxModel<>(
		//			new String[] { "Cargando datos..." }));
			cargarDatos(seleccion.getSelectedFile());

		
	}
	
	public void cargarDatos(File archivo){
		
	}
	
}