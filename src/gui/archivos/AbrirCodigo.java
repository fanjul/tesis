package gui.archivos;

import gui.VentanaPrincipal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import syntax.JEditTextArea;

public class AbrirCodigo extends ManejadorArchivos {

	private static final long serialVersionUID = 2107012427432749042L;
	private JEditTextArea textArea;

	public AbrirCodigo(JEditTextArea textArea) {
		this.textArea = textArea;
	}

	@Override
	public void abrirArchivo() throws IOException {

		JFileChooser seleccion;
		if (ultimaRuta == null) {
			seleccion = new JFileChooser();
			seleccion.setCurrentDirectory(new File("./"));
		} else {
			seleccion = new JFileChooser(new File(ultimaRuta));
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos", "java", "txt");
		seleccion.setFileFilter(filter);
		if (seleccion.showOpenDialog(VentanaPrincipal.getInstance()) == JFileChooser.APPROVE_OPTION) {
			cargarEditor(seleccion);
			ultimaRuta = seleccion.getSelectedFile().getParent();
		}
	}

	private void cargarEditor(JFileChooser seleccion) throws IOException {
		File archivo = seleccion.getSelectedFile();
		BufferedReader buffer;
		try {
			String contenido = "";
			buffer = new BufferedReader(new FileReader(archivo));
			String linea = buffer.readLine();
			while (linea != null) {
				contenido += linea;
				contenido += System.lineSeparator();
				linea = buffer.readLine();
			}

			textArea.setText(contenido);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void abrirBaseDeDatos() {
	}

}
