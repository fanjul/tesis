package gui.archivos;

import gui.VentanaPrincipal;

import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.joda.time.LocalDate;

public class AbrirBaseDeDatos extends ManejadorArchivos {

	private static final long serialVersionUID = 3115930476309649671L;

	public AbrirBaseDeDatos(LocalDate fechaInicio, LocalDate fechaFin,
			JComboBox comboBoxSeleccionarIndicador, JTextField txtFechaInicio,
			JTextField txtFechaFin, JComboBox comboBoxPeriodo){
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comboBoxSeleccionarIndicador = comboBoxSeleccionarIndicador;
		this.txtFechaInicio = txtFechaInicio;
		this.txtFechaFin = txtFechaFin;
		this.comboBoxPeriodo = comboBoxPeriodo;
	}
	
	public void abrirBaseDeDatos() {
		JFileChooser seleccion;
		if (ultimaRuta == null) {
			seleccion = new JFileChooser();
		} else {
			seleccion = new JFileChooser(new File(ultimaRuta));
		}
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"*.mdb , *.accdb", "mdb", "accdb");
		seleccion.setFileFilter(filter);

		if (seleccion.showOpenDialog(VentanaPrincipal.getInstance()) == JFileChooser.APPROVE_OPTION) {
			comboBoxSeleccionarIndicador.setModel(new DefaultComboBoxModel<>(
					new String[] { "Cargando datos..." }));
			cargarDatos(seleccion.getSelectedFile());
			ultimaRuta = seleccion.getSelectedFile().getParent();
		}

	}

	@Override
	public void abrirArchivo() {
	}

}
