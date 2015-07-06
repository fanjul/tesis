package gui.archivos;

import gui.VentanaPrincipal;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.joda.time.LocalDate;

import properties.Parameters;
import properties.PropertyManager;
import baseDatos.DBIndicador;
import baseDatos.DBValorIndicador;
import baseDatos.Item;

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
			seleccion.setCurrentDirectory(new File("./"));

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
	

	private void cargarDatos(File dbPath) {
		PropertyManager.instance().setProperty(Parameters.DB_NAME.toString(),
				"jdbc:ucanaccess://" + dbPath.getAbsolutePath());

		new SwingWorker<Object[], Void>() {

			@Override
			protected Object[] doInBackground() throws Exception {
				DBValorIndicador vIndicador = new DBValorIndicador();
				fechaInicio = vIndicador.getMinDate();
				fechaFin = vIndicador.getMaxDate();
				DBIndicador indicador = new DBIndicador();
				return indicador.getIndicatorNames();
			}

			@SuppressWarnings({ "unchecked" })
			@Override
			public void done() {
				try {
					comboBoxSeleccionarIndicador
							.setModel(new DefaultComboBoxModel<>(get()));
					AutoCompleteDecorator
							.decorate(comboBoxSeleccionarIndicador);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}

				// Cargar el valor de periodo del indicador
				DBIndicador indicador = new DBIndicador();
				// switch
				// (indicador.getPeriodo(indicador.getIdByName(comboBox.getSelectedItem().toString())))
				// {
				switch (indicador
						.getPeriodo(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()))) {
				case "diario":
					comboBoxPeriodo.setSelectedIndex(0);
					break;
				case "mensual":
					comboBoxPeriodo.setSelectedIndex(1);
					break;
				case "anual":
					comboBoxPeriodo.setSelectedIndex(2);
					break;
				}

				// Cambiar la fecha inicio y fin segun el indicador
				DBValorIndicador vIndicador = new DBValorIndicador();
				fechaInicio = vIndicador
						.getMinDate(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()));
				// fechaFin =
				// vIndicador.getMaxDate(indicador.getIdByName(comboBoxSeleccionarIndicador.getSelectedItem().toString()));
				fechaFin = vIndicador
						.getMaxDate(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()));
				txtFechaInicio.setText(fechaInicio.getDayOfMonth() + "/"
						+ fechaInicio.getMonthOfYear() + "/"
						+ fechaInicio.getYear());
				txtFechaFin.setText(fechaFin.getDayOfMonth() + "/"
						+ fechaFin.getMonthOfYear() + "/" + fechaFin.getYear());
				// generarButton.setEnabled(true);
			}
		}.execute();
		;

	}


	@Override
	public void abrirArchivo() {
	}

}
