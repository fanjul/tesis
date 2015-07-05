package gui.archivos;

import gui.VentanaPrincipal;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AbrirCodigo extends ManejadorArchivos {

	@Override
	public void abrirBaseDeDatos() {
	}

	@Override
	public void abrirArchivo() {

		JFileChooser seleccion;
		if (ultimaRuta == null) {
			seleccion = new JFileChooser();
		} else {
			seleccion = new JFileChooser(new File(ultimaRuta));
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Archivos", "java", "txt");
		seleccion.setFileFilter(filter);
		seleccion.setCurrentDirectory(new File("./"));
		if (seleccion.showOpenDialog(VentanaPrincipal.getInstance()) == JFileChooser.APPROVE_OPTION) {

			// comboBox.setModel(new DefaultComboBoxModel<>(
			// new String[] { "Cargando datos..." }));
			// cargarDatos(seleccion.getSelectedFile());
			ultimaRuta = seleccion.getSelectedFile().getParent();

		}
	}

}
